package com.example.springbootexample.infra.redis;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.Duration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash(value = "movie")
public class MovieDocument implements Serializable {

  @Id
  private String id;
  @Indexed
  private String name;
  private Duration duration;
}
