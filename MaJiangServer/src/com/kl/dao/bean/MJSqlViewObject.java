package com.kl.dao.bean;

import com.kl.dao.MJConnParameterList.MJConnParameter;
import com.kl.dao.MJConnOperator.ConnOperatorType;
import com.kl.dao.MJConnParameterList;
import com.kl.dao.MJConnRecordObject;
import com.kl.dao.MJDaoCacheManager;

public class MJSqlViewObject extends MJConnRecordObject
{
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
		 parameter = pLst.new MJConnParameter();
		 parameter.mType = MJConnParameterList.MJConnParameterType.P_STRING;
		 parameter.mSortId = 3;
		 pLst.AddParam(parameter);
		 parameter = pLst.new MJConnParameter();
		 parameter.mType = MJConnParameterList.MJConnParameterType.P_INT;
		 parameter.mSortId = 4;
		 pLst.AddParam(parameter);
		 MJDaoCacheManager.RegisterParamLst(MJSqlViewObject.class, pLst);
	 }
	
	@Override
	protected void ParseColumn(MJConnParameter param) 
	{
		switch (param.mSortId)
		{
		case 1:
			mSqlId = param.toInt();
			break;
		case 2:
			mObjectIds = param.toString();
			break;
		case 3:
			mSqlStatement = param.toString();
			break;
		case 4:
			mOperatorType = GetType(param.toInt());
			break;
		}
	}
	
	///
	///T_INSERT(1),
	///T_DELETE(2),
	///T_UPDATE(3),
	///T_QUERY(4);
	static ConnOperatorType GetType(int type)
	{
		switch(type)
		{
		case 1:
			return ConnOperatorType.T_INSERT;
		case 2:
			return ConnOperatorType.T_DELETE;
		case 3:
			return ConnOperatorType.T_UPDATE;
		case 4:
			return ConnOperatorType.T_QUERY;
		}
		return ConnOperatorType.T_NONE;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MJSqlViewObject ( _sqlId: ").append(mSqlId).append(" _objectIds: ").append(mObjectIds).append(" _sql: ").append(mSqlStatement).append(" _type: ").append(mOperatorType).append(" )");
		return builder.toString();
	}
	
	public int mSqlId;
	public String mObjectIds;
	public String mSqlStatement;
	public ConnOperatorType mOperatorType;
}
