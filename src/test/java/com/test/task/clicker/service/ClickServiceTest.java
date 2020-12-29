package com.test.task.clicker.service;

import com.test.task.clicker.config.ClickServiceTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.OptionalInt;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * @author aleshin.dmitrii
 * @since 28.12.2020
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ClickServiceTestConfig.class)
public class ClickServiceTest {

	@Autowired
	private ClickService clickService;

	@Test
	public void incrementTest() {
		assertEquals(1, clickService.incrementClickNumber().intValue());

		ExecutorService executorService = Executors.newFixedThreadPool(100);
		OptionalInt max = IntStream.rangeClosed(1, 100).mapToObj(t -> executorService.submit(() -> clickService.incrementClickNumber())).mapToInt(this::getValue).max();
		assertTrue(max.isPresent());
		assertEquals(101, max.getAsInt());
	}

	private int getValue(Future<Integer> f) {
		try {
			return f.get();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
