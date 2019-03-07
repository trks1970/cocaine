package com.ioz.cocaine.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ScanStarter
{
	@Autowired
	private CentralScheduler scheduler;
	
	@Autowired
	private FileScanTask fileScanTask;

	@EventListener(ContextRefreshedEvent.class )
	public void onContextRefreshed() throws Exception
	{
		scheduler.start( fileScanTask, 5000L );
	}
}
