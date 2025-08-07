package com.interceptorsystem.api.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.Value;
import org.springframework.stereotype.Component;

@Embeddable
@Value
public class Celular {
    String celular;
}
