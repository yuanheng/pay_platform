package com.bootdo.common.service.impl;

import com.bootdo.app.config.Constants;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.common.config.Constant;
import com.bootdo.common.dao.TaskDao;
import com.bootdo.common.domain.ScheduleJob;
import com.bootdo.common.domain.TaskDO;
import com.bootdo.common.service.JobService;
import com.bootdo.common.utils.ScheduleJobUtils;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class JobServiceImpl implements JobService {

	private static Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);
	@Autowired
	private TaskDao taskScheduleJobMapper;

	@Autowired
	private RedisUtils redisUtils;

	private static Random random = new Random();


	@Override
	public TaskDO get(Long id) {
		return taskScheduleJobMapper.get(id);
	}

	@Override
	public List<TaskDO> list(Map<String, Object> map) {
		return taskScheduleJobMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return taskScheduleJobMapper.count(map);
	}

	@Override
	public int save(TaskDO taskScheduleJob) {
		return taskScheduleJobMapper.save(taskScheduleJob);
	}

	@Override
	public int update(TaskDO taskScheduleJob) {
		return taskScheduleJobMapper.update(taskScheduleJob);
	}

	@Override
	public int remove(Long id) {
		try {
			TaskDO scheduleJob = get(id);
			//quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
			return taskScheduleJobMapper.remove(id);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public int batchRemove(Long[] ids) {
		for (Long id : ids) {
			try {
				TaskDO scheduleJob = get(id);
				//quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
		return taskScheduleJobMapper.batchRemove(ids);
	}

	@Override
	public void initSchedule() throws SchedulerException {
		// 这里获取任务信息数据
		List<TaskDO> jobList = taskScheduleJobMapper.list(new HashMap<String, Object>(16));
		for (TaskDO scheduleJob : jobList) {
			if ("1".equals(scheduleJob.getJobStatus())) {
				ScheduleJob job = ScheduleJobUtils.entityToData(scheduleJob);
				//quartzManager.addJob(job);

			}

		}
	}

	@Override
	public void changeStatus(Long jobId, String cmd) throws SchedulerException {
		TaskDO scheduleJob = get(jobId);
		if (scheduleJob == null) {
			return;
		}
		if (Constant.STATUS_RUNNING_STOP.equals(cmd)) {
			//quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
			scheduleJob.setJobStatus(ScheduleJob.STATUS_NOT_RUNNING);
		} else {
			if (!Constant.STATUS_RUNNING_START.equals(cmd)) {
			} else {
                scheduleJob.setJobStatus(ScheduleJob.STATUS_RUNNING);
                //quartzManager.addJob(ScheduleJobUtils.entityToData(scheduleJob));
            }
		}
		update(scheduleJob);
	}

	@Override
	public void updateCron(Long jobId) throws SchedulerException {
		TaskDO scheduleJob = get(jobId);

		update(scheduleJob);
	}

	@Override
	public void addAgreementJob(Map<String, Object> params, Long triggerDate) {
		/*
		Date trigger = new Date(triggerDate);
		String cron = generator(trigger);
		TaskDO taskScheduleJob = new TaskDO();
		taskScheduleJob.setJobName("agreement_"+System.currentTimeMillis());
		taskScheduleJob.setJobStatus(Constant.STATUS_RUNNING_START);
		taskScheduleJob.setJobGroup("agreement");
		taskScheduleJob.setBeanClass("");
		taskScheduleJob.setCreateDate(new Date());
		taskScheduleJob.setDescription(params.toString());
		taskScheduleJob.setCronExpression(cron);
		taskScheduleJob.setDescription(params.toString());
		save(taskScheduleJob);
		Long taskId = taskScheduleJob.getId();
		Long activeTime = (triggerDate - System.currentTimeMillis()) / 1000;
		redisUtils.set(Constants.BACK_AGREEMENT_PRE_KEY+params.get("mid")+"_"+params.get("tid")+"_"+params.get("agreementId")+"_"+taskId,params,activeTime);
		taskScheduleJob.setJobStatus(ScheduleJob.STATUS_RUNNING);
		ScheduleJob scheduleJob = ScheduleJobUtils.entityToData(taskScheduleJob);
		JobDataMap dataMap = new JobDataMap();
		dataMap.put("mid",params.get("mid").toString());
		dataMap.put("tid",params.get("tid").toString());
		dataMap.put("agreementId",params.get("agreementId").toString());
		scheduleJob.setJobDataMap(dataMap);
		quartzManager.addJob(scheduleJob);*/

	}

	@Override
	public void addPurchaseBackJob(Map<String, Object> params) {

		//添加抢单后的返现任务

		int time = random.nextInt(Constants.BACK_PAY_TIME_MAX - Constants.BACK_PAY_TIME_MIN + 1) + Constants.BACK_PAY_TIME_MIN;
		TaskDO taskScheduleJob = new TaskDO();
		taskScheduleJob.setJobName("purchase_back"+System.currentTimeMillis());
		taskScheduleJob.setJobStatus(Constant.STATUS_RUNNING_START);
		taskScheduleJob.setJobGroup("purchase");
		taskScheduleJob.setBeanClass("");
		taskScheduleJob.setCreateDate(new Date());
		taskScheduleJob.setDescription(params.toString());
		taskScheduleJob.setCronExpression(time+"");
		save(taskScheduleJob);
		Long taskId = taskScheduleJob.getId();
		redisUtils.set(Constants.BACK_PAY_PRE_KEY+params.get("mid")+"_"+params.get("tid")+"_"+taskId,params,time);

	}







	private static String generator(Date trigger) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(trigger);

		String cron = "* {min} {hour} {day} {month} ? {year}";
		//cron = cron.replace("{sec}",calendar.get(Calendar.SECOND)+"");
		cron = cron.replace("{min}",calendar.get(Calendar.MINUTE)+"");
		cron = cron.replace("{hour}",calendar.get(Calendar.HOUR_OF_DAY)+"");
		cron = cron.replace("{day}",calendar.get(Calendar.DAY_OF_MONTH)+"");
		cron = cron.replace("{month}",calendar.get(Calendar.MONTH) + 1 + "");
		cron = cron.replace("{year}",calendar.get(Calendar.YEAR)+"");


		return cron;
	}


	public static void main(String[] args) {
		//Date temp = DateUtil.toDate("2019-05-11 18:24:33","yyy-MM-dd hh:mm:ss");
		int MAX = 18000;
		int MIN = 14400;

		for(int i=0;i<100;i++){
			System.out.println(random.nextInt(MAX - MIN + 1) + MIN);
		}

	}

}
