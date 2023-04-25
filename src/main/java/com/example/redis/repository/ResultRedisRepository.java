package com.example.redis.repository;

import com.example.redis.entity.ResultHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRedisRepository extends CrudRepository<ResultHistory, Long> {
    Optional<List<ResultHistory>> findByIpAsc(String ip);
}
