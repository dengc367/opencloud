package com.renren.api.dao;


import com.renren.api.model.Cluster;


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
public interface ClusterDAO {
	
	public static final String table = " cluster ";
	public static final String insert_fields = " name, node_number, info "; 
	public static final String select_fields = "id, " + insert_fields ;
	
	/**
	 */
	@ReturnGeneratedKeys
	@SQL("insert into " + table  + " ( " + insert_fields + " ) values( :model.name, :model.nodeNumber, :model.info )")
	public int insert(@SQLParam("model") Cluster cluster);

	@SQL( "select " + select_fields + " from " + table + " where id = :id ")
	public Cluster getClusterById(@SQLParam("id") int id);
	

	
	
}
