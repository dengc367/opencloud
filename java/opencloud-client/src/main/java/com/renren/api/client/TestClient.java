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
import com.renren.open.api.test.ThriftTest;

public class TestClient {
	private static Log logger = LogFactory.getLog(TestClient.class);

	
	public static void main(String[] args)  {
		try {
			TTransport transport = new TSocket( "localhost", 9090 );
			TProtocol protocol = new TCompactProtocol(transport);
			ThriftTest.Client client = new ThriftTest.Client(protocol);
			transport.open();
			client.testVoid();
			double d = client.testDouble(325);
			System.out.println(d);	
			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}
	}
}
