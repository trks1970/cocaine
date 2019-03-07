package com.ioz.cocaine.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
@Component
public class CentralScheduler
{
	@Autowired
	private ThreadPoolTaskScheduler scheduler;

	@Bean
	public ThreadPoolTaskScheduler taskScheduler()
	{
		return new ThreadPoolTaskScheduler();
	}

	public void start( Runnable task, String scheduleExpression ) throws Exception
	{
		scheduler.schedule( task, new CronTrigger( scheduleExpression ) );
	}

	public void start( Runnable task, Long delay ) throws Exception
	{
		scheduler.scheduleWithFixedDelay( task, delay );
	}

	public void stopAll()
	{
		scheduler.shutdown();
		//CONTEXT.close();
	}
}
