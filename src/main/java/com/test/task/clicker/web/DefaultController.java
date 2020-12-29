package com.test.task.clicker.web;

import com.test.task.clicker.service.ClickService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author aleshin.dmitrii
 * @since 27.12.2020
 */
@Controller
public class DefaultController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultController.class);

	private final ClickService clickService;

	public DefaultController(ClickService clickService) {
		this.clickService = clickService;
	}

	@GetMapping("/")
	public String getClickNumber(Model model) {
		LOGGER.info("Get current click number");
		model.addAttribute("clickNumber", clickService.getClickNumber());
		return "index";
	}
}
