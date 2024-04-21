package Demos.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.*;

public class ThreadPoolDemo
{

    public static void main(String[] args)
    {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Runnable processor1 = new MessageProcessor(4);
        executor.execute(processor1);
        Runnable processor2 = new MessageProcessor(86);
        executor.execute(processor2);
        Runnable processor3 = new MessageProcessor(45);
        executor.execute(processor3);

        executor.shutdown();

        while(!executor.isTerminated())
        {

        }
        System.out.println("All tasks submitted");
    }



}
