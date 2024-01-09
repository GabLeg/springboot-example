package com.example.springbootexample.config;

import com.example.springbootexample.SpringbootExampleApplication;
import com.example.springbootexample.domain.services.DoSomethingWithKafkaEventService;
import com.example.springbootexample.infra.mongo.GameDocument;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.transaction.Transactional;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lognet.springboot.grpc.autoconfigure.GRpcServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.EmbeddedKafkaZKBroker;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;
import org.springframework.web.context.WebApplicationContext;
import redis.embedded.RedisServer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SpringbootExampleApplication.class})
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
// Transactional + Sql combo make the test-data.sql to run every time. Data are always fresh for every test.
@Transactional
@Sql("/data/test-data.sql")
public abstract class IntegrationTestParent {

  protected static final String PARTY_TOPIC = "party-topic";
  protected static final String INVITATION_TOPIC = "invitation-topic";
  protected static final EmbeddedKafkaBroker KAFKA = new EmbeddedKafkaZKBroker(1, true, PARTY_TOPIC, INVITATION_TOPIC).kafkaPorts(9092);
  protected static final RedisServer REDIS_SERVER = new RedisServer(6379);
  private final static AtomicBoolean started = new AtomicBoolean(false);
  protected static KafkaConsumer<String, String> kafkaConsumerFixture;
  protected static KafkaTemplate<String, String> kafkaProducerFixture;

  @Autowired
  private RedisConnectionFactory redisConnectionFactory;
  @Autowired
  protected MongoTemplate mongoTemplate;
  @Autowired
  protected ObjectMapper objectMapper;
  @Autowired
  protected RestTemplate restTemplate;
  @Autowired
  protected JmsTemplate jmsTemplate;
  @Autowired
  protected ConfigService configService;
  @MockBean
  protected DoSomethingWithKafkaEventService doSomethingWithKafkaEventService;
  protected MockMvc mockMvc;
  protected MockRestServiceServer mockServer;
  protected ManagedChannel grpcChannel;
  @Autowired
  private WebApplicationContext context;
  @Autowired
  private GRpcServerProperties grpcServerProperties;

  @BeforeAll
  static void startEmbeddedServer() {
    if (!started.getAndSet(true)) {
      REDIS_SERVER.start();
      KAFKA.afterPropertiesSet();
      kafkaConsumerFixture = createKafkaConsumerFixture();
      kafkaProducerFixture = createKafkaProducerFixture();
    }
  }

  private static KafkaConsumer<String, String> createKafkaConsumerFixture() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA.getBrokersAsString());
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "assertion-consumer-group");
    KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
    kafkaConsumer.subscribe(Collections.singleton(PARTY_TOPIC));
    kafkaConsumer.subscribe(Collections.singleton(INVITATION_TOPIC));
    return kafkaConsumer;
  }

  private static KafkaTemplate<String, String> createKafkaProducerFixture() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA.getBrokersAsString());
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(props));
  }

  @BeforeEach
  void init() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    //Create a fake Rest Service server
    RestGatewaySupport gateway = new RestGatewaySupport();
    gateway.setRestTemplate(restTemplate);
    mockServer = MockRestServiceServer.createServer(gateway);
    redisConnectionFactory.getConnection().flushAll();
    mongoTemplate.remove(new Query(), GameDocument.class);
  }

  @BeforeEach
  void initGrpcChannel() {
    ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder.forAddress("localhost", grpcServerProperties.getPort()).usePlaintext();
    grpcChannel = channelBuilder.build();
  }

  @AfterEach
  void shutdownGrpcChannel() {
    grpcChannel.shutdown();
  }
}
