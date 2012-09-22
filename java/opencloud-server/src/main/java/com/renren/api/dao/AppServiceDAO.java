package com.renren.api.dao;


import java.util.List;

import com.renren.api.model.AppService;


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
public interface AppServiceDAO {
	
	public static final String table = " app_service ";
	public static final String insert_fields = " app_id, service_id "; 
	public static final String select_fields = "id, " + insert_fields ;

	/**
	 * 
	 */
	@ReturnGeneratedKeys
	@SQL("insert into " + table  + " ( " + insert_fields + " ) values( :model.appId, :model.serviceId )")
	public int insert(@SQLParam("model") AppService appService);

	@SQL( " select " + select_fields + " from " + table + " where app_id = :appId ")
	public List<AppService> getAppServiceListByAppId(@SQLParam("appId") int appId);
	
	

	
	
}
