package com.renren.api.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;

import com.renren.api.client.zookeeper.ClientZooKeeperAdapter;

public class ClientAdapter {
	
	private static String CLIENT_PROPERTIES_FILE = "/client.properties";
	
	private static char[] appKey = new char[64];
	
	private static int len = 0;
	
	public static String[] getServerInfo(){
		
		String hostAndPort = ClientZooKeeperAdapter.getUploadAddress();
		if( StringUtils.isBlank(hostAndPort) || ! hostAndPort.contains(":") ){
			return null;
		}
		return hostAndPort.split(":"); 
		
	}
	
	public static String getAppKey(){
		
		if( len ==0 ){
			InputStream is = ClientAdapter.class.getResourceAsStream( CLIENT_PROPERTIES_FILE );
			InputStreamReader reader = new InputStreamReader( is );
			try {
				len = reader.read(appKey);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new String( appKey );
	}
	
	public static void main(String[] args) {
		System.out.println( getServerInfo()[1] );
		System.out.println( getAppKey() );
		System.out.println( getAppKey() );
		
	}
}
