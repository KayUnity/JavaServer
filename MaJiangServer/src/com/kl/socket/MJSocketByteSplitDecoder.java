package com.kl.socket;

import java.util.List;

import com.kl.packet.MJMessage;
import com.kl.packet.MJMessageHead;
import com.kl.utils.MJByteBufHelper;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MJSocketByteSplitDecoder  extends ByteToMessageDecoder
{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception 
	{
		ByteBuf remain = ctx.channel().attr(MJSocketChannelKey.SESSIONBUF).get();
		remain.writeBytes(in);
		MJMessageHead head = ctx.channel().attr(MJSocketChannelKey.HEAD).get();
		while (true)
		{
			if (!ctx.channel().attr(MJSocketChannelKey.IsSharedHeadFilled).get())
			{
				boolean success = MJByteBufHelper.ReadHead(remain, head);
				if (!success)
				{
					break;
				}
				ctx.channel().attr(MJSocketChannelKey.IsSharedHeadFilled).set(true);
			}
			if (ctx.channel().attr(MJSocketChannelKey.IsSharedHeadFilled).get())
			{
				if (head.mLength == 0)
				{
					out.add(new MJMessage(head.Clone(), null));
					ctx.channel().attr(MJSocketChannelKey.IsSharedHeadFilled).set(false);
				}
				else if (MJByteBufHelper.CanRead(remain, head.mLength))
				{
					byte[] content = MJByteBufHelper.readBytes(remain, head.mLength);
					out.add(new MJMessage(head.Clone(), content));
					ctx.channel().attr(MJSocketChannelKey.IsSharedHeadFilled).set(false);
				}
				else
				{
					break;
				}
			}
		}
		remain.discardReadBytes();
	}

}
