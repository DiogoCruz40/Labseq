package pt.exercise.Labseq.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import pt.exercise.Labseq.exceptions.LabSeqException;

import java.math.BigInteger;

@Service
public class LabSeqService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String CACHE_KEY_PREFIX = "labseq:";
    @Cacheable(value = CACHE_KEY_PREFIX, key = "#n")
    public BigInteger calculateLabSeq(int n) {
        if (n < 0) {
            throw new LabSeqException("Index must be non-negative");
        }
        String key = CACHE_KEY_PREFIX + n;

        // Check if result is cached in Redis
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            return (BigInteger) redisTemplate.opsForValue().get(key);
        }

        // Default result
        BigInteger result = BigInteger.ZERO;

        // Cases
        if (n == 1) {
            result = BigInteger.ONE;
        } else if (n == 2) {
            result = BigInteger.ZERO;
        } else if (n == 3) {
            result = BigInteger.ONE;
        } else if (n > 4) {
            result = calculateLabSeq(n - 4).add(calculateLabSeq(n - 3));
        }

        // Cache result in Redis
        redisTemplate.opsForValue().set(key, result);

        return result;
    }
}
