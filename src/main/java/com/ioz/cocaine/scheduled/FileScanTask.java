package com.ioz.cocaine.scheduled;

import static com.ioz.cocaine.file.monitor.event.FileOperation.CREATED;
import static com.ioz.cocaine.file.monitor.event.FileOperation.DELETED;
import static com.ioz.cocaine.file.monitor.event.FileOperation.MONITORING_FINISH;
import static com.ioz.cocaine.file.monitor.event.FileOperation.MONITORING_START;
import static com.ioz.cocaine.file.monitor.event.FileOperation.RENAMED;
import static com.ioz.cocaine.file.monitor.event.FileOperation.UPDATED;

import java.io.File;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ioz.cocaine.file.monitor.DirectoryMonitoringRequest;
import com.ioz.cocaine.file.monitor.event.FileOperation;
import com.ioz.cocaine.file.monitor.polling.PollingFileMonitorService;

@Component
public class FileScanTask implements Runnable, InitializingBean
{
	private static final FileOperation[] MONITORED_OPERATIONS = { MONITORING_START, MONITORING_FINISH, CREATED, RENAMED,
			UPDATED, DELETED };
	
	@Autowired
	private PollingFileMonitorService service;
	
	@Autowired
	private JavaFileListener listener;

	public FileScanTask()
	{

	}

	@Override
	public void run()
	{
		service.scanAll();

	}

	@Override
	public void afterPropertiesSet() throws Exception
	{
		DirectoryMonitoringRequest request = new DirectoryMonitoringRequest( new File( "tst" ), true, MONITORED_OPERATIONS );
		service.add( listener );
		service.add( request );
	}

}
