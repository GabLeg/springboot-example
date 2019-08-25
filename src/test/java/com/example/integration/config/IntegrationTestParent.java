package com.example.integration.config;

import com.example.springbootexample.SpringbootExampleApplication;
import com.example.springbootexample.config.ConfigService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringbootExampleApplication.class})
@TestPropertySource("classpath:application-test.properties")
@ComponentScan("com.example.springbootexample")
@ActiveProfiles("test")
// Transactional + Sql combo make the test-data.sql to run every time. Data are always fresh for every test.
@Transactional
@Sql("/data/test-data.sql")
public abstract class IntegrationTestParent {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    protected ConfigService configService;

    protected MockMvc mockMvc;
    protected MockRestServiceServer mockServer;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        //Create a fake Rest Service server
        RestGatewaySupport gateway = new RestGatewaySupport();
        gateway.setRestTemplate(restTemplate);
        mockServer = MockRestServiceServer.createServer(gateway);
    }
}
