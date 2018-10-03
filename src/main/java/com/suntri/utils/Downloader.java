package com.suntri.utils;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Downloader implements Worker {
    private Worker worker = null;
    private Options options = new Options();

    private Option optServer = new Option("s", "server", false, "Server Mode");
    private Option optClient = new Option("c", "client", false, "Client Mode");
    private Option optStandalone = new Option("a", "standalone", false, "Standalone Mode");
    private Option optFile = new Option("f", "file", true, "Specify a file");
    private Option optRetry = new Option("r", "retry", true, "Retry");
    private Option optJson = new Option("j", "json", false, "Use JSON format to specify task");
    private Option optHelp = new Option("h", "help", false, "Print help");

    public Downloader(){
        this.options.addOption(optServer);
        this.options.addOption(optClient);
        this.options.addOption(optStandalone);
        this.options.addOption(optFile);
        this.options.addOption(optRetry);
        this.options.addOption(optJson);
        this.options.addOption(optHelp);
    }

    @Override
    public boolean execute(Task task){
        return this.worker.execute(task);
    }

    private void printHelp(){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "downloader", options );
    }
    /*
     * java -jar downloader.jar --server --config config.json
     * java -jar downloader.jar --client --uri http://192.168.0.1:8080/api/v1/job?type=downloader
     * java -jar downloader.jar [--standalone] --file sample.json
     * java -jar downloader.jar [--standalone] --json "{'id': 123, 'from': 'http://www.foo.bar/sample.txt' 'to': 'file:///foo/bar/sample.txt'}"
     * cat sample.json > java -jar downloader.jar [--standalone]
     * java -jar downloader.jar [--standalone] http://www.google.com/ file:///foo/bar/hello.txt
     */
    public static void main(String [] args){
        Downloader downloader = new Downloader();
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(downloader.options, args);
            List <Option> input = Arrays.asList(cmd.getOptions());
            if(input.contains(downloader.optHelp)){
               downloader.printHelp();
            } else if(input.contains(downloader.optServer)){
                System.out.println("Server mode");
            } else if(input.contains(downloader.optClient)){
                System.out.println("Client mode");
            } else {
                System.out.print("Standalone mode - ");
                if(input.contains(downloader.optFile)){
                    System.out.println("Using file#");
                } else if(input.contains(downloader.optJson)){
                    System.out.println("Using json input");
                } else {
                    if(args.length == 0){
                        System.out.println("Using System.in");
                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                        System.out.println("Reading from System.in");
                    } else if(args.length == 2){
                        System.out.println("Using args");
                        URLWorker worker = new URLWorker();
                        worker.copy(args[0], args[1]);
                    } else {
                        downloader.printHelp();
                    }
                }
            }
        } catch(ParseException e){
            e.printStackTrace();
        }
    }
}
