package com.kl.main;

import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import com.kl.cmdline.CmdlineServices;
import com.kl.command.MJCommandManager;
import com.kl.dao.MJConnOperator;
import com.kl.dao.MJConnOperator.ConnResultList;
import com.kl.dao.MJConnRecordObject;
import com.kl.dao.MJConnResultSet;
import com.kl.dao.MJDaoCacheManager;
import com.kl.dao.MJDaoPoolManager;
import com.kl.file.MJPathConfig;
import com.kl.http.MJHttpServer;
import com.kl.server.MJServerManager;

public class MJMain 
{
	public static void main(String[] argvs) throws Exception
	{
		String config = "";
		if (argvs.length < 1)
			config = "res/base_config.properties";
		else
			config = argvs[0];
		MJPathConfig.initConfig(config);
		PropertyConfigurator.configure(MJPathConfig.getValue("log"));
		if (MJPathConfig.getValue("openDb") == "1")
		{
			MJDaoPoolManager.instance.Initialize(MJPathConfig.getValue("bonecp_config"));		
			MJDaoCacheManager.Initialize();
		}
		MJHttpServer.SetResourceDir(MJPathConfig.getValue("http_res"));
		MJCommandManager.initCommandManager(MJPathConfig.getValue("command"));
		MJServerManager.instance.startServer(MJPathConfig.getValue("ip_port"));
		CmdlineServices.instance().startCmdline();
	}
	
	static void Test()
	{
		ConnResultList set = MJConnOperator.instance.Execute(1, null);
		if (set.mFlag)
		{
			List<MJConnResultSet> temps = MJConnOperator.instance.ParseFrom(1, set);		
			for (MJConnResultSet set2 : temps)
			{
				for (MJConnRecordObject obj : set2.GetObjects())
				{
					System.out.println(obj);	
				}
			}
		}
	}
}
