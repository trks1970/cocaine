package com.ioz.cocaine.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.ExitRequest;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;

import com.ioz.cocaine.scheduled.CentralScheduler;

@ShellComponent
public class QuitCommand implements Quit.Command
{	
	@Autowired
	private CentralScheduler scheduler;
	
	@ShellMethod( "Quit Cocaine" )
	public void quit()
	{
		scheduler.stopAll();
		throw new ExitRequest();
	}
}