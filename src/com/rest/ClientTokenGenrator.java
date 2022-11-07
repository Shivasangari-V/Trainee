//$Id$
package com.rest;

import java.util.Random;
import java.util.UUID;

public class ClientTokenGenrator {
	public static String tokenGenerator(String clientID) throws Exception{
	Random random = new Random();
    UUID uuid = UUID.randomUUID();
    String token = uuid.toString() + random.nextLong();
    DBUtil.tokenMapper(token, clientID);
    return token;
	}
}
