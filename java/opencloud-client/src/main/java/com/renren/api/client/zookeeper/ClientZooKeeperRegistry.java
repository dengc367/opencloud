package com.renren.api.client.zookeeper;

import com.renren.api.zookeeper.ZooKeeperRegister;


public class ClientZooKeeperRegistry implements ZooKeeperRegister {

	@Override
	public void callback() {
		ClientZooKeeperAdapter.loadAddresses();
	}

}
