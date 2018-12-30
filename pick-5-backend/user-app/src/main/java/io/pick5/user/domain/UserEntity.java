package io.pick5.user.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.pick5.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Document
@Getter 
@Setter
public class UserEntity extends User {
	
	@Id
	private String id;
}
