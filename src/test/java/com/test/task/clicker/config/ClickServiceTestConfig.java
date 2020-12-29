package com.test.task.clicker.config;

import com.test.task.clicker.repository.ClickRepository;
import com.test.task.clicker.service.ClickService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

/**
 * @author aleshin.dmitrii
 * @since 28.12.2020
 */
@TestConfiguration
public class ClickServiceTestConfig {

	@MockBean
	private ClickRepository clickRepository;

	@Bean
	public ClickService clickService(ClickRepository clickRepository) {
		return new ClickService(clickRepository);
	}
}
