package com.trifork.riaksearchpoc;

import com.basho.riak.client.RiakClient;
import com.basho.riak.client.response.BucketResponse;
import com.basho.riak.client.response.FetchResponse;

public class Getter {
	
	public static void main(String[] args) {
		Util util = new Util();
		RiakClient client = util.getClient();
		
		BucketResponse response = null;
		try {
			response = client.streamBucket("log");
			if (response.isSuccess()) {
				for (final String key : response.getBucketInfo().getKeys()) {
					FetchResponse res = client.fetch("log", key);
					System.out.println(res.getBodyAsString());
				}
			}
		} catch (Exception e) {
			
		}
			
		
		
	}

}
