package Demos.Syncronization;

import java.util.*;
public class Application
{
    sequence sequence = new sequence();
    Thread t1 = new Thread(new Incrementer());
    Thread t2 = new Thread(new Incrementer());



}

class Incrementer implements Runnable
{
    public void run()
    {

    }
}
