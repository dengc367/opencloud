package com.renren.api.server;

import net.paoding.rose.scanning.context.RoseAppContext;

import org.slf4j.Log;
import org.slf4j.LogFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TCompactProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.stereotype.Service;

import com.renren.api.Constants;
import com.renren.api.thrift.ConnectionService;
import com.renren.api.thrift.ConnectionService.Iface;
import com.renren.api.thrift.impl.ConnectionServiceImpl;


@Service
public class ThriftServer{
	private static Log logger = LogFactory.getLog( ThriftServer.class );
	
	static private Iface iface;
	
	public Iface getIface(){
		logger.info( "get Thrift Iface Implementation Object:" + iface );
		return iface;
	}
	
	private void start() {
		try {
			TServerSocket serverTransport = new TServerSocket( Constants.LISTENING_PORT );
			ConnectionService.Processor<Iface> processor = new ConnectionService.Processor<Iface>( getIface() );
			Factory protFactory = new TCompactProtocol.Factory();
			TServer server = new TThreadPoolServer( new Args( serverTransport ).processor(processor).protocolFactory(protFactory));
			logger.info("Starting open cloud server on listening port: " + Constants.LISTENING_PORT + " ...");
			server.serve();
			logger.info( "the cloud server started!" );
		} catch (TTransportException e) {
			logger.error("the Thrift Transport Exception of Starting the openCloud Server, Type: " + e.getType() + " and the Message: " + e.getMessage() );
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("the Exception of Starting the openCloud Server: " + e.getMessage() );
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		
		RoseAppContext rose = new RoseAppContext();
		ThriftServer server = rose.getBean(ThriftServer.class);
		iface = (Iface) rose.getBean(ConnectionServiceImpl.class);
		
		server.start();
	}
	
	
	
	
}
