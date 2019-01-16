package io.pick5.user.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import io.pick5.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper=false)
@Document
@Data
@ToString
public class UserEntity extends User {
	

}
