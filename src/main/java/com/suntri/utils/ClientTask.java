package com.suntri.utils;

import java.io.InputStream;

public class ClientTask implements Task{
    private String id;
    private String copyFrom;
    private String copyTo;
    private String reportUrl = null;

    public ClientTask(){

    }

    public String getTaskId(){
        return id;
    }

    public void setTaskId(String id){
        this.id = id;
    }

    public String getCopyFrom() {
        return copyFrom;
    }

    public void setCopyFrom(String copyFrom) {
        this.copyFrom = copyFrom;
    }

    public String getCopyTo() {
        return copyTo;
    }

    public void setCopyTo(String copyTo) {
        this.copyTo = copyTo;
    }

    public String getReportUrl(){
        return reportUrl;
    }

    public void setReportUrl(String reportUrl){
        this.reportUrl = reportUrl;
    }

    public static ClientTask getSampleTask(){
        ClientTask task = new ClientTask();
        task.id = "12345";
        task.copyFrom = "http://www.google.com/sample.json";
        task.copyTo = "file:///Volumes/RamDisk/Workspace/sample.json";
        return task;
    }
}
