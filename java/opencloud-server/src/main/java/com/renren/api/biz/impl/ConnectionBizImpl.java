package com.renren.api.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Log;
import org.slf4j.LogFactory;

import com.renren.api.biz.BaseBiz;
import com.renren.api.biz.ConnectionBiz;
import com.renren.api.model.App;
import com.renren.api.model.AppService;
import com.renren.api.model.Cluster;
import com.renren.api.model.Node;
import com.renren.api.model.Service;
import com.renren.api.thrift.ConnectionInfo;

@org.springframework.stereotype.Service
public class ConnectionBizImpl extends BaseBiz implements ConnectionBiz {

	private Log logger = LogFactory.getLog(getClass());
	
	@Override
	public App getAppByKey(String appKey) {
		return appDao.getByAppKey( appKey.trim() );
	}

	@Override
	public List<ConnectionInfo> getConnectionInfoListByApp(App app, String type) {
		
		logger.info("app: " + app.getId() + ": " + app.getAppKey() );
		List<AppService> asList = appServiceDao.getAppServiceListByAppId( app.getId() );
		List<ConnectionInfo> ciList = new ArrayList<ConnectionInfo>();
		ConnectionInfo ci = null;
		logger.info("app service size : " + asList.size() );
		if( CollectionUtils.isNotEmpty(asList) ){
			for( AppService as : asList ){
				Service s = serviceDao.getServiceById( as.getServiceId() );
				logger.info( "the service id : " + s.getId() + ", type: " + s.getType() );
				if ( s == null || ! s.getType().trim().equalsIgnoreCase( type ) )
					continue;
				Cluster c = clusterDao.getClusterById( s.getClusterId() );
				List<Node> nlist = nodeDao.getNodeListByClsuterId( c.getId() );
				if ( CollectionUtils.isEmpty( nlist ) )
					continue;
				for ( Node n : nlist ) {
					ci = new ConnectionInfo();
					ci.setAddress( n.getIp() );
					ci.setPort( String.valueOf( s.getPort() ) );
					ci.setType( s.getType() );
					ciList.add( ci );
				}
			}
			logger.info("node list: " + ciList.size() );
			return ciList;
		} else 
			return null;
	}


}
