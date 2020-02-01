package com.epoint.mealorder.util;

import com.alibaba.druid.pool.DruidDataSource;

public class DataSourceUtil {
    private static DruidDataSource ds;
    
    public static DruidDataSource getDruidDataSource() {
    	if(ds==null) {
    		ds=new DruidDataSource();
    		ds.setDriverClassName(ConfigUtil.getJDBCConfigValue("driver"));
    		ds.setUrl(ConfigUtil.getJDBCConfigValue("url"));
    		ds.setUsername(ConfigUtil.getJDBCConfigValue("user"));
    		ds.setPassword(ConfigUtil.getJDBCConfigValue("password"));		
    	}
    	return ds;
    }
	
	
	
}
