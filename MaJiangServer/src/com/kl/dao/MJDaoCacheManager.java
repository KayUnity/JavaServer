package com.kl.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.kl.dao.MJConnOperator.ConnResult;
import com.kl.dao.MJConnOperator.ConnResultList;
import com.kl.dao.bean.MJSqlViewObject;

/*
 * 
 * 1 t_object 表
 * 		int     objId 唯一索引
 * 		string  className
 * 
 * 2 t_object_filed 表
 * 		int     id 
 * 		int     objId  foreign key  t_object
 * 		int 	sortId  表字段的index
 * 		int		type    字段类型
 * 
 * 3 t_sql （所需参数，客户端传递）
 * 		int   sqlId  primary key
 * 		string  objIds(foreign, 以','分割)
 * 		string sql_statement
 * 
 * 4 t_sql_param (暂时不需要配置表，使用 pb 文件配置即可)
 * 		int id
 * 		int sqlId(foreign key)
 * 		int paramType
 * 		int paramIndex
 * 		string value
 * */

public class MJDaoCacheManager 
{
	static Map<String, MJConnParameterList> mParamCache = new HashMap<>();
	static Map<Integer, MJSqlViewObject> mSqlCache = new HashMap<>();
	
	public static void RegisterParamLst(Class<? extends MJConnRecordObject> clazz, MJConnParameterList lst)
	{
		mParamCache.put(clazz.toString(), lst);
	}
	
	public static MJConnParameterList GetParamLst(Class<? extends MJConnRecordObject> clazz)
	{
		return mParamCache.get(clazz.toString());
	}
	
	public static void Initialize()
	{
		try
		{
			ConnResultList result = MJConnOperator.instance.new ConnResultList();
			Connection connection = MJDaoPoolManager.instance.AquireConnection();
			ConnResult flag = MJConnOperator.instance.Execute(connection, "select sql_id, object_ids, sql_statement, sql_type from t_sql_view;", MJConnOperator.ConnOperatorType.T_QUERY, null, result);
			if (flag.mFlag)
			{
				MJConnResultSet resultSet = MJConnOperator.instance.CreateTableObject(result.Get(0), MJSqlViewObject.class);
				ArrayList<MJConnRecordObject> objects = resultSet.GetObjects();
				for (MJConnRecordObject obj : objects)
				{
					System.out.println(obj);	
					MJSqlViewObject sqlObject = (MJSqlViewObject)obj;
					mSqlCache.put(sqlObject.mSqlId, sqlObject);
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static MJSqlViewObject Get(int sqlId)
	{
		return mSqlCache.get(sqlId);
	}
}
