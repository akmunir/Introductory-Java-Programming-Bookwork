package Demos.Syncronization;

public class sequence
{
    private int value;
    public int getNext()
    {
        synchronized (this)
        {
            return value++;
        }

    }
}
