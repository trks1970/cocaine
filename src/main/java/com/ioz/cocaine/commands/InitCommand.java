package com.ioz.cocaine.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Command;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.ioz.cocaine.scheduled.CentralScheduler;
import com.ioz.cocaine.scheduled.FileScanTask;

@ShellComponent
public class InitCommand implements Command
{	
	@Autowired
	private CentralScheduler scheduler;
	
	@Autowired
	private FileScanTask fileScanTask;
	
	@ShellMethod("Initialize Cocaine")
	public void init() throws Exception
	{
		scheduler.start( fileScanTask, 5000L );
	}
}
