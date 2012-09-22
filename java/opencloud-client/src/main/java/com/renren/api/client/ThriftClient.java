package com.renren.api.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.*;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.*;
import org.slf4j.Log;
import org.slf4j.LogFactory;

import com.renren.api.Constants;
import com.renren.api.thrift.ConnectionService;
import com.renren.api.thrift.ConnectionInfo;
import com.renren.api.thrift.OpenCloudException;

public class ThriftClient {
	private static Log logger = LogFactory.getLog(ThriftClient.class);

	static List<ConnectionInfo> infos = new ArrayList<ConnectionInfo> ();
	
	public static void main(String[] args)  {
		try {
			String[] info = ClientAdapter.getServerInfo();
			TTransport transport = new TSocket( info[0], new Integer( info[1] ) );
			TProtocol protocol = new TCompactProtocol(transport);
			ConnectionService.Client client = new ConnectionService.Client( protocol );
			transport.open();
			try {
				infos = client.getConnection( ClientAdapter.getAppKey().trim(), Constants.MEMCACHE );
			} catch (OpenCloudException e) {
				logger.error( "the open cloud exception type: " + e.getType() + ", the message: " + e.getMsg() );
			} 
			System.out.println(infos.size());
			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}
	}
}
