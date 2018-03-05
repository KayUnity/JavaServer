package com.kl.socket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MJSocketChannelInitializer extends ChannelInitializer<SocketChannel>
{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception 
	{
		ChannelPipeline pipeline = ch.pipeline();
		// 断包粘包处理
		pipeline.addLast(new MJSocketByteSplitDecoder());
		// 收包解密处理
		pipeline.addLast(new MJSocketByteDecryptDecoder());
		// Message对象转字节流
		pipeline.addLast(new MJSocketMessageToByteEncoder());
		// 加密
		pipeline.addLast(new MJSocketByteEncryptEncoder());
		// 收包handle处理器
		pipeline.addLast(new MJSocketChannelHandler());
	}

}
