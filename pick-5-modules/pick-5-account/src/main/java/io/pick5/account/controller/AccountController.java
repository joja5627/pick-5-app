package io.pick5.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.pick5.account.model.Account;
import io.pick5.account.repository.AccountRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountRepository repository;

//	@GetMapping()
//	public Flux<Account> findByCustomer(@PathVariable("userId") String userId) {
//		logger.info("find by user id: userId={}", userId);
//		return repository.findByUserId(userId);
//	}

	@GetMapping
	public Flux<Account> findAll() {
		logger.info("findAll");
		return repository.findAll();
	}

	@GetMapping("/user/{userId}")
	public Mono<Account> findById(@PathVariable("id") String userId) {
		logger.info("find by user id: {}", userId);
		return repository.findById(userId);
	}

	@PostMapping("/user/create/")
	public Mono<Account> create(@RequestBody Account account) {
		logger.info("create:{}", account);
		return repository.save(account);
	}

}
