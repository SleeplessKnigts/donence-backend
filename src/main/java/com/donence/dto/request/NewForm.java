package com.donence.dto.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewForm {
    
    @NotBlank
    private String heading;

    @NotBlank
    private String content;

    @NotBlank
    private String imageUrl;
    
}
