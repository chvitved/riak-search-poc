package com.trifork.riaksearchpoc;

import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.json.JSONException;

import com.basho.riak.client.RiakClient;
import com.basho.riak.client.RiakConfig;
import com.basho.riak.client.request.RequestMeta;
import com.basho.riak.client.util.Constants;

public class GetAllKeys {

	private RiakClient client;
	private RequestMeta meta;

	GetAllKeys() {
		setupMeta();
		client = createRiakClient("localhost", 5098, 100);
	}

	private void setupMeta() {
		meta = new RequestMeta();
		meta.setClientId("testclient");
		meta.setQueryParam(Constants.QP_RETURN_BODY, "false");
		meta.setQueryParam(Constants.QP_DW, "1");
		meta.setQueryParam(Constants.QP_W, "3");
		
	}

	public static void main(String[] args) throws Exception {
		new GetAllKeys().go();
	}

	private void go() throws JSONException, UnsupportedEncodingException {
		
		int size = client.listBucket("_rs_schema").getBucketInfo().getKeys().size();
		size += client.listBucket("log").getBucketInfo().getKeys().size();
		size += client.listBucket("_rsid_log").getBucketInfo().getKeys().size();
		System.out.println("keys " + size);
	}

	static RiakClient createRiakClient(String host, int port, int maxConnections) {
		String url = "http://" + host + ":" + port + "/riak/";
		RiakConfig config = new RiakConfig(url);
		config.setMaxConnections(maxConnections);
		RiakClient riakClient = new com.basho.riak.client.RiakClient(config);

		MultiThreadedHttpConnectionManager cm = (MultiThreadedHttpConnectionManager) riakClient.getHttpClient().getHttpConnectionManager();
		HttpConnectionManagerParams params = cm.getParams();
		params.setDefaultMaxConnectionsPerHost(maxConnections);
		//config.setTimeout(15000);
		return riakClient;
	}

}
