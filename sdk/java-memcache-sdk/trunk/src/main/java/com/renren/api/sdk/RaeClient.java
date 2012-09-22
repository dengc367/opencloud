package com.renren.api.sdk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.ConnectionObserver;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.NodeLocator;
import net.spy.memcached.OperationTimeoutException;
import net.spy.memcached.internal.BulkFuture;
import net.spy.memcached.transcoders.Transcoder;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.renren.api.thrift.ConnectionInfo;
import com.renren.api.thrift.ConnectionService;

public class RaeClient {
	public MemcachedClient memClient;

	public String APP_PRE = "$@_";
	public String APP_EN = "_$#_";
	public String KEYPREF = "";
	public String app_key = "";
	public static String THRISERVERIP = "10.32.16.81";

	public RaeClient(String app_key) throws TException, NumberFormatException,
			IOException {
		this.app_key = app_key;
		this.KEYPREF = APP_PRE + this.app_key + APP_EN;
		List<ConnectionInfo> conns = getConnections(this.app_key);
		
		memClient = new MemcachedClient(new InetSocketAddress(conns.get(0).getAddress(), Integer.valueOf(conns.get(0).getPort())));

		//memClient = new MemcachedClient(new InetSocketAddress("10.32.16.90",
        //				11211));

	}

	private List<ConnectionInfo> getConnections(String app_key)
			throws TException {
		TTransport transport = new TSocket(THRISERVERIP, 7911);
		System.out.println("Cnnect to Thrift Server " + THRISERVERIP + " port "
				+ 7911);
		TProtocol protocol = new TCompactProtocol(transport);
		ConnectionService.Client client = new ConnectionService.Client(protocol);
		transport.open();
		List<ConnectionInfo> infos = client
				.getConnection(app_key, "memcache" /* service type */);
		transport.close();
		return infos;
	}

	public Future<Boolean> add(String key, int exp, Object o) {
		return memClient.add(key, exp, o);
	}

	public <T> Future<Boolean> add(String key, int exp, T o, Transcoder<T> tc) {
		return memClient.add(key, exp, o, tc);
	}

	public boolean addObserver(ConnectionObserver obs) {
		return memClient.addObserver(obs);
	}

	public Future<Boolean> append(long cas, String key, Object val) {
		return memClient.append(cas, key, val);
	}

	public <T> Future<Boolean> append(long cas, String key, T val,
			Transcoder<T> tc) {
		return memClient.append(cas, key, val, tc);
	}

	public Future<CASResponse> asyncCAS(String key, long casId, Object value) {
		return memClient.asyncCAS(key, casId, value);
	}

	public <T> Future<CASResponse> asyncCAS(String key, long casId, T tc,
			Transcoder<T> arg3) {
		return memClient.asyncCAS(key, casId, tc, arg3);
	}

	public Future<Long> asyncDecr(String key, int by) {
		return memClient.asyncDecr(key, by);
	}

	public Future<Object> asyncGet(String arg0) {
		return null;
	}

	public <T> Future<T> asyncGet(String arg0, Transcoder<T> arg1) {
		return null;
	}

	public Future<CASValue<Object>> asyncGetAndLock(String arg0, int arg1) {
		return null;
	}

	public <T> Future<CASValue<T>> asyncGetAndLock(String arg0, int arg1,
			Transcoder<T> arg2) {
		return null;
	}

	public Future<CASValue<Object>> asyncGetAndTouch(String arg0, int arg1) {
		return null;
	}

	public <T> Future<CASValue<T>> asyncGetAndTouch(String arg0, int arg1,
			Transcoder<T> arg2) {
		return null;
	}

	public BulkFuture<Map<String, Object>> asyncGetBulk(Collection<String> arg0) {

		return null;
	}

	public BulkFuture<Map<String, Object>> asyncGetBulk(String... arg0) {

		return null;
	}

	public <T> BulkFuture<Map<String, T>> asyncGetBulk(Collection<String> arg0,
			Iterator<Transcoder<T>> arg1) {

		return null;
	}

	public <T> BulkFuture<Map<String, T>> asyncGetBulk(Collection<String> arg0,
			Transcoder<T> arg1) {

		return null;
	}

	public <T> BulkFuture<Map<String, T>> asyncGetBulk(Transcoder<T> arg0,
			String... arg1) {

		return null;
	}

	public Future<CASValue<Object>> asyncGets(String arg0) {

		return null;
	}

	public <T> Future<CASValue<T>> asyncGets(String arg0, Transcoder<T> arg1) {

		return null;
	}

	public Future<Long> asyncIncr(String arg0, int arg1) {

		return null;
	}

	public CASResponse cas(String arg0, long arg1, Object arg2)
			throws OperationTimeoutException {

		return null;
	}

	public <T> CASResponse cas(String arg0, long arg1, T arg2,
			Transcoder<T> arg3) throws OperationTimeoutException {

		return null;
	}

	public long decr(String arg0, int arg1) throws OperationTimeoutException {

		return 0;
	}

