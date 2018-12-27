package io.pick5.constants;


import static java.util.Map.entry;

import java.util.Map;

public class BovadaConstants {
	public final static Map<String,String> QUERY_PARAMS = Map.ofEntries(
	        entry("marketFilterId", "def"),
	        entry("preMatchOnly", "true"),
	        entry("lang", "en")
	    );
}
