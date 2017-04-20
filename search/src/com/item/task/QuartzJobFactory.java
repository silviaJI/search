package com.item.task;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.base.task.ScheduleJob;

/**
 * 定时任务运行工厂类
 */
@DisallowConcurrentExecution
public class QuartzJobFactory implements Job {
	
	
	
	private static final Logger logger = Logger.getLogger(QuartzJobFactory.class);
	
    public void execute(JobExecutionContext context) throws JobExecutionException {
    	ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
    	if(scheduleJob.getJobId().equals("10001")){
    		
    	}
    }
}