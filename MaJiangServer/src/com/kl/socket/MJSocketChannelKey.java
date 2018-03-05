package com.kl.socket;

import com.kl.packet.MJMessageHead;

import io.netty.buffer.ByteBuf;
import io.netty.util.AttributeKey;

public class MJSocketChannelKey 
{
	public static AttributeKey<MJSocketSession> SESSION = AttributeKey.newInstance(MJSocketSession.class.toString());
	public static AttributeKey<ByteBuf> SESSIONBUF =  AttributeKey.newInstance(ByteBuf.class.toString());
	public static AttributeKey<MJMessageHead> HEAD =  AttributeKey.newInstance(MJMessageHead.class.toString());
	public static AttributeKey<Boolean> IsSharedHeadFilled = AttributeKey.newInstance("IsSharedHeadFilled");

}
