package com.kl.socket;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kl.command.MJCommandManager;
import com.kl.logic.MJSessionManager;
import com.kl.packet.MJMessage;
import com.kl.packet.MJMessageHead;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MJSocketChannelHandler extends SimpleChannelInboundHandler<MJMessage>
{
	private static Logger mLogger = LoggerFactory.getLogger(MJSocketChannelHandler.class);
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MJMessage msg) throws Exception 
	{
		MJSocketSession session = ctx.channel().attr(MJSocketChannelKey.SESSION).get();
		MJCommandManager.instance.ExecuteMessage(session, msg);
	}
	
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
    	MJSocketSession session = new MJSocketSession();
    	MJSessionManager.manager.AddSession(session);
    	
    	ByteBuf buf = PooledByteBufAllocator.DEFAULT.directBuffer(MJMessageHead.Size());
    	MJMessageHead head = new MJMessageHead();
        session.Channel(ctx.channel());
        
        ctx.channel().attr(MJSocketChannelKey.SESSION).set(session);
        ctx.channel().attr(MJSocketChannelKey.SESSIONBUF).set(buf);
        ctx.channel().attr(MJSocketChannelKey.HEAD).set(head);
        ctx.channel().attr(MJSocketChannelKey.IsSharedHeadFilled).set(false);
        
        InetSocketAddress saddr =  (InetSocketAddress)ctx.channel().remoteAddress();
		session.IP(saddr.getHostString());
		session.port(saddr.getPort());
		System.out.println("connected: " + session.IP() + " " + session.port());
		mLogger.info("connected: " + session.IP() + " " + session.port());
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception
    {
    	MJSocketSession session = ctx.channel().attr(MJSocketChannelKey.SESSION).get();
    	ctx.channel().attr(MJSocketChannelKey.SESSIONBUF).get().release();
    	MJSessionManager.manager.Remove(session.id());
    	System.out.println("disconnected: " + session.IP() + " " + session.port());
    	mLogger.info("disconnected: " + session.IP() + " " + session.port());
    	ctx.close(); 
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
    	System.out.println("exception: " + cause.getMessage());
    	ctx.close();
    }
}
