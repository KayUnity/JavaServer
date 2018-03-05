package com.kl.server;

import java.util.ArrayList;
import java.util.List;

import com.kl.http.MJHttpServer;
import com.kl.socket.MJSocketServer;
import com.kl.utils.MJThreePair;
import com.kl.websocket.MJWebsocketServer;

public class MJServerManager
{
	public static MJServerManager instance = new MJServerManager();
	
	private List<MJThreePair<Integer, String, Integer>> mAddresses;
	private List<MJServer> mServerList;
	private MJServerManager()
	{
		mAddresses = new ArrayList<MJThreePair<Integer, String, Integer>>();
		mServerList = new ArrayList<>();
	}
	
	public void startServer(String ip_port) throws Exception
	{
		String[] strs = ip_port.replaceAll(" ", "").split(",");
		for (String str : strs) 
		{
			String[] ipport = str.split(":");
			if (ipport.length != 3)
			{
				continue;
			}
			AddServerAddr(Integer.valueOf(ipport[0]), ipport[1], Integer.valueOf(ipport[2]));
		}
		Run();
	}
	
	private void AddServerAddr(Integer id, String ip, int port)
	{
		mAddresses.add(new MJThreePair<Integer, String, Integer>(id, ip, port));
	}
	
	private void Run() throws Exception
	{
		MJServer server;
		for (MJThreePair<Integer, String, Integer> pair : mAddresses) 
		{
			server = StartServer(pair);
			server.Start();
			mServerList.add(server);
		}
	}
	
	public void Stop()
	{
		for (MJServer server : mServerList)
		{
			server.Stop();
		}
	}
	
	private MJServer StartServer(MJThreePair<Integer, String, Integer> pair) throws Exception
	{
		if (pair.first() == 0)
		{
			return new MJSocketServer(pair.second(), pair.three());
		}
		else if (pair.first() == 1)
		{
			return new MJHttpServer(pair.second(), pair.three());
		}
		else if (pair.first() == 2)
		{
			return new MJWebsocketServer(pair.second(), pair.three());
		}
		else
		{
			throw new Exception("No Support Server Type: " + pair.first() );
		
		}
	}
	
}
