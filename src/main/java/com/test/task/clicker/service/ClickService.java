package com.test.task.clicker.service;

import com.test.task.clicker.repository.ClickRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author aleshin.dmitrii
 * @since 27.12.2020
 */

@Service
public class ClickService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClickService.class);

	private final ClickRepository repository;

	private AtomicInteger clickNumber;

	public ClickService(ClickRepository repository) {
		this.repository = repository;
	}

	@PostConstruct
	public void init() {
		Integer cN = repository.getClickNumber();
		LOGGER.debug("Init clickNumber this {} value", cN);
		clickNumber = new AtomicInteger();
		clickNumber.set(cN);
	}

	@PreDestroy
	public void destroy() {
		Integer cN = clickNumber.get();
		LOGGER.debug("Destroy clicker, save last value: {}", cN);
		repository.saveClickNumber(cN);
	}

	public Integer incrementClickNumber() {
		Integer curClickNumber = clickNumber.incrementAndGet();
		LOGGER.info("Incremented clickNumber: {}", curClickNumber);
		repository.saveClickNumber(curClickNumber);
		return curClickNumber;
	}

	public Integer getClickNumber() {
		return clickNumber.get();
	}

}
