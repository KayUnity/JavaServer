package com.kl.socket;

import com.kl.packet.MJMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MJSocketMessageToByteEncoder extends MessageToByteEncoder<MJMessage>
{
	@Override
	protected void encode(ChannelHandlerContext ctx, MJMessage msg, ByteBuf out) throws Exception 
	{
		out = msg.GetByteBuf();
		ctx.writeAndFlush(out);
		out.release();
	}

}
