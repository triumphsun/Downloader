# Objective
*Downloader* is a sub-project of a program tradding platform, 
and it works as a infrastructure to systematically download huge amount of files everyday after the market is closed.

The Client-Server architecture allows you to manage download tasks within a central server and 
get tasks executed distributively at clients with different IPs. 
Data provider at public sector tend to block abnormal access which they consider initiated by 
machines, rather ran human beings.

# Definition
Introduction of some terms used in this project.

## Task
A *Task* in this system is a work to **copy** a file from its source to an new destination.
A *Download* can be considerd as a *Task* as it copies file across networks. 

在這個系統中，「任務」就是一種將檔案**複製**到新目的地的工作。「下載」也可以被理解成「任務」的一種，它實際上就是一種橫跨網路的複製。

*Tasks* are independent to each other. It doesn't matter which task executes first or whether a task fails its execution.

任務之間彼此獨立。個別任務的執行順序與成功與否，並不影響其它任務。

## Server
A server manages plenty of tasks to be downloaded and executed by its clients.
The client will report its execution status whether the task is successfully completed or not.

伺服器管理著許多「任務」，這些任務會被客戶端下載回去執行，並回報執行成功與否。

A server could choose to re-assign a task that was previously failed, as a mechanism of *Retry*.

伺服器可以選擇重新派出曾經失敗過的任務，來做為一種重試的機制。

## Client
A client will execute tasks downloaded from a given server, 
and will report its execution status whether the task is successfully completed or not.
Once a task is completed, the clint will go for another task.

客戶端負責執行任務。它會連線到指定的伺服器、下載任務、執行任務，並回報執行成功與否。
完成了一個任務之後，會繼續下一個任務。

## Standalone Execution
Sometimes 

Debug is another scenario 

# Usage

## Server Mode

``` java -jar downloader.jar --server --config config.json ```



## Client Mode

``` java -jar downloader.jar --client --endpoint https://example.com/api/v1/task?type=foo```

## Standalone Mode
The program will work independently to execute a task provided from the command line.

獨立運作模式。程式由命令列取得任務後直接執行。

```JSON
{
"id": "my-download-task-12345",
"from": "https://www.github.com/triumphsun",
"to": "file:///Users/suntri/foo.html",
"reportTo": "https://example.com/api.v1/task/my-download-task-12345"
}
```

Read task from file
``` java -jar downloader.jar --file task.json ```

Read task from pipe
``` cat task.json | java -jar downloader.jar ```

Read task from command line
``` java -jar downloader.jar https://github.com/triumphsun file:///Users/suntri/github.html ```

# Communication Details

## Retrieve a Task

## Report a Task Execution
