package com.wk.quartz.demo.util;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuartzUtil {

    @Autowired
    private Scheduler sched;
    /**
     *  间隔多少秒来执行任务
     * @param jobClass
     * @param jobName
     * @param jobGroupName
     * @param seconds  间隔多少秒
     * @throws SchedulerException
     */
    public void addSimpleJob(Class<? extends Job> jobClass,String jobName,String jobGroupName,int seconds) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(seconds)
                        // 超时处理机制
                        .withMisfireHandlingInstructionNextWithExistingCount())
                .startNow().build();
        sched.scheduleJob(jobDetail,simpleTrigger);
        if (!sched.isShutdown()){
            sched.start();
        }
    }
    /**
     * 添加一个job到队列中
     * @param jobclass 任务实现类
     * @param jobname 任务名字
     * @param jobGroupName  任务组名
     * @param jobcron  时间设置字符串
     */
    public void addJob(Class<? extends Job> jobclass,String jobname,
                    String jobGroupName,String jobcron){
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(jobclass).withIdentity(jobname, jobGroupName).build();
        //创建触发器
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobname, jobGroupName)
                .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                .withSchedule(CronScheduleBuilder.cronSchedule(jobcron)).startNow().build();

        try {
            //把任务加入调度队列
            sched.scheduleJob(jobDetail,cronTrigger);
            if(!sched.isShutdown()){
                //任务队列开始
                sched.start();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     *  添加任务或更新任务
     *  如果任务存在就更新任务
     * @param jobclass
     * @param jobName
     * @param jobGroupName
     * @param jobCron
     */
    public void addOrUpadteJob(Class<? extends Job> jobclass,String jobName,
                               String jobGroupName,String jobCron){
        //先去队列获取任务的 触发器key
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
        CronTrigger trigger = null;
        try {
            //获取触发器
            trigger = (CronTrigger)sched.getTrigger(triggerKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        if(trigger == null){
            //如果任务不存在  就创建新任务
            addJob(jobclass,jobName,jobGroupName,jobCron);
        }else{
            if(trigger.getCronExpression().equals(jobCron)){
                //如果任务的触发时间一致  就退出
                return;
            }
            try {
                //触发时间不一致  就更新任务
                updateJob(jobName,jobGroupName,jobCron);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
    }

    public void addJob(Class<? extends Job> jobClass,String jobName,String jobGroupname,int jobTime){
        addJob(jobClass,jobName,jobGroupname,-1);
    }

    //simpletrigger  的任务
    public void addJob(Class<? extends Job> jobClass,String jobName,String jobGroupName,int jobTime,int jobTimes) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
        Trigger trigger = null;
        if(jobTimes < 0){
            //间隔jobTime就执行一次
            trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1).withIntervalInSeconds(jobTime))
                    .startNow().build();
        }else{
            //间隔jobTime执行一次 一共执行jobTimes
            trigger = TriggerBuilder.newTrigger().withIdentity(jobName,jobGroupName)
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1)
                    .withIntervalInSeconds(jobTime).withRepeatCount(jobTimes))
                    .startNow().build();
        }
            //把任务添加到任务队列中
            sched.scheduleJob(jobDetail,trigger);
            if(! sched.isShutdown()){
                sched.start();
            }
    }

    /**
     * 更新任务
     * @param jobName
     * @param jobGroupname
     * @param jobTime
     * @throws SchedulerException
     */
    public void updateJob(String jobName,String jobGroupname,String jobTime) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupname);
        CronTrigger trigger = (CronTrigger)sched.getTrigger(triggerKey);
        //重新创建触发器
        CronTrigger cronTrigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                .withSchedule(CronScheduleBuilder.cronSchedule(jobTime)).build();
        //重新启动触发器
        sched.rescheduleJob(triggerKey,trigger);
    }

    /**
     * 删除任务
     * @param jobname
     * @param jobGroupName
     */
    public void deleteJob(String jobname,String jobGroupName){
        try {
            sched.deleteJob(new JobKey(jobname,jobGroupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停任务
     * @param jobName
     * @param jobGroupName
     */
    public void pauseJob(String jobName,String jobGroupName){
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        try {
            sched.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     *  恢复任务
     * @param jobName
     * @param jobGroupName
     */
    public void resumeJob(String jobName,String jobGroupName){
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        try {
            sched.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 立即执行一个任务
     * @param jobName
     * @param jobGroupName
     */
    public void runJobNow(String jobName,String jobGroupName){
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        try {
            sched.triggerJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有计划中的任务列表
     * @return
     */
    public List<Map<String,Object>>  queryAllJob(){
        List<Map<String,Object>> jobList = null;

        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        try {
            Set<JobKey> jobKeys = sched.getJobKeys(matcher);
            jobList = new ArrayList<>();
            for(JobKey jobKey:jobKeys){
                List<? extends Trigger> triggersOfJob = sched.getTriggersOfJob(jobKey);
                for(Trigger trigger:triggersOfJob){
                    Map<String,Object> map = new HashMap<>();
                    map.put("jobName",jobKey.getName());
                    map.put("jobGroupName",jobKey.getGroup());
                    map.put("description","触发器"+trigger.getKey());
                    Trigger.TriggerState triggerState = sched.getTriggerState(trigger.getKey());
                    map.put("jobStatus",triggerState.name());
                    if(trigger instanceof CronTrigger){
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        map.put("jobTime",cronExpression);
                    }
                    jobList.add(map);
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobList;
    }

    /**
     * 获取所有的执行中的任务
     * @return
     */
    public List<Map<String,Object>> queryRunJob(){
        List<Map<String,Object>> jobList = null;
        try {
            List<JobExecutionContext> currentlyExecutingJobs = sched.getCurrentlyExecutingJobs();
            jobList = new ArrayList<>(currentlyExecutingJobs.size());
            for(JobExecutionContext executionJob:currentlyExecutingJobs) {
                Map<String, Object> map = new HashMap<>();
                JobDetail jobDetail = executionJob.getJobDetail();
                JobKey key = jobDetail.getKey();
                Trigger trigger = executionJob.getTrigger();
                map.put("jobName",key.getName());
                map.put("jobGroupName",key.getGroup());
                map.put("description","触发器"+trigger.getKey());
                Trigger.TriggerState triggerState = sched.getTriggerState(trigger.getKey());
                map.put("jobStatus",triggerState.name());
                if(trigger instanceof CronTrigger){
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    map.put("jobTime",cronExpression);
                }
                jobList.add(map);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobList;
    }
}
















