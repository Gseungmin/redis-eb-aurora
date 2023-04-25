package com.example.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;

@Getter
@RedisHash(value = "resultHistory", timeToLive = 3600)
@AllArgsConstructor
@NoArgsConstructor
public class ResultHistory {

    @Id
    private String id;
    @Indexed
    private String ip; // 필드 값으로 데이터를 찾을 수 있도록 설정 (findByAccessToken)
    private String originalText;
    private String translatedText;

    @Builder
    public ResultHistory(String ip, String originalText, String translatedText) {
        this.ip = ip;
        this.originalText = originalText;
        this.translatedText = translatedText;
    }
}
