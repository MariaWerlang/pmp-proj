package com.duda.repository.orm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "users")
public record UserOrm(
        @Id
        String id,
        String username,
        String email,
        String password
) {
}
