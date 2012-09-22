package com.renren.api.server.zookeeper;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Log;
import org.slf4j.LogFactory;
import org.springframework.stereotype.Service;



@Service
public class ServerZooKeeperAdapter {

	private static Log logger = LogFactory.getLog(ServerZooKeeperAdapter.class);
	
	public static String ZOOKEEPER_SERVER = "zookeeper.server";
	
	
	static {
		createEphemeralNode();
	}
	/**
	 * 用于添加这个机器 的 监控节点，用于监控
	 */
	public static void createEphemeralNode(){
		logger.info("start the zookeeper server");
		String hostName =  getUploadAddressHostName();
		
		String msg = "this domain of this server is : " + hostName;
		System.out.println(msg);
		if (logger.isDebugEnabled() ){
			logger.debug(msg);
		}
		ServerZooKeeperHelper.getInstance().createEphemeralServer( hostName );
		
	}
	
	/**
	 * @return 域名地址 
	 */
	public static String getUploadAddressHostName(){
			String hostName = System.getProperty( ZOOKEEPER_SERVER ); 
				
			if ( StringUtils.isBlank( hostName ) ){
				String errorString = "the domain name is not declared, please add it in two ways: " +
						"eg, -Dzookeeper.address=123.125.45.86:7911";
				logger.error( errorString );
				throw new InternalError(errorString);
			}
			return hostName;
	}
	
	public static void main(String[] args) {
		createEphemeralNode();
		System.out.println( getUploadAddressHostName() );
	}
}
