package com.rest;

import redis.clients.jedis.Jedis;

public class ClientConnectionMapper {
	 
	
	public static Boolean isAllowed(String token) {
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("spyder");
		jedis.incrBy(token,1);
		int callCount=Integer.parseInt(jedis.get(token));
		if (callCount > 5) {
			return false;
		} else {
			return true;
		}
	}public static void workDoneEntry(String token) {
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("spyder");
		jedis.decrBy(token,1);
	}

}
