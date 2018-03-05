package com.kl.socket;

import java.util.List;

import com.kl.packet.MJMessage;

import io.netty.channel.ChannelHandlerContext;

import io.netty.handler.codec.MessageToMessageEncoder;

public class MJSocketByteEncryptEncoder extends MessageToMessageEncoder<MJMessage>{

	@Override
	protected void encode(ChannelHandlerContext ctx, MJMessage msg, List<Object> out) throws Exception 
	{
		msg.Encrypt();
		out.add(msg);
	}

}
