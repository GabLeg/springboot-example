package com.example.springbootexample.config;

import com.example.springbootexample.SpringbootExampleApplication;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lognet.springboot.grpc.autoconfigure.GRpcServerProperties;
import org.lognet.springboot.grpc.context.LocalRunningGrpcPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SpringbootExampleApplication.class})
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
// Transactional + Sql combo make the test-data.sql to run every time. Data are always fresh for every test.
@Transactional
@Sql("/data/test-data.sql")
public abstract class IntegrationTestParent {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private GRpcServerProperties gRpcServerProperties;

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected ConfigService configService;

    protected MockMvc mockMvc;
    protected MockRestServiceServer mockServer;
    protected ManagedChannel grpcChannel;

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                                      .build();
        //Create a fake Rest Service server
        RestGatewaySupport gateway = new RestGatewaySupport();
        gateway.setRestTemplate(restTemplate);
        mockServer = MockRestServiceServer.createServer(gateway);
    }

    @BeforeEach
    void initGrpcChannel() {
        ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder.forAddress("localhost", gRpcServerProperties.getPort())
                                                                       .usePlaintext();
        grpcChannel = channelBuilder.build();
    }

    @AfterEach
    void shutdownGrpcChannel() {
        grpcChannel.shutdown();
    }
}
