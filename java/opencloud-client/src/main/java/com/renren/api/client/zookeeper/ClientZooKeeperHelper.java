package com.renren.api.client.zookeeper;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.KeeperException;

import com.renren.api.zookeeper.ZooKeeperHelper;
import com.renren.api.zookeeper.ZooKeeperRegister;



/**
 * 访问ZooKeeper服务的帮助类
 * @author Zhancheng Deng
 * @since 2011.11.02
 */
public class ClientZooKeeperHelper extends ZooKeeperHelper {
	
	private ClientZooKeeperHelper(){
		super( new ClientWatcher() );
	}
	
	public static ClientZooKeeperHelper getInstance(){
		if ( _helper == null ) {
			_helper = new ClientZooKeeperHelper();
		}
		return (ClientZooKeeperHelper) _helper;
	}
	
	@Override
	public void setRegister(ZooKeeperRegister register) {
		 ( (ClientWatcher) watcher).setRegister(register);
	}
	
	public Set<String> getUploadServerSet() {
		
		Set<String> hosts = new HashSet<String>();
		try {
			if ( zk.exists(ZOOKEEPER_ROOT_PATH, false) != null  ) {
				List<String> hostChildren = zk.getChildren( ZOOKEEPER_ROOT_PATH, watcher, null );
				for ( int i = 0; i < hostChildren.size(); i++ ) {
					String host = hostChildren.get(i);
					byte[] bytes = zk.getData( ZOOKEEPER_ROOT_PATH + "/" + host, watcher, null );
					String flag = new String(bytes);
					if ( StringUtils.isNotBlank(flag) && "enabled".equals( flag ) ) {
						hosts.add( host );
					}
				}
			}
		} catch (KeeperException e) {
			logger.warn( "the ZooKeeper throw Exception ", e );
		} catch (InterruptedException e) {
			logger.warn( "the thread was interrupted! ", e );
		}
		return hosts;
	}
	
	public static void main(String[] args) {
		Set<String> set = ClientZooKeeperHelper.getInstance().getUploadServerSet();
		System.out.println(set);
	}

	

}
