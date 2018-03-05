package com.kl.packet;

import com.kl.utils.MJByteBufHelper;

import io.netty.buffer.ByteBuf;

public class MJMessage 
{
	public MJMessageHead mHead;
	public byte[] mContent;
	
	public MJMessage()
	{
		mHead = new MJMessageHead();
		mContent = new byte[MJMessageHead.Size()];
	}
	
	public MJMessage(MJMessageHead head, byte[] content)
	{
		mHead = head;
		mContent = content;
	}
	
	public ByteBuf GetByteBuf()
	{
		ByteBuf buf = MJByteBufHelper.WriteHead(mHead);		
		if (mContent != null && mContent.length > 0)
		{
			buf.writeBytes(mContent);
		}
		return buf;
	}
	
	public void Encrypt()
	{
		// to do
	}
	
}
