package com.emart.order.service.orderservice.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;

public enum TransactionType {
    CREDIT("credit"),
    DEBIT("debit");
    
    private final String type;
    
    @JsonValue
    public String getType() {
      return type;
    }

    private TransactionType(String type) {
        this.type = type;
    }

    @JsonCreator
    public static TransactionType of(final String type) {
      return Stream.of(TransactionType.values())
          .filter(p -> p.getType().equalsIgnoreCase(type))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
}