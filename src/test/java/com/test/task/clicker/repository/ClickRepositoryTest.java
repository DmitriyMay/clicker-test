package com.test.task.clicker.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author aleshin.dmitrii
 * @since 28.12.2020
 */
@JdbcTest
@TestPropertySource("classpath:application-integration.properties")
class ClickRepositoryTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void saveAndGetClickNumber() {
		ClickRepository clickRepository = new ClickRepository(jdbcTemplate);
		clickRepository.init();
		clickRepository.saveClickNumber(1);
		assertEquals(1, clickRepository.getClickNumber().intValue());
	}
}