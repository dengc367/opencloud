#!/usr/bin/env php
<?php


$GLOBALS['THRIFT_ROOT'] = dirname(__FILE__).'/lib/php/src';

if (!isset($GEN_DIR)) {
	$GEN_DIR = $GLOBALS['THRIFT_ROOT'].'/packages';
}


require_once $GLOBALS['THRIFT_ROOT'].'/Thrift.php';
require_once $GLOBALS['THRIFT_ROOT'].'/protocol/TCompactProtocol.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TSocket.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TSocketPool.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/THttpClient.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TBufferedTransport.php';

#error_reporting(E_NONE);
require_once $GEN_DIR.'/opencloud/opencloud_types.php';
require_once $GEN_DIR.'/opencloud/ConnectionService.php';


require_once dirname(__FILE__).'/ClientAdapter.php';
#error_reporting(E_ALL);

class ThriftClient{
	
	function main(){
		
		try {
			$hostAndPort = ClientAdapter::getServerInfo();
			$appKey = ClientAdapter::getAppKey();
			$host = $hostAndPort[0];
			$port = $hostAndPort[1];
			$hosts = array($host);
			$socket = new TSocket($host, $port);
			$socket = new TSocketPool($hosts, $port);
			$socket->setDebug(TRUE);
		
			$transport = new TBufferedTransport($socket, 1024, 1024);
			$protocol = new TCompactProtocol($transport);
			$client = new ConnectionServiceClient($protocol, $protocol);
			$transport->open();
		
			$conInfo = $client->getConnection($appKey, ClientAdapter::$MEMCACHE);
		
			print_r( 'username='.$conInfo[0]-> address, true);
			print 'port='.$conInfo[0]-> port;
			
			
			$transport->close();
		} catch ( OpenCloudException $e ) {
			print "opencloudexception";
		} catch (TException $tx) {
			print 'xxx TException: '.$tx->getMessage()."\n";
		}
	
	}
	
}

$client = new ThriftClient();
$client->main();
