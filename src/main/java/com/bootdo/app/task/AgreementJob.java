package com.bootdo.app.task;


import org.quartz.Job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AgreementJob implements Job {
    @Override
    public void execute(JobExecutionContext executionContext) throws JobExecutionException {

        System.out.println("123123开始执行："+ System.currentTimeMillis());

    }
}
