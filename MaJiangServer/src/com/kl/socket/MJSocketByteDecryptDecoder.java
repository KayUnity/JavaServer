package com.kl.socket;

import java.util.List;

import com.kl.packet.MJMessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class MJSocketByteDecryptDecoder extends MessageToMessageDecoder<MJMessage>
{
	@Override
	protected void decode(ChannelHandlerContext ctx, MJMessage msg, List<Object> out) throws Exception 
	{
		out.add(msg);
	}
	
}
