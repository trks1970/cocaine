package com.ioz.cocaine.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FileScanner
{
	@Scheduled( fixedDelay=5000 )
	public void scanDirectory()
	{
		System.out.println( "scheduled" );
	}
}
