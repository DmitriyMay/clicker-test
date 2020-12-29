package com.test.task.clicker.web;

import com.test.task.clicker.config.ClickServiceTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.ResultMatcher.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author aleshin.dmitrii
 * @since 28.12.2020
 */
@WebMvcTest(value = ClickController.class)
@ContextConfiguration(classes = ClickServiceTestConfig.class)
class ClickControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void incrementClickNumber() throws Exception {
		mvc.perform(post("/increment"))
				.andExpect(matchAll(
						status().isOk(),
						content().string(String.valueOf(1))
				));
	}
}