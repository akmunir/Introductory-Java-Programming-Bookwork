package Demos.ThreadPool;

public class MessageProcessor implements Runnable
{
    private int message;

    public MessageProcessor(int message)
    {
        this.message = message;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Messaged Received = " + message);
        processMessage();
        System.out.println(Thread.currentThread().getName() + " Messaged Processed = " + message);

    }

    private void processMessage() {
        this.message *= 10;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
