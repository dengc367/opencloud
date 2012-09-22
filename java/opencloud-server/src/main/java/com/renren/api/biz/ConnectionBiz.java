package com.renren.api.biz;

import java.util.List;

import com.renren.api.model.App;
import com.renren.api.thrift.ConnectionInfo;


public interface ConnectionBiz {

	public App getAppByKey(String appKey);

	public List<ConnectionInfo> getConnectionInfoListByApp(App app, String type);

}
