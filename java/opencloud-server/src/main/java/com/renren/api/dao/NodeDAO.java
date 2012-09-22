package com.renren.api.dao;


import java.util.List;

import com.renren.api.model.Node;


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
public interface NodeDAO {
	
	public static final String table = " node ";
	public static final String insert_fields = " cluster_id, name, ip, service_number "; 
	public static final String select_fields = "id, " + insert_fields ;
	
	/**
	 */
	@ReturnGeneratedKeys
	@SQL("insert into " + table  + " ( " + insert_fields + " ) values( :model.clusterId, :model.name, :model.ip, :model.serviceNumber )")
	public int insert(@SQLParam("model") Node node);

	@SQL(" select " + select_fields + " from " + table + " where id = :id ")
	public Node getNodeById(@SQLParam("id") int id);

	@SQL(" select " + select_fields + " from " + table + " where cluster_id = :clusterId ")
	public List<Node> getNodeListByClsuterId( @SQLParam("clusterId") int clusterId);
	

	
	
}