	public long decr(String arg0, int arg1, long arg2)
			throws OperationTimeoutException {

		return 0;
	}

	public long decr(String arg0, int arg1, long arg2, int arg3)
			throws OperationTimeoutException {

		return 0;
	}

	public Future<Boolean> delete(String key) {
		return memClient.delete(key);
	}

	public Future<Boolean> flush() {
		return null;
	}

	public Future<Boolean> flush(int arg0) {
		return null;
	}

	public Object get(String key) throws OperationTimeoutException {
		return memClient.get(this.KEYPREF+key);
	}

	public <T> T get(String arg0, Transcoder<T> arg1)
			throws OperationTimeoutException {
		return memClient.get(this.KEYPREF+arg0, arg1);
	}

	public CASValue<Object> getAndLock(String key, int exp) {
		return memClient.getAndLock(key, exp);
	}

	public <T> CASValue<T> getAndLock(String arg0, int arg1, Transcoder<T> arg2) {
		return memClient.getAndLock(arg0, arg1, arg2);
	}

	public CASValue<Object> getAndTouch(String arg0, int arg1) {
		return null;
	}

	public <T> CASValue<T> getAndTouch(String arg0, int arg1, Transcoder<T> arg2) {

		return null;
	}

	public Collection<SocketAddress> getAvailableServers() {
		return memClient.getAvailableServers();
	}

	public Map<String, Object> getBulk(Collection<String> keys)
			throws OperationTimeoutException {
		return memClient.getBulk(keys);
	}

	public Map<String, Object> getBulk(String... arg0)
			throws OperationTimeoutException {

		return memClient.getBulk(arg0);
	}

	public <T> Map<String, T> getBulk(Collection<String> arg0,
			Transcoder<T> arg1) throws OperationTimeoutException {

		return memClient.getBulk(arg0, arg1);
	}

	public <T> Map<String, T> getBulk(Transcoder<T> tc, String... keys)
			throws OperationTimeoutException {

		return memClient.getBulk(tc, keys);
	}

	public NodeLocator getNodeLocator() {

		return null;
	}

	public Map<SocketAddress, Map<String, String>> getStats() {

		return null;
	}

	public Map<SocketAddress, Map<String, String>> getStats(String arg0) {

		return null;
	}

	public Transcoder<Object> getTranscoder() {

		return memClient.getTranscoder();
	}

	public Collection<SocketAddress> getUnavailableServers() {

		return null;
	}

	public Map<SocketAddress, String> getVersions() {

		return null;
	}

	public CASValue<Object> gets(String key) throws OperationTimeoutException {
		return memClient.gets(key);
	}

	public <T> CASValue<T> gets(String arg0, Transcoder<T> arg1)
			throws OperationTimeoutException {
		return memClient.gets(arg0, arg1);
	}

	public long incr(String key, int by) throws OperationTimeoutException {
		return memClient.incr(key, by);
	}

	public long incr(String key, int by, long def)
			throws OperationTimeoutException {
		return memClient.incr(key, by, def);
	}

	public long incr(String key, int by, long def, int exp)
			throws OperationTimeoutException {

		return memClient.incr(key, by, def, exp);
	}

	public Set<String> listSaslMechanisms() {

		return memClient.listSaslMechanisms();
	}

	public Future<Boolean> prepend(long cas, String key, Object val) {

		return memClient.prepend(cas, key, val);
	}

	public <T> Future<Boolean> prepend(long cas, String key, T val,
			Transcoder<T> tc) {

		return memClient.prepend(cas, key, val, tc);
	}

	public boolean removeObserver(ConnectionObserver obs) {
		return memClient.removeObserver(obs);
	}

	public Future<Boolean> replace(String key, int exp, Object o) {

		return memClient.replace(key, exp, o);
	}

	public <T> Future<Boolean> replace(String key, int exp, T o,
			Transcoder<T> tc) {

		return memClient.replace(key, exp, o, tc);
	}

	public Future<Boolean> set(String key, int exp, Object o) {
		return memClient.set(this.KEYPREF+ key, exp, o);
	}

	public <T> Future<Boolean> set(String key, int exp, T o, Transcoder<T> tc) {

		return memClient.set(this.KEYPREF+key, exp, o, tc);
	}

	public void shutdown() {

	}

	public boolean shutdown(long arg0, TimeUnit arg1) {

		return false;
	}

	public <T> Future<Boolean> touch(String arg0, int arg1) {

		return null;
	}

	public <T> Future<Boolean> touch(String arg0, int arg1, Transcoder<T> arg2) {

		return null;
	}

	public boolean waitForQueues(long arg0, TimeUnit arg1) {

		return false;
	}

	public static void main(String args[]) {

		try {
			RaeClient rae = new RaeClient("sadfsadgdsfsaxfsdaewrwdfsadf");
			String value = "323r23r223rqwe";
			rae.set("key", 100, "323r23r223rqwe");
			System.out.println("set key from membase cluster : key :  " + value);
	        System.out.println("get key from membase cluster : "+ rae.get("key"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
