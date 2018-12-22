package io.pick5.wed.util;

import java.util.function.Function;
import java.util.function.Predicate;

import org.mockserver.mock.action.ExpectationCallback;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

public class DefaultCallback  implements ExpectationCallback {
	
	public HttpResponse goodResponse = new HttpResponse().withStatusCode(200).withBody("OK");
	
	public HttpResponse badResponse = new HttpResponse().withStatusCode(400).withBody("BAD");
	
	public HttpResponse getGoodResponse() {
		return goodResponse; 
	}

	public HttpResponse getBadResponse() {
		return badResponse; 
	}
	
	
	
	@Override
	public HttpResponse handle(HttpRequest httpRequest) {
		
		String path = httpRequest.getPath().getValue();
		
		if(path.contains("good")) {
			return getGoodResponse();
		}else if(path.contains("bad")) {
			return getBadResponse();
		}
		return getGoodResponse();
		
	}


}
