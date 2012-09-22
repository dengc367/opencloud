#!/usr/bin/env php
<?php


class ZooKeeperAdapter{

	public static $CLOUD = "/cloud";
	public static $CONNECTION_STR = "10.32.16.81:2181/open";
	
	public static $ADDRESSES = array();
	
	public static function getServerAddress(){
		if ( count(self::$ADDRESSES) == 0 ){
			$zk = new Zookeeper( self::$CONNECTION_STR );
			$list = $zk->getChildren( self::$CLOUD );
			if ( $list != null && count( $list ) >= 1 ){
				for ($i = 0; $i < count( $list ); $i++) {
					$ip = $list[$i];
					$str = $zk->get( self::$CLOUD."/".$ip );
					if ( $str == "enabled" ){
						array_push(self::$ADDRESSES, $ip );
					}
				}
			} else {
				throw new Exception( "no open cloud server exists, please contact the server administrator!" );
			}
		}
		$ip = array_pop(self::$ADDRESSES);
		array_push(self::$ADDRESSES, $ip );
		$hostAndPort = split( ':', $ip );
		return $hostAndPort;
	}

}

//$zc = new ZooKeeperAdapter();
//$zc->getServerAddress();
$a = ZooKeeperAdapter::getServerAddress();
print $a[0].":".$a[1];

//print ZooKeeperAdapter::$ADDRESSES[0];
