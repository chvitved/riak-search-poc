package com.trifork.riaksearchpoc;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.basho.riak.pbc.IRequestMeta;
import com.basho.riak.pbc.RequestMeta;
import com.basho.riak.pbc.RiakClient;

public class PbUtil {

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	private RiakClient client;
	private RequestMeta meta = setupMeta();
	
	PbUtil() {
		try {
			client = new RiakClient("127.0.0.1", 8087);
			client.setClientID("testclient");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private RequestMeta setupMeta() {
		RequestMeta meta = new RequestMeta();
		meta.returnBody(false);
		meta.dw(1);
		meta.w(2);			
		return meta;
	}
	
	public IRequestMeta getMeta() {
		return meta;
	}
	
	DateFormat getTimeFormatter() {
		return formatter;
	}
	
	public RiakClient getClient() {
		return client;
	}
	
}
