package com.kl.utils;

/**
 * 泛型
 * @author kay.yang
 *
 * @param <T1>
 * @param <T2>
 */
public class MJPair<T1, T2>
{
	private T1 mFirst;
	private T2 mSecond;
	
	public MJPair(T1 first, T2 second)
	{
		first(first);
		second(second);
	}

	public T1 first()
	{
		return mFirst;
	}

	public void first(T1 first)
	{
		this.mFirst = first;
	}

	public T2 second()
	{
		return mSecond;
	}

	public void second(T2 second)
	{
		this.mSecond = second;
	}
}