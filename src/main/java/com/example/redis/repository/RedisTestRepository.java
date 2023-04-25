package com.example.redis.repository;

import com.example.redis.entity.ResultHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface RedisTestRepository extends JpaRepository<ResultHistory, String> {
    Optional<List<ResultHistory>> findByIpOrder(String ip);
}
