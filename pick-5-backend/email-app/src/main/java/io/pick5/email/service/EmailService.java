package io.pick5.email.service;

import io.pick5.domain.User;

public interface EmailService {
	void buildAndSendEmail(User user);
}