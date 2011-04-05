package com.trifork.riaksearchpoc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import com.basho.riak.client.util.Constants;
import com.basho.riak.pbc.RiakObject;

public class PbTestClient {
	
	private static final int THREADS = 1;

	PbUtil util = new PbUtil();
	
	Monitor monitor = new Monitor();
	
	final Random random = new Random();
	
	public static void main(String[] args) throws Exception {
		new PbTestClient().go();
	}

	private void go() throws JSONException, UnsupportedEncodingException {
		for(int i = 0; i < THREADS; i++) {
			new Thread() {
				public void run() {
					while (true) {
						try {
							final long id = random.nextLong();
							final String time = util.getTimeFormatter().format(new Date());
							createObject(id, random.nextInt(5000), time);
							monitor.increment();
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
				};
			}.start();
		}
	}
	
	static Charset UTF8 = Charset.forName("UTF-8");
	
	private void createObject(long id, long code, String time) throws JSONException {
		JSONObject jo = new JSONObject();
		jo.put("id", id++);
		jo.put("time", time);
		jo.put("code", code);
		RiakObject o = new RiakObject("log", id + "", jo.toString(1).getBytes(UTF8));
		o.setContentType(Constants.CTYPE_JSON_UTF8);
		try {
			util.getClient().store(o, util.getMeta());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
}
