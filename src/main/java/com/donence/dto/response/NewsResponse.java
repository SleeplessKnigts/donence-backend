package com.donence.dto.response;

import java.time.LocalDateTime;

import lombok.Data;
import net.bytebuddy.asm.Advice.Local;


@Data
public class NewsResponse {
    private String heading;
    private String content;
    private LocalDateTime createdAt;
    private String imageUrl;

    public NewsResponse(String content, String heading, LocalDateTime createdAt, String imageUrl){
        this.heading = heading;
        this.content = content;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
    }
}
