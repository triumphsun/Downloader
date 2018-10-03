package com.suntri.utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class URLWorker implements Worker{

    private boolean copy(InputStream is, OutputStream os){
        boolean isDone = false;
        byte [] buffer = new byte[1024];
        int bytesRead = 0;
        try {
            while((bytesRead = is.read(buffer))!=-1){
                os.write(buffer, 0 , bytesRead);
                os.flush();
            }
            isDone = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isDone;
    }

    public boolean copy(String sCopyFrom, String sCopyTo){
        InputStream is = null;
        OutputStream os = null;
        try {
            URL urlCopyFrom = new URL(sCopyFrom);
            URL urlCopyTo = new URL(sCopyTo);

            if(isLocalFile(urlCopyFrom)){
                File fileCopyFrom = new File(urlCopyFrom.getPath());
                is = new FileInputStream(fileCopyFrom);
            } else {
                is = urlCopyFrom.openConnection().getInputStream();
            }

            if(isLocalFile(urlCopyTo)){
                File fileCopyTo = new File(urlCopyTo.getPath());
                if(fileCopyTo.exists()){
                    return false;
                } else {
                    fileCopyTo.createNewFile();
                }
                if(!fileCopyTo.canWrite())
                    return false;
                os = new FileOutputStream(fileCopyTo);
            } else {
                os = urlCopyTo.openConnection().getOutputStream();
            }
            return copy(is, os);
        } catch(MalformedURLException e){
            e.printStackTrace();
            return false;
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if(is != null){
                    is.close();
                }

                if(os != null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean execute(Task task){
        return copy(task.getCopyFrom(), task.getCopyTo());
    }

    public static boolean isLocalFile(URL url){
        return ("file".equalsIgnoreCase(url.getProtocol()) && !hasHost(url));
    }

    public static boolean hasHost(java.net.URL url) {
        String host = url.getHost();
        return (host!=null && !"".equals(host));
    }

    public static void main(String [] args){
        URLWorker worker = new URLWorker();
        String sCopyFrom = "http://www.twse.com.tw/exchangeReport/STOCK_DAY?response=csv&date=20181002&stockNo=2353";
        String sCopyTo = "file:///Volumes/RamDisk/Workspace/BucketDownload/test.txt";

        if(args.length > 0){
            sCopyFrom = args[0];
        }

        if(args.length > 1){
            sCopyTo = args[1];
        }

        boolean result = worker.copy(sCopyFrom, sCopyTo);
        System.out.println(String.format("Done? %b", result));
    }
}
