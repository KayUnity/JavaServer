package com.kl.command.impl;

import com.kl.command.MJAbstractCommand;
import com.kl.command.MJCommandAnnotion;
import com.kl.packet.MJMessage;
import com.kl.packet.MJMessageType;
import com.kl.socket.MJSocketSession;

@MJCommandAnnotion(code = MJMessageType.HEARTBEAT_RES, desc = "心跳包")
public class MJHeartBeatCommand extends MJAbstractCommand
{
	public MJHeartBeatCommand()
	{
		
	}
	
	public MJHeartBeatCommand(MJSocketSession session, MJMessage message)
	{
		super(session, message);
	}
	
	@Override
	public void Execute() 
	{
		mOut.add(mMessage);
	}
	
}
