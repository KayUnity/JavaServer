package com.kl.dao.bean;

import com.kl.dao.MJConnParameterList;
import com.kl.dao.MJConnParameterList.MJConnParameter;
import com.kl.dao.MJConnRecordObject;
import com.kl.dao.MJDaoCacheManager;

public class MJAccountObject extends MJConnRecordObject
{
	// must register
	static 
	{
		MJConnParameterList pLst = new MJConnParameterList();
		MJConnParameter parameter = pLst.new MJConnParameter();
		parameter.mType = MJConnParameterList.MJConnParameterType.P_INT;
		parameter.mSortId = 1;
		pLst.AddParam(parameter);
		parameter = pLst.new MJConnParameter();
		parameter.mType = MJConnParameterList.MJConnParameterType.P_STRING;
		parameter.mSortId = 2;
		pLst.AddParam(parameter);
		MJDaoCacheManager.RegisterParamLst(MJAccountObject.class, pLst);
	}

	public MJAccountObject()
	{
		
	}
	
	@Override
	protected void ParseColumn(MJConnParameter param) 
	{
		switch (param.mSortId)
		{
		case 1:
			mAge = param.toInt();
			break;
		case 2:
			mName = param.toString();
			break;
		}
	}
	
	@Override
	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("MJDaoTest ( name: ").append(mName).append(" age: ").append(mAge).append(" )");
		return builder.toString();
	}
	
	private int mAge;
	private String mName;
}
