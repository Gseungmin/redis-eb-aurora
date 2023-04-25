package com.example.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash(value = "resultHistory", timeToLive = 3600) //@Entity 어노테이션
@AllArgsConstructor
@NoArgsConstructor
public class ResultHistory {

    @Id //PK
    private Long id;
    @Indexed //필드 값으로 데이터를 찾을 수 있도록 설정 (findByIp)
    private String ip;
    private String originalText;
    private String translatedText;

    @Builder
    public ResultHistory(String ip, String originalText, String translatedText) {
        this.ip = ip;
        this.originalText = originalText;
        this.translatedText = translatedText;
    }
}