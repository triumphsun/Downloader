package com.suntri.utils;

import java.io.IOException;
import java.net.HttpURLConnection;

public class ClientLogicConcrete implements ClientLogic, Runnable {

    private HttpURLConnection conn;
    private Worker worker;
    private final int sleepTime = 3 * 1000;

    @Override
    public Task getTask() throws IOException{
        return null;
    }

    @Override
    public void reportTask(Task task, boolean isSucceed) throws IOException{

    }

    @Override
    public void run() {
        while(true){
            try {
                Task task = this.getTask();
                boolean isSucceed = this.worker.execute(task);
                this.reportTask(task, isSucceed);
                Thread.sleep(sleepTime);
            }catch(IOException e){
                e.printStackTrace();;
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
