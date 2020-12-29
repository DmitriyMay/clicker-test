package com.test.task.clicker.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.test.task.clicker.dict.Sql.*;

/**
 * @author aleshin.dmitrii
 * @since 27.12.2020
 */
@Component
public class ClickRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClickRepository.class);
	private final JdbcTemplate jdbcTemplate;

	public ClickRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@PostConstruct
	public void init() {
		updateIfNeedSqlSchema();
	}

	private void updateIfNeedSqlSchema() {
		jdbcTemplate.execute(CREATE_TABLE.sqlQuery());
		Integer countRow = jdbcTemplate.query(GET_COUNT_CLICK_NUMBER.sqlQuery(), resultSet ->
				resultSet.next() ? resultSet.getInt(1) : 0);
		if (countRow == null) {
			throw new RuntimeException("invalid table clicks");
		}
		if (countRow == 0) {
			jdbcTemplate.execute(INIT_CLICK_NUMBER.sqlQuery());
		}
	}

	public void saveClickNumber(Integer value) {
		LOGGER.info("Save value: {}", value);
		jdbcTemplate.execute(UPDATE_CLICK_NUMBER.updateSqlQuery(value));
	}

	public Integer getClickNumber() {
		return jdbcTemplate.query(GET_CLICK_NUMBER.sqlQuery(), resultSet ->
				resultSet.next() ? resultSet.getInt(1) : 0);
	}
}
