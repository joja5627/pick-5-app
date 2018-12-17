package io.pick5.web.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@RedisHash("bovadaResponse")
public class BovadaResponse {

	@NonNull
	private final String response;

	@Id
	private String id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private final LocalDateTime timeStamp;

}
