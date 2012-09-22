package com.renren.api.dao;


import com.renren.api.model.Service;


import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;


/**
 * 
 * @author Zhancheng.deng
 * @since 2012.01.05 14:35
 * 
 */
@DAO
public interface ServiceDAO {
	
	public static final String table = " service ";
	public static final String insert_fields = " cluster_id, port, type, permission "; 
	public static final String select_fields = "id, " + insert_fields ;
	
	/**
	 */
	@ReturnGeneratedKeys
	@SQL("insert into " + table  + " ( " + insert_fields + " ) values( :model.clusterId, :model.port, :model.type, :model.permission )")
	public int insert(@SQLParam("model") Service service);

	@SQL(" select " + select_fields + " from " + table + " where id = :id ")
	public Service getServiceById(@SQLParam("id") int serviceId);
	

	
	
}
