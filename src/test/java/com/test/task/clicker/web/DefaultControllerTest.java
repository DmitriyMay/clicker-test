package com.test.task.clicker.web;

import com.test.task.clicker.config.ClickServiceTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * @author aleshin.dmitrii
 * @since 28.12.2020
 */
@WebMvcTest(value = DefaultController.class)
@ContextConfiguration(classes = ClickServiceTestConfig.class)
class DefaultControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getClickNumber() throws Exception {
		mvc.perform(get("/"))
				.andExpect(matchAll(
						status().isOk(),
						model().size(1),
						model().attribute("clickNumber", 0),
						view().name("index")
				));
	}
}