package com.weitac.ql.redis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

public class redisTest {

	@SuppressWarnings("resource")
	public void getCache() throws IOException{
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.auth("0p-0p-0p-");  
        
        BufferedReader buf = null;
	    BufferedWriter bufferedWriter = null;

	    String inputPath = "/usr/local/soft/user_base.csv";
	    
	    buf = new BufferedReader(new InputStreamReader(new FileInputStream(new File(inputPath))));
	    String line;
	    
	    while ((line = buf.readLine()) != null) {
	    String[] value = StringUtils.splitByWholeSeparatorPreserveAllTokens(line, ",");
	    if(value.length==3){
	    String openid = value[0];
	    String pic = value[1];
	    String name = value[2];
	    
        Map<String, String> map = new HashMap<String, String>();
               map.put("name", name);
               map.put("pic", pic);
               jedis.hmset(openid,map);
    }  }  
	}
    public static void main(String[] args) throws IOException{
        redisTest client = new redisTest();
        client.getCache();
    }
	
}
