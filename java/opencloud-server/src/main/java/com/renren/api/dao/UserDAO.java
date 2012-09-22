package com.renren.api.dao;


import com.renren.api.model.User;


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
public interface UserDAO {
	
	public static final String table = " user ";
	public static final String insert_fields = " uid, uname, password, email, code, mobile_phone, telephone, gender, create_time "; 
	public static final String select_fields = "id, " + insert_fields ;
	
	/**
	 */
	@ReturnGeneratedKeys
	@SQL("insert into " + table  + " ( " + insert_fields + " ) values( :model.uid, :model.uname, :model.password, :model.email, :model.code, :model.mobile_phone, :model.telephone, :model.gender, :model.createTime )")
	public int insert(@SQLParam("model") User user);
	

	
	
}
