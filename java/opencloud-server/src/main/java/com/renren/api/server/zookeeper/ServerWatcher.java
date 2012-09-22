package com.renren.api.server.zookeeper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;


public class ServerWatcher implements Watcher {

	private static Log logger = LogFactory.getLog(ServerWatcher.class);
	
	@Override
	public void process(WatchedEvent event) {
		logger.debug("WatchedEvent received : " + event);
	}

}
