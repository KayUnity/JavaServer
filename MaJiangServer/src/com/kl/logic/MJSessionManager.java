package com.kl.logic;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.kl.packet.MJMessage;
import com.kl.socket.MJSocketSession;

public class MJSessionManager 
{
	public static MJSessionManager manager = new MJSessionManager();

	private AtomicInteger mIdGenerator = new AtomicInteger(1);
	private ConcurrentHashMap<Integer, MJSocketSession> mSessions = null;
	
	private MJSessionManager()
	{
		mSessions = new ConcurrentHashMap<Integer, MJSocketSession>();
	}
	
	public ConcurrentHashMap<Integer, MJSocketSession> Sessions() 
	{
		return mSessions;
	}
	
	public void AddSession(MJSocketSession session)
	{
		int id = mIdGenerator.getAndIncrement();
		session.id(id);
		mSessions.put(id, session);
	}
	
	public void Remove(int id)
	{
		MJSocketSession session = mSessions.get(id);
		if (session != null)
		{
			session.Close();
			mSessions.remove(id);
		}
	}
	
	public MJSocketSession GetSession(int id)
	{
		if (mSessions.containsKey(id))
		{
			return mSessions.get(id);
		}
		return null;
	}
	
	public void BroadAll(MJMessage msg)
	{
		BroadAllWithout(msg, -1);
	}
	
	public void BroadAllWithout(MJMessage msg, int id)
	{
		for (MJSocketSession sessionid : mSessions.values())
		{
			if (sessionid.id() != id)
			{
				msg.mHead.mSession = sessionid.id();
				sessionid.WriteMessage(msg, null);
			}
		}
	}
}
