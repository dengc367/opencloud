package com.renren.api.dao;


import com.renren.api.model.App;


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
public interface AppDAO {
	
	public static final String table = " app ";
	public static final String insert_fields = " app_key, name, uid, run_env, create_time, options "; 
	public static final String select_fields = "id, " + insert_fields ;
	
	/**
	 */
	@ReturnGeneratedKeys
	@SQL("insert into " + table  + " ( " + insert_fields + " ) values( :model.appKey, :model.name, :model.name, :model.uid, :model.runEnv, :model.createTime, :model.options )")
	public int insert(@SQLParam("model") App app);

	@SQL(" select " + select_fields + " from " + table + " where app_key = :appKey")
	public App getByAppKey(@SQLParam("appKey") String appKey);
	

	
	
}
