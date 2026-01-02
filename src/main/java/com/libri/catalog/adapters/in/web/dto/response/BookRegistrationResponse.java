package com.libri.catalog.adapters.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BookRegistrationResponse {
    private int status;
    private String message;
}
