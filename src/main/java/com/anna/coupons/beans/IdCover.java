package com.anna.coupons.beans;

import java.io.IOException;


import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

public class IdCover {
	
	private long id;

	public IdCover(long id) {
		this.id = id;
	}
	public String getId() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(id);
		return json;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "idCover [id=" + id + "]";
	}
	
	

}
