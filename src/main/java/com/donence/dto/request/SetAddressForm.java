package com.donence.dto.request;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SetAddressForm {
    @DecimalMax("90.0")
    @DecimalMin("-90.0")
    @NotNull(message = "Latitude field cannot be empty")
    private Double lat;

    @DecimalMax("180.0")
    @DecimalMin("-180.0")
    @NotNull(message = "Longitude field cannot be empty")
    private Double lon;

}
