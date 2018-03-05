package com.kl.cmdline;


/**
 * 获得所有的可用  命令，方便查看
 * @author kay.yang
 *
 */
public class TotalCommandLine extends CmdlineCommand
{

	public TotalCommandLine() {
		super(CmdlineType.TOTLE, "显示所有 命令行 函数");
	}

	@Override
	protected void executeBefore() throws Exception 
	{
		
	}

	@Override
	protected void executeRunning() throws Exception 
	{
		CmdlineServices.instance().print();
	}

	@Override
	protected void executeAfter() throws Exception 
	{
		
	}
	
}
