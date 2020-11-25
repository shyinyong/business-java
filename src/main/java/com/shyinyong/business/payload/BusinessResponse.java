package com.shyinyong.business.payload;

import lombok.*;

import java.time.Instant;

@Data
public class BusinessResponse {
    private Long id;
    private String question;
    private Instant creationDateTime;
    private Instant expirationDateTime;
    private Boolean isExpired;
}
