package com.bootdo.common.task;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.bootdo.oa.domain.Response;

import java.security.Principal;

@Component
public class WelcomeJob implements Job{
	@Autowired
	SimpMessagingTemplate template;

    @Override
    public void execute(JobExecutionContext executionContext) throws JobExecutionException {

        JobDetail jobDetail = executionContext.getJobDetail();
        JobDataMap map = jobDetail.getJobDataMap();
        System.out.println("*************:"+map);
        System.out.println("开始执行："+ System.currentTimeMillis());
    	template.convertAndSend("/topic/getResponse", new Response("欢迎体验bootdo,这是一个任务计划，使用了websocket和quzrtz技术，可以在计划列表中取消，欢迎您加入qq群交流学习!" ));

    }



}