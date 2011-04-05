package com.trifork.riaksearchpoc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.basho.riak.client.RiakClient;
import com.basho.riak.client.request.RequestMeta;
import com.basho.riak.client.util.Constants;

public class Util {

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	private RoundRobbinLoadBalancer rrloadBalancer;
	private RequestMeta meta = setupMeta();
	
	private RequestMeta setupMeta() {
		rrloadBalancer = new RoundRobbinLoadBalancer("riak02.local", "riak03.local", "riak04.local", "riak05.local");
		RequestMeta meta = new RequestMeta();
		meta.setClientId("testclient");
		meta.setQueryParam(Constants.QP_RETURN_BODY, "false");
		meta.setQueryParam(Constants.QP_DW, "1");
		meta.setQueryParam(Constants.QP_W, "3");			
		return meta;
	}
	
	public RequestMeta getMeta() {
		return meta;
	}
	
	DateFormat getTimeFormatter() {
		return formatter;
	}
	
	public RiakClient getClient() {
		return rrloadBalancer.getRiakClient();
	}
}
