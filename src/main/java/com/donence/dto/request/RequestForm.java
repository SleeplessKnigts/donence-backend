package com.donence.dto.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RequestForm {

    @NotEmpty
    private String requestType;
}
