package com.renren.api.thrift.impl;

import java.util.List;

import org.apache.thrift.TException;
import org.slf4j.Log;
import org.slf4j.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renren.api.Constants;
import com.renren.api.biz.ConnectionBiz;
import com.renren.api.model.App;
import com.renren.api.thrift.ConnectionInfo;
import com.renren.api.thrift.ConnectionService;
import com.renren.api.thrift.OpenCloudException;

@Service
public class ConnectionServiceImpl implements ConnectionService.Iface {

	
	private Log logger = LogFactory.getLog(getClass());	
	@Autowired
	private ConnectionBiz connBiz;
	
	
	@Override
	public List<ConnectionInfo> getConnection(String appKey, String type ) throws OpenCloudException, TException {
	
		logger.info( "get the connection with the appKey: " + appKey + ", and the type: " + type );
		App app = connBiz.getAppByKey( appKey );
		if ( app == null ){
			logger.error( "the app of the key(" + appKey + ") is null. ");
			throw new OpenCloudException(Constants.ErrorCode.APP_KEY_NOT_EXIST, "the appKey is not existed, please check it, or connect the app admin");
		}
		List<ConnectionInfo> connList = connBiz.getConnectionInfoListByApp( app, type );
		
//		List<ConnectionInfo> connList = new ArrayList<ConnectionInfo> ();
//		ConnectionInfo ci = new ConnectionInfo();
//		ci.setAddress("chinese");
//		ci.setPort("3611");
//		connList.add( ci );
		return connList;
	}

}
