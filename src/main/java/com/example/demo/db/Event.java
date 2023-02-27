package com.example.demo.db;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Value
@Builder
public class Event {

    @Id
    Integer id;
    @NotNull
    Instant timestamp;
}
