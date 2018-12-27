package io.pick5.wed.util;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import org.json.JSONException;

import io.pick5.web.tags.UnitTest;

@UnitTest
public class JsonParsingTests {
	private final Gson gson = new Gson();
	private final File bovadaResFile = new File(
			getClass().getClassLoader().getResource("file/bovada_response.json").getFile());

	private static final Function<JsonObject, JsonElement> getDisplayGroups = (obj) -> {
		return obj.get("displayGroups");
	};

	@Test
	public void shouldParseJson() throws JSONException, IOException {

		String bovadaRes = FileUtils.readFileToString(bovadaResFile, "UTF-8");
		JsonArray responseArray = gson.fromJson(bovadaRes, JsonArray.class);

		assertNotNull(responseArray);
//
//		JsonObject unNestedObject = responseArray.get(0).getAsJsonObject();
//
//		JsonObject events = unNestedObject.getAsJsonObject("events");

	}

}
