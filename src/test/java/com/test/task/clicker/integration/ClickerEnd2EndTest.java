package com.test.task.clicker.integration;

import com.test.task.clicker.ClickerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author aleshin.dmitrii
 * @since 28.12.2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClickerApplication.class)
@AutoConfigureMockMvc
@AutoConfigureJdbc
@TestPropertySource("classpath:application-integration.properties")
public class ClickerEnd2EndTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getClickNumberEnd2EndTest() throws Exception {
		mvc.perform(get("/"))
				.andExpect(matchAll(
						status().isOk(),
						model().size(1),
						model().attribute("clickNumber", 0),
						view().name("index")
				));
	}

	@Test
	public void incrementEnd2EndTest() throws Exception {
		mvc.perform(post("/increment"))
				.andExpect(matchAll(
						status().isOk(),
						content().string(String.valueOf(1))
				));
	}

}
