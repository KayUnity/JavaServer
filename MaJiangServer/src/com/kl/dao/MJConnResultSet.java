package com.kl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kl.dao.MJConnParameterList.MJConnParameter;

public class MJConnResultSet
{
	private MJConnParameterList mResultParameterList;
	private ArrayList<MJConnRecordObject> mRecords;
	private Class<? extends MJConnRecordObject> mClazz;
	
	public MJConnResultSet(Class<? extends MJConnRecordObject> clazz)
	{
		mClazz = clazz;
		mResultParameterList = MJDaoCacheManager.GetParamLst(clazz);
		mRecords = new ArrayList<>();
	}
	
	public void HandleResult(ResultSet set)
	{
		try 
		{
			if (mResultParameterList == null)
			{
				mClazz.newInstance();
				mResultParameterList = MJDaoCacheManager.GetParamLst(mClazz);
			}
			while (set.next())
			{
				MJConnRecordObject t = mClazz.newInstance();
				Parse(set, t);
				mRecords.add(t);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<MJConnRecordObject> GetObjects()
	{
		return mRecords;
	}
	
	private void Parse(ResultSet resultSet, MJConnRecordObject t) throws SQLException
	{
		int size = mResultParameterList.Size();
		// should have default constructor
		for (int i = 0; i < size; ++i)
		{
			MJConnParameter param = mResultParameterList.GetAt(i);
			param.mValue = resultSet.getString(param.mSortId.intValue());	
		}
		t.InitializeByParameter(mResultParameterList);
	}
}
