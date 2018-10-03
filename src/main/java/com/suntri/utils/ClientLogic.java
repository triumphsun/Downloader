package com.suntri.utils;

        import java.io.IOException;

public interface ClientLogic {
    public Task getTask() throws IOException;
    public void reportTask(Task task, boolean isSucceed) throws IOException;
}
