package com.test.task.clicker.web;

import com.test.task.clicker.service.ClickService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aleshin.dmitrii
 * @since 27.12.2020
 */

@RestController
public class ClickController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClickController.class);

	private final ClickService clickService;

	public ClickController(ClickService clickService) {
		this.clickService = clickService;
	}

	@PostMapping("/increment")
	public ResponseEntity<Integer> incrementClickNumber() {
		LOGGER.info("Start increment click number");
		return ResponseEntity.ok().body(clickService.incrementClickNumber());
	}
}
