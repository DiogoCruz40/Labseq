package pt.exercise.Labseq.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class LabSeqService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String CACHE_KEY_PREFIX = "labseq:";

    public BigInteger calculateLabSeq(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Index must be non-negative");
        }

        String key = CACHE_KEY_PREFIX + n;

        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            return (BigInteger) redisTemplate.opsForValue().get(key);
        }

        // Calculate and cache result in Redis
        BigInteger result = calculateLabSeq(n - 4).add(calculateLabSeq(n - 3));
        redisTemplate.opsForValue().set(key, result);
        return result;
    }
}
