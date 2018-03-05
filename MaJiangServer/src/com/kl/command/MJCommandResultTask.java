package com.kl.command;

import java.util.concurrent.FutureTask;

public class MJCommandResultTask extends FutureTask<Boolean>
{
	@SuppressWarnings("unused")
	private MJAbstractCommand mCommand;
	private long mStart = 0;
	public MJCommandResultTask(MJAbstractCommand callable) 
	{
		super(callable); 
		mCommand = callable;
		mStart = System.currentTimeMillis();
	}
	
	@Override
	protected void done()
	{
		System.out.println("task time cost: " + (System.currentTimeMillis() - mStart));
        if (this.isCancelled()) 
        {
            System.out.println("cancelled ");
        }
        else
        {
        	
        } 
	}
}
