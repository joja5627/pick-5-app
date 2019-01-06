package io.pick5.user.domain;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.pick5.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper=false)
@Document
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends User {
	
	@Id
	private String mongoId = UUID.randomUUID().toString();
}
