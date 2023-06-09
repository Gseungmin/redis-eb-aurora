package com.example.redis.repository;


import com.example.redis.entity.ResultHistory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class ResultRedisRepositoryTest {

    @Autowired
    private ResultRedisRepository redisRepository;

    @AfterEach
    void afterAll() {
        redisRepository.deleteAll();
    }

    @Test
    void save() throws Exception {
        // given
        ResultHistory result = ResultHistory.builder()
                .ip("127.0.0.1")
                .originalText("안녕하세요.")
                .translatedText("hello")
                .build();

        // when
        ResultHistory save = redisRepository.save(result);

        // then
        ResultHistory find = redisRepository.findById(save.getId()).get();
        log.info("id: {}", find.getId());
        log.info("original text: {}", find.getOriginalText());
        log.info("translated text: {}", find.getTranslatedText());

        Assertions.assertThat(save.getIp()).isEqualTo(find.getIp());
        Assertions.assertThat(save.getOriginalText()).isEqualTo(find.getOriginalText());
        Assertions.assertThat(save.getTranslatedText()).isEqualTo(find.getTranslatedText());
    }

    @Test
    void save_multi() throws Exception {
        // given
        ResultHistory rst1 = ResultHistory.builder()
                .ip("127.0.0.1")
                .originalText("안녕하세요.")
                .translatedText("hello")
                .build();

        ResultHistory rst2 = ResultHistory.builder()
                .ip("127.0.0.1")
                .originalText("반갑습니다.")
                .translatedText("Nice to meet you.")
                .build();

        ResultHistory rst3 = ResultHistory.builder()
                .ip("127.1.1.1")
                .originalText("반갑습니다.")
                .translatedText("Nice to meet you.")
                .build();

        // when
        redisRepository.save(rst1);
        redisRepository.save(rst2);
        redisRepository.save(rst3);

        // then
        List<ResultHistory> results = redisRepository.findByIp("127.0.0.1").get();
        Assertions.assertThat(results.size()).isEqualTo(2);
    }
}