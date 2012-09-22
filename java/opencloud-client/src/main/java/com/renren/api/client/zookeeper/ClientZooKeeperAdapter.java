package com.renren.api.client.zookeeper;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Log;
import org.slf4j.LogFactory;


public class ClientZooKeeperAdapter {
	private static Log logger = LogFactory.getLog(ClientZooKeeperAdapter.class);
	
	
	private static Queue<String> addresses = new LinkedList<String>();
	
	static ClientZooKeeperHelper _helper;
	
	static {
		_helper = ClientZooKeeperHelper.getInstance();
		_helper.setRegister( new ClientZooKeeperRegistry() );
	}
	
	public static void loadAddresses(){
		Set<String> ips = _helper.getUploadServerSet();
		synchronized (addresses) {
			addresses.clear();
			if ( CollectionUtils.isNotEmpty(ips) ) {
				Iterator<String> iter = ips.iterator();
				while ( iter.hasNext() ) {
					String host = iter.next();
					addresses.offer( host );
				}
			}
		}
		logger.info( "the addresses：　" + Arrays.toString(addresses.toArray()) );
	}
	
	public static String getUploadAddress(){
		String ip = null;
		if ( CollectionUtils.isEmpty( addresses ) ) {
			loadAddresses();
		}
		synchronized ( addresses ) {
			ip = addresses.poll();
			addresses.offer( ip );
		}
		return ip;
	}
	
	public static void main(String[] args) {
		System.out.println( ClientZooKeeperAdapter.getUploadAddress() );
		System.out.println( ClientZooKeeperAdapter.getUploadAddress() );
		System.out.println( ClientZooKeeperAdapter.getUploadAddress() );
	}
}
