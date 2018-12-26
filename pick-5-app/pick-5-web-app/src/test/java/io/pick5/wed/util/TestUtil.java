package io.pick5.wed.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Mono;

@Getter
@Setter
public class TestUtil {
	public <T> T valueFromMono(Mono<T> mono) {
		return mono.block();
	}

	private final Gson gson = new Gson();

	private final File bovadaResFile = new File(
			getClass().getClassLoader().getResource("file/bovada_response.json").getFile());

	private String bovadaResString;
	private JsonArray bovadaResJsonArray;

	public TestUtil() {
		try {
			bovadaResString = FileUtils.readFileToString(bovadaResFile, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bovadaResJsonArray = gson.fromJson(bovadaResString, JsonArray.class);
	}

	public JsonArray getBovadaResJsonArray() {
		return bovadaResJsonArray;
	}

}
