package com.trifork.riaksearchpoc;

import com.basho.riak.client.RiakClient;
import com.basho.riak.client.RiakConfig;

public class RoundRobbinLoadBalancer {
	
	private RiakClient[] clients;
	private int index = 0;
	
	RoundRobbinLoadBalancer(String ... addresses) {
		clients = new RiakClient[addresses.length];
		for (int i = 0; i < addresses.length; i++) {
			clients[i] = createRiakClient(addresses[i], 8098, 1000);
		}
	}
	
	synchronized RiakClient getRiakClient() {
		RiakClient rc = clients[index];
		index = (index + 1) % clients.length;
		return rc;
	}
	
	private static RiakClient createRiakClient(String host, int port, int maxConnections) {
		String url = "http://" + host + ":" + port + "/riak/";
		RiakConfig config = new RiakConfig(url);
		config.setMaxConnections(maxConnections);
		RiakClient riakClient = new com.basho.riak.client.RiakClient(config);
		return riakClient;
	}	

}
