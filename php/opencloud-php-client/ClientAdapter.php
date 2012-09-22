<?php

require_once dirname(__FILE__).'/ZooKeeperAdapter.php';

class ClientAdapter {
	
	private static $APP_KEY = "sadfsadgdsfsaxfsdaewrwdfsadf"; 
	public static $MEMCACHE = "memcache"; 
	public static $MYSQL = "mysql"; 
	
	public static function getServerInfo() {
//		$host = '10.32.16.81';
//		$port = 7911;
//		$hostAndPort = array($host, $port);
//		return $hostAndPort;
		return ZooKeeperAdapter::getServerAddress();
	}
	
	public static function getAppKey() {
		return self::$APP_KEY;
	}
}



