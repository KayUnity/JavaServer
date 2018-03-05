package com.kl.command;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import com.kl.packet.MJMessage;
import com.kl.socket.MJSocketSession;

public abstract class MJAbstractCommand implements Callable<Boolean> 
{	
	protected MJSocketSession mSession;
	protected MJMessage mMessage;
	protected List<MJMessage> mOut;
	// private long _start;
	public MJAbstractCommand()
	{
		mOut = new ArrayList<MJMessage>();
	}
	
	public MJAbstractCommand(MJSocketSession session, MJMessage message)
	{
		this();
		mSession = session;
		mMessage = message;
		// _start = System.currentTimeMillis();
	}
	
	public MJSocketSession Session()
	{
		return mSession;
	}
	
	public abstract void Execute();

	@Override
	public Boolean call() throws Exception
	{
		 Execute();
		 for (MJMessage msg : mOut) 
		 {
			 mSession.WriteMessage(msg, null);
		 }
		 mOut.clear();
		 // System.out.println("task cost: " + (System.currentTimeMillis() - _start));
		 return true;
	}
}
