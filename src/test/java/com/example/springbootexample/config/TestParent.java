package com.example.springbootexample.config;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@PropertySource("classpath:application-test.properties")
@Import(TestConfig.class)
@ActiveProfiles(value = {"test"})
public abstract class TestParent {
}
