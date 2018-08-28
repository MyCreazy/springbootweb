package com.mycreazy.springbootweb.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Time: 上午11:10
 * 参数管理
 **/
@Component
@PropertySource({"classpath:paramconfig.properties"})
public class ParamManager {
    @Value("${shell.hql.path}")
    private  String shellPath;

    @Value("${sendmail.address}")
    private String sendPerson;

    @Value("${sendmail.username}")
    private  String sendmailUsername;

    @Value("${sendmail.password}")
    private  String sendmailPassword;

    @Value("${mail.receiver}")
    private  String mailReceiver;

    @Value("${task.excutequeue}")
    private String taskExcuteQueue;

    @Value("${shell.excutelog.path}")
    private  String shellExcutelogPath;

    @Value("${regex.success.flag}")
    private  String regexSuccessFlag;

    /**
     * 执行的shell名称
     */
    @Value("${executeshell.name}")
    private  String executeShellName;

    @Value("${chown.shellname}")
    private  String chownShellname;

    @Value("${shell.backup.path}")
    private  String shellBackupPath;

    /**
     * 线程休眠时间
     */
    @Value("${thread.sleep.time}")
    private  int threadSleepTime;

    public int getThreadSleepTime() {
        return threadSleepTime;
    }

    public void setThreadSleepTime(int threadSleepTime) {
        this.threadSleepTime = threadSleepTime;
    }

    public String getShellBackupPath() {
        return shellBackupPath;
    }

    public void setShellBackupPath(String shellBackupPath) {
        this.shellBackupPath = shellBackupPath;
    }

    public String getChownShellname() {
        return chownShellname;
    }

    public void setChownShellname(String chownShellname) {
        this.chownShellname = chownShellname;
    }

    public String getExecuteShellName() {
        return executeShellName;
    }

    public void setExecuteShellName(String executeShellName) {
        this.executeShellName = executeShellName;
    }

    public String getRegexSuccessFlag() {
        return regexSuccessFlag;
    }

    public void setRegexSuccessFlag(String regexSuccessFlag) {
        this.regexSuccessFlag = regexSuccessFlag;
    }

    public String getShellExcutelogPath() {
        return shellExcutelogPath;
    }

    public void setShellExcutelogPath(String shellExcutelogPath) {
        this.shellExcutelogPath = shellExcutelogPath;
    }

    public String getTaskExcuteQueue() {
        return taskExcuteQueue;
    }

    public void setTaskExcuteQueue(String taskExcuteQueue) {
        this.taskExcuteQueue = taskExcuteQueue;
    }

    public String getMailReceiver() {
        return mailReceiver;
    }

    public void setMailReceiver(String mailReceiver) {
        this.mailReceiver = mailReceiver;
    }

    public String getShellPath() {
        return shellPath;
    }

    public void setShellPath(String shellPath) {
        this.shellPath = shellPath;
    }

    public String getSendPerson() {
        return sendPerson;
    }

    public void setSendPerson(String sendPerson) {
        this.sendPerson = sendPerson;
    }

    public String getSendmailUsername() {
        return sendmailUsername;
    }

    public void setSendmailUsername(String sendmailUsername) {
        this.sendmailUsername = sendmailUsername;
    }

    public String getSendmailPassword() {
        return sendmailPassword;
    }

    public void setSendmailPassword(String sendmailPassword) {
        this.sendmailPassword = sendmailPassword;
    }
}
