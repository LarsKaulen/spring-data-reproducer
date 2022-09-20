package com.example.demo.db;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;

@Table("USER")
@Value
@Builder
public class User {

    @Id @Column("USER_ID") Integer id;
    @NotEmpty @Column("USER_NAME") String username;
    @NotEmpty @Column("USER_PASSWORD") String password;
}
