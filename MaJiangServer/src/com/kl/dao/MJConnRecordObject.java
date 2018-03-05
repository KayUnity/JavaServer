package com.kl.dao;

import com.kl.dao.MJConnParameterList.MJConnParameter;

public abstract class MJConnRecordObject
{	
	public MJConnRecordObject()
	{
		
	}
	
	// implement by derived class
	public void InitializeByParameter(MJConnParameterList recordLst)
	{
		int size = recordLst.Size();
		for (int i = 0; i < size; ++i)
		{
			MJConnParameter parameter = recordLst.GetAt(i);
			ParseColumn(parameter);
		}
	}
	
	protected abstract void ParseColumn(MJConnParameter param);	
	

}
