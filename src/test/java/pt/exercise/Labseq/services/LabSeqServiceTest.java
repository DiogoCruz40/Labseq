package pt.exercise.Labseq.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class LabSeqServiceTest {

	@Mock
	private RedisTemplate<String, Object> redisTemplate;
	@Mock
	private ValueOperations<String, Object> valueOperations;
	@InjectMocks
	private LabSeqService labSeqService;

	@BeforeEach
	public void setUp() {
		Mockito.lenient().when(redisTemplate.opsForValue()).thenReturn(valueOperations);
	}
	@Test
	public void testCalculateLabSeq_CachedValueExists() {
		// Mock RedisTemplate behavior to return a cached value
		when(redisTemplate.hasKey(any())).thenReturn(true);
		when(valueOperations.get(any())).thenReturn(new BigInteger("5"));

		// Test calculation for a specific index
		BigInteger result = labSeqService.calculateLabSeq(5);

		// Verify that the result is the cached value
		assertEquals(new BigInteger("5"), result);
	}

	@Test
	public void testCalculateLabSeq_IndexZero() {
		// Test calculation for index 0
		BigInteger result = labSeqService.calculateLabSeq(0);

		// Verify that the result is 0
		assertEquals(BigInteger.ZERO, result);
	}

	@Test
	public void testCalculateLabSeq_IndexOne() {
		// Test calculation for index 1
		BigInteger result = labSeqService.calculateLabSeq(1);

		// Verify that the result is 1
		assertEquals(BigInteger.ONE, result);
	}

	@Test
	public void testCalculateLabSeq_IndexTwo() {
		// Test calculation for index 2
		BigInteger result = labSeqService.calculateLabSeq(2);

		// Verify that the result is 0
		assertEquals(BigInteger.ZERO, result);
	}

	@Test
	public void testCalculateLabSeq_IndexThree() {
		// Test calculation for index 3
		BigInteger result = labSeqService.calculateLabSeq(3);

		// Verify that the result is 1
		assertEquals(BigInteger.ONE, result);
	}

	@Test
	public void testCalculateLabSeq_NegativeIndex() {
		// Test calculation for a negative index, should throw an exception
		assertThrows(RuntimeException.class, () -> labSeqService.calculateLabSeq(-1));
	}

	// Add more test cases as needed
}
