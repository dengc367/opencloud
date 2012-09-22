package com.renren.api.biz;

import net.paoding.rose.scanning.LoadScope;
import net.paoding.rose.scanning.context.RoseAppContext;
import net.paoding.rose.scanning.context.RoseWebAppContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renren.api.dao.AppDAO;
import com.renren.api.dao.AppServiceDAO;
import com.renren.api.dao.ClusterDAO;
import com.renren.api.dao.NodeDAO;
import com.renren.api.dao.ServiceDAO;
import com.renren.api.dao.UserDAO;

@Service
public abstract class BaseBiz {

//	LoadScope scope = new LoadScope( "", "applicaitonContext");
//	RoseAppContext context = new RoseAppContext();
	
	@Autowired protected AppDAO appDao;// = context.getBean(AppDAO.class);
	@Autowired protected AppServiceDAO appServiceDao;// = context.getBean(AppServiceDAO.class);
	@Autowired protected ClusterDAO clusterDao;// = context.getBean(ClusterDAO.class);
	@Autowired protected NodeDAO nodeDao;// = context.getBean(NodeDAO.class);
	@Autowired protected ServiceDAO serviceDao;// = context.getBean(ServiceDAO.class);
	@Autowired protected UserDAO userDao;// = context.getBean(UserDAO.class);
}
