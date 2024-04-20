package bookStuff;// Given the below main(), complete PrintChar2_SHELL run() as specified below:

//The thread class for printing specific characters in specified times
class PrintChar2 implements Runnable // get rid of _SHELL
{
    private String charsToPrint;   // the character(s) to print
    private int times;                    // the times to repeat

    // thread class constructor
    public PrintChar2(String s, int t) {
        charsToPrint = s;
        times = t;
    }

    public void run() {
        System.out.println("Thread.getName()  = " + Thread.currentThread().getName());
        for (int i = 1; i <= times; i++) {
            System.out.println(charsToPrint);
            if ((i >= times / 2) && (times % 2 == 0)) {
                int r1 = (int) (Math.random() * 10 + 1);   // 1-10
                switch (r1) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        int r2 = (int) (Math.random() * 3 + 1);
                        switch (r2) {
                            case 1:
                                Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
                                break;
                            case 2:
                                Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
                                break;
                            case 3:
                                Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                                break;
                        }
                        break;
                    case 6:
                        int currentGen = Integer.parseInt(charsToPrint.substring(charsToPrint.length() - 1));
                        String newChars = charsToPrint;
                        newChars += charsToPrint.substring(0, 1);
                        newChars += (currentGen + 1);
                        Thread n = new Thread(new PrintChar2(newChars, 50));
                        if (currentGen + 1 <= 3)
                            n.start();
                        break;
                    case 7:
                        currentGen = Integer.parseInt(charsToPrint.substring(charsToPrint.length() - 1));
                         newChars = charsToPrint;
                        newChars += charsToPrint.substring(0, 1);
                        newChars += (currentGen + 1);
                        n = new Thread(new PrintChar2(newChars, 50));
                        if (currentGen + 1 <= 3)
                        {
                            n.start();
                            try {
                                n.join();
                            } catch (InterruptedException e) {

                            }
                        }
                        break;
                    case 8:
                        Thread.yield();
                        break;
                    case 9:
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {

                        }
                        break;
                    case 10:
                        Thread.currentThread().interrupt();
                        break;
                }



                    // >>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<
                    // >>>>>YOUR CODE REPLACES THE FOLLOWING <<<<<
                    // >>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<
                    // Random number r1 (1-10) results as follows:
    /* (1)-(5) [50% of the time] generate an r2 such that 1 <= r2 <= 3 resulting as follows:
             //    (1) Set the Thread Priority to MIN.
             //    (2) Set the Thread Priority to NORMAL.
             //    (3) Set the Thread Priority to MAX.
       (6) Create a new thread off of the current one and “start” it.
                Ex.  current thread = a1  new thread = a1a2  and a thread off of that would be a1a2a3
                        NOTE1: there most likely will be more than one time that a1 spons a new a1a2
                                      thread and that is ok.  Same with the others.
                        NOTE2:  Make sure to STOP the Thread when it reaches its 3rd generation,
                                       Otherwise it may go on forever!
                        Ex  a1a2a3  STOP it at this point!  So NO a1a2a3a4!
       (7) Create a new thread off of the current one and “join” it.
                Ex.  current thread = a1  new thread = a1a2  and a thread off of that would be a1a2a3
                        NOTE1: there most likely will be more than one time that a1 spawns a new a1a2
                                      thread and that is ok.  Same with the others.
                        NOTE2:  Make sure to STOP the Thread when it reaches its 3rd generation,
                                       Otherwise it may go on forever!
                        Ex  a1a2a3  STOP it at this point!  So NO a1a2a3a4!
       (8) Yield the current thread.
       (9) Sleep the current thread for 500 milliseconds.  (½ second)
       (10) Stop the current thread.
       (Bonus): Add in a few of your own.
       NOTE: you will have to look through the book, online, etc in order to accomplish this.  There
                   are various places that will need to be in a try-catch Exception Handler.
    */

            }// if
        }  // run()
    } // bookStuff.PrintChar2


    // ===============================================================
    public static class TaskThreadDemo_LiangChap39_pg1100_PrintChar2_SHELL {
        public static void main(String[] args) {
            final int N = 50;
            Runnable printA = new PrintChar2("a1", N);
            Runnable printB = new PrintChar2("b1", N);
            Runnable printC = new PrintChar2("c1", N);

            // Create threads
            Thread thread1 = new Thread(printA);
            Thread thread2 = new Thread(printB);
            Thread thread3 = new Thread(printC);

            // Start threads
            thread1.start();
            thread2.start();
            thread3.start();

        }// main

    }
}//  TaskThreadDemo_LiangChap39_pg1100_PrintChar2_CLIENT


/*
 * Possible OUTPUT:

charsToPrint = c1     r1 = 4    Thread.getName()  = Thread-7
Thread.getName()  = Thread-7 & Thread.currentThread().setPriority(Thread.MIN_PRIORITY)
charsToPrint = b1     r1 = 9    Thread.getName()  = Thread-6
r=9: Thread.currentThread().sleep(500);  Thread.currentThread().getName() = Thread-6
charsToPrint = a1     r1 = 8    Thread.getName()  = Thread-5
r=8: Thread.currentThread().yield();  Thread.currentThread().getName() = Thread-5
charsToPrint = a1     r1 = 5    Thread.getName()  = Thread-5
Thread.getName()  = Thread-5 & Thread.currentThread().setPriority(Thread.MAX_PRIORITY)
charsToPrint = a1     r1 = 6    Thread.getName()  = Thread-5
r=6 New Thread via newThread.start().  newThread.getName() = Thread-8
charsToPrint = a1a2     r1 = 2    Thread.getName()  = Thread-5
Thread.getName()  = Thread-5 & Thread.currentThread().setPriority(Thread.MAX_PRIORITY)
charsToPrint = a1a2     r1 = 3    Thread.getName()  = Thread-5
Thread.getName()  = Thread-5 & Thread.currentThread().setPriority(Thread.MAX_PRIORITY)
charsToPrint = a1a2     r1 = 8    Thread.getName()  = Thread-5
r=8: Thread.currentThread().yield();  Thread.currentThread().getName() = Thread-5
charsToPrint = a1a2     r1 = 6    Thread.getName()  = Thread-5
r=6 New Thread via newThread.start().  newThread.getName() = Thread-9
charsToPrint = a1a2a3     r1 = 5    Thread.getName()  = Thread-5
Thread.getName()  = Thread-5 & Thread.currentThread().setPriority(Thread.NORM_PRIORITY)
charsToPrint = a1a2a3     r1 = 1    Thread.getName()  = Thread-5
Thread.getName()  = Thread-5 & Thread.currentThread().setPriority(Thread.NORM_PRIORITY)
charsToPrint = a1a2a3     r1 = 8    Thread.getName()  = Thread-5
charsToPrint = a1a2a3     r1 = 1    Thread.getName()  = Thread-9
Thread.getName()  = Thread-9 & Thread.currentThread().setPriority(Thread.MAX_PRIORITY)
charsToPrint = a1a2a3     r1 = 7    Thread.getName()  = Thread-9
charsToPrint = a1a2a3     r1 = 4    Thread.getName()  = Thread-9
Thread.getName()  = Thread-9 & Thread.currentThread().setPriority(Thread.MIN_PRIORITY)
charsToPrint = a1a2     r1 = 2    Thread.getName()  = Thread-8
Thread.getName()  = Thread-8 & Thread.currentThread().setPriority(Thread.MIN_PRIORITY)
r=8: Thread.currentThread().yield();  Thread.currentThread().getName() = Thread-5
charsToPrint = c1     r1 = 2    Thread.getName()  = Thread-7
Thread.getName()  = Thread-7 & Thread.currentThread().setPriority(Thread.NORM_PRIORITY)
charsToPrint = c1     r1 = 1    Thread.getName()  = Thread-7
Thread.getName()  = Thread-7 & Thread.currentThread().setPriority(Thread.NORM_PRIORITY)
charsToPrint = c1     r1 = 6    Thread.getName()  = Thread-7
r=6 New Thread via newThread.start().  newThread.getName() = Thread-10
charsToPrint = c1c2     r1 = 4    Thread.getName()  = Thread-7
Thread.getName()  = Thread-7 & Thread.currentThread().setPriority(Thread.NORM_PRIORITY)
charsToPrint = c1c2     r1 = 1    Thread.getName()  = Thread-7
Thread.getName()  = Thread-7 & Thread.currentThread().setPriority(Thread.MIN_PRIORITY)
charsToPrint = a1a2a3     r1 = 6    Thread.getName()  = Thread-5
charsToPrint = a1a2a3     r1 = 7    Thread.getName()  = Thread-5
charsToPrint = a1a2a3     r1 = 1    Thread.getName()  = Thread-5
Thread.getName()  = Thread-5 & Thread.currentThread().setPriority(Thread.MAX_PRIORITY)
charsToPrint = a1a2a3     r1 = 6    Thread.getName()  = Thread-5
charsToPrint = a1a2a3     r1 = 7    Thread.getName()  = Thread-5
charsToPrint = a1a2a3     r1 = 4    Thread.getName()  = Thread-5
Thread.getName()  = Thread-5 & Thread.currentThread().setPriority(Thread.NORM_PRIORITY)
charsToPrint = a1a2a3     r1 = 9    Thread.getName()  = Thread-5
r=9: Thread.currentThread().sleep(500);  Thread.currentThread().getName() = Thread-5
charsToPrint = c1c2     r1 = 4    Thread.getName()  = Thread-10
Thread.getName()  = Thread-10 & Thread.currentThread().setPriority(Thread.MIN_PRIORITY)
charsToPrint = a1a2a3     r1 = 6    Thread.getName()  = Thread-9
charsToPrint = a1a2a3     r1 = 10    Thread.getName()  = Thread-9
r=10: Thread.currentThread().stop());  Thread.currentThread().getName() = Thread-9
charsToPrint = a1a2     r1 = 10    Thread.getName()  = Thread-8
r=10: Thread.currentThread().stop());  Thread.currentThread().getName() = Thread-8
charsToPrint = c1c2     r1 = 9    Thread.getName()  = Thread-7
charsToPrint = c1c2     r1 = 6    Thread.getName()  = Thread-10
r=6 New Thread via newThread.start().  newThread.getName() = Thread-11
r=9: Thread.currentThread().sleep(500);  Thread.currentThread().getName() = Thread-7
charsToPrint = c1c2c3     r1 = 10    Thread.getName()  = Thread-10
r=10: Thread.currentThread().stop());  Thread.currentThread().getName() = Thread-10
charsToPrint = c1c2c3     r1 = 10    Thread.getName()  = Thread-11
r=10: Thread.currentThread().stop());  Thread.currentThread().getName() = Thread-11
charsToPrint = b1     r1 = 6    Thread.getName()  = Thread-6
r=6 New Thread via newThread.start().  newThread.getName() = Thread-12
charsToPrint = b1b2     r1 = 7    Thread.getName()  = Thread-6
r=7: New Thread via t2.start()/t2.join()  t2.getName() = Thread-13
charsToPrint = b1b2     r1 = 5    Thread.getName()  = Thread-12
Thread.getName()  = Thread-12 & Thread.currentThread().setPriority(Thread.MIN_PRIORITY)
charsToPrint = b1b2     r1 = 6    Thread.getName()  = Thread-12
r=6 New Thread via newThread.start().  newThread.getName() = Thread-14
charsToPrint = b1b2b3     r1 = 3    Thread.getName()  = Thread-13
Thread.getName()  = Thread-13 & Thread.currentThread().setPriority(Thread.MIN_PRIORITY)
charsToPrint = b1b2b3     r1 = 8    Thread.getName()  = Thread-13
r=8: Thread.currentThread().yield();  Thread.currentThread().getName() = Thread-13
charsToPrint = b1b2b3     r1 = 2    Thread.getName()  = Thread-13
Thread.getName()  = Thread-13 & Thread.currentThread().setPriority(Thread.MAX_PRIORITY)
charsToPrint = b1b2b3     r1 = 9    Thread.getName()  = Thread-13
r=9: Thread.currentThread().sleep(500);  Thread.currentThread().getName() = Thread-13
charsToPrint = b1b2b3     r1 = 2    Thread.getName()  = Thread-12
Thread.getName()  = Thread-12 & Thread.currentThread().setPriority(Thread.NORM_PRIORITY)
charsToPrint = b1b2b3     r1 = 5    Thread.getName()  = Thread-12
Thread.getName()  = Thread-12 & Thread.currentThread().setPriority(Thread.MIN_PRIORITY)
charsToPrint = b1b2b3     r1 = 9    Thread.getName()  = Thread-12
r=9: Thread.currentThread().sleep(500);  Thread.currentThread().getName() = Thread-12
charsToPrint = b1b2b3     r1 = 10    Thread.getName()  = Thread-14
r=10: Thread.currentThread().stop());  Thread.currentThread().getName() = Thread-14
charsToPrint = a1a2a3     r1 = 8    Thread.getName()  = Thread-5
r=8: Thread.currentThread().yield();  Thread.currentThread().getName() = Thread-5
charsToPrint = a1a2a3     r1 = 6    Thread.getName()  = Thread-5
charsToPrint = a1a2a3     r1 = 7    Thread.getName()  = Thread-5
charsToPrint = c1c2     r1 = 7    Thread.getName()  = Thread-7
charsToPrint = a1a2a3     r1 = 6    Thread.getName()  = Thread-5
charsToPrint = a1a2a3     r1 = 3    Thread.getName()  = Thread-5
Thread.getName()  = Thread-5 & Thread.currentThread().setPriority(Thread.NORM_PRIORITY)
r=7: New Thread via t2.start()/t2.join()  t2.getName() = Thread-15
charsToPrint = a1a2a3     r1 = 10    Thread.getName()  = Thread-5
r=10: Thread.currentThread().stop());  Thread.currentThread().getName() = Thread-5
charsToPrint = c1c2c3     r1 = 5    Thread.getName()  = Thread-15
Thread.getName()  = Thread-15 & Thread.currentThread().setPriority(Thread.MAX_PRIORITY)
charsToPrint = c1c2c3     r1 = 1    Thread.getName()  = Thread-15
Thread.getName()  = Thread-15 & Thread.currentThread().setPriority(Thread.MIN_PRIORITY)
charsToPrint = c1c2c3     r1 = 1    Thread.getName()  = Thread-15
Thread.getName()  = Thread-15 & Thread.currentThread().setPriority(Thread.NORM_PRIORITY)
charsToPrint = c1c2c3     r1 = 7    Thread.getName()  = Thread-15
charsToPrint = c1c2c3     r1 = 6    Thread.getName()  = Thread-15
charsToPrint = c1c2c3     r1 = 3    Thread.getName()  = Thread-15
Thread.getName()  = Thread-15 & Thread.currentThread().setPriority(Thread.NORM_PRIORITY)
charsToPrint = c1c2c3     r1 = 2    Thread.getName()  = Thread-15
Thread.getName()  = Thread-15 & Thread.currentThread().setPriority(Thread.MAX_PRIORITY)
charsToPrint = c1c2c3     r1 = 7    Thread.getName()  = Thread-15
charsToPrint = c1c2c3     r1 = 2    Thread.getName()  = Thread-15
Thread.getName()  = Thread-15 & Thread.currentThread().setPriority(Thread.NORM_PRIORITY)
charsToPrint = c1c2c3     r1 = 7    Thread.getName()  = Thread-15
charsToPrint = c1c2c3     r1 = 5    Thread.getName()  = Thread-15
Thread.getName()  = Thread-15 & Thread.currentThread().setPriority(Thread.MIN_PRIORITY)
charsToPrint = c1c2c3     r1 = 4    Thread.getName()  = Thread-15
Thread.getName()  = Thread-15 & Thread.currentThread().setPriority(Thread.NORM_PRIORITY)
charsToPrint = c1c2c3     r1 = 8    Thread.getName()  = Thread-15
r=8: Thread.currentThread().yield();  Thread.currentThread().getName() = Thread-15
charsToPrint = c1c2c3     r1 = 3    Thread.getName()  = Thread-15
Thread.getName()  = Thread-15 & Thread.currentThread().setPriority(Thread.MIN_PRIORITY)
charsToPrint = c1c2c3     r1 = 4    Thread.getName()  = Thread-15
Thread.getName()  = Thread-15 & Thread.currentThread().setPriority(Thread.MAX_PRIORITY)
charsToPrint = c1c2c3     r1 = 9    Thread.getName()  = Thread-15
r=9: Thread.currentThread().sleep(500);  Thread.currentThread().getName() = Thread-15
charsToPrint = b1b2b3     r1 = 6    Thread.getName()  = Thread-13
charsToPrint = b1b2b3     r1 = 4    Thread.getName()  = Thread-13
Thread.getName()  = Thread-13 & Thread.currentThread().setPriority(Thread.MIN_PRIORITY)
charsToPrint = b1b2b3     r1 = 7    Thread.getName()  = Thread-12
charsToPrint = b1b2b3     r1 = 7    Thread.getName()  = Thread-13
charsToPrint = b1b2b3     r1 = 7    Thread.getName()  = Thread-12
charsToPrint = b1b2b3     r1 = 10    Thread.getName()  = Thread-13
charsToPrint = b1b2b3     r1 = 3    Thread.getName()  = Thread-12
r=10: Thread.currentThread().stop());  Thread.currentThread().getName() = Thread-13
Thread.getName()  = Thread-12 & Thread.currentThread().setPriority(Thread.MAX_PRIORITY)
charsToPrint = b1b2b3     r1 = 8    Thread.getName()  = Thread-12
r=8: Thread.currentThread().yield();  Thread.currentThread().getName() = Thread-12
charsToPrint = b1b2b3     r1 = 6    Thread.getName()  = Thread-12
charsToPrint = b1b2b3     r1 = 9    Thread.getName()  = Thread-12
r=9: Thread.currentThread().sleep(500);  Thread.currentThread().getName() = Thread-12
charsToPrint = c1c2c3     r1 = 5    Thread.getName()  = Thread-15
Thread.getName()  = Thread-15 & Thread.currentThread().setPriority(Thread.MIN_PRIORITY)
charsToPrint = c1c2c3     r1 = 10    Thread.getName()  = Thread-15
r=10: Thread.currentThread().stop());  Thread.currentThread().getName() = Thread-15
charsToPrint = b1b2b3     r1 = 5    Thread.getName()  = Thread-12
Thread.getName()  = Thread-12 & Thread.currentThread().setPriority(Thread.MAX_PRIORITY)
charsToPrint = b1b2b3     r1 = 6    Thread.getName()  = Thread-12
charsToPrint = b1b2b3     r1 = 4    Thread.getName()  = Thread-12
Thread.getName()  = Thread-12 & Thread.currentThread().setPriority(Thread.NORM_PRIORITY)
charsToPrint = b1b2b3     r1 = 4    Thread.getName()  = Thread-12
Thread.getName()  = Thread-12 & Thread.currentThread().setPriority(Thread.NORM_PRIORITY)
charsToPrint = b1b2b3     r1 = 7    Thread.getName()  = Thread-12
charsToPrint = b1b2b3     r1 = 7    Thread.getName()  = Thread-12
charsToPrint = b1b2b3     r1 = 9    Thread.getName()  = Thread-12
r=9: Thread.currentThread().sleep(500);  Thread.currentThread().getName() = Thread-12
charsToPrint = b1b2b3     r1 = 7    Thread.getName()  = Thread-12
charsToPrint = b1b2b3     r1 = 3    Thread.getName()  = Thread-12
Thread.getName()  = Thread-12 & Thread.currentThread().setPriority(Thread.NORM_PRIORITY)
charsToPrint = b1b2b3     r1 = 3    Thread.getName()  = Thread-12
Thread.getName()  = Thread-12 & Thread.currentThread().setPriority(Thread.MAX_PRIORITY)
charsToPrint = b1b2b3     r1 = 9    Thread.getName()  = Thread-12
r=9: Thread.currentThread().sleep(500);  Thread.currentThread().getName() = Thread-12
charsToPrint = b1b2b3     r1 = 4    Thread.getName()  = Thread-12
Thread.getName()  = Thread-12 & Thread.currentThread().setPriority(Thread.MAX_PRIORITY)
charsToPrint = b1b2b3     r1 = 6    Thread.getName()  = Thread-12
charsToPrint = b1b2b3     r1 = 2    Thread.getName()  = Thread-12
Thread.getName()  = Thread-12 & Thread.currentThread().setPriority(Thread.MIN_PRIORITY)
charsToPrint = b1b2b3     r1 = 6    Thread.getName()  = Thread-12
charsToPrint = b1b2b3     r1 = 7    Thread.getName()  = Thread-12
charsToPrint = b1b2b3     r1 = 4    Thread.getName()  = Thread-12
Thread.getName()  = Thread-12 & Thread.currentThread().setPriority(Thread.NORM_PRIORITY)
charsToPrint = b1b2b3     r1 = 3    Thread.getName()  = Thread-12
Thread.getName()  = Thread-12 & Thread.currentThread().setPriority(Thread.NORM_PRIORITY)
charsToPrint = b1b2b3     r1 = 6    Thread.getName()  = Thread-12
charsToPrint = b1b2b3     r1 = 1    Thread.getName()  = Thread-12
Thread.getName()  = Thread-12 & Thread.currentThread().setPriority(Thread.NORM_PRIORITY)
charsToPrint = b1b2b3     r1 = 2    Thread.getName()  = Thread-12
Thread.getName()  = Thread-12 & Thread.currentThread().setPriority(Thread.MIN_PRIORITY)
charsToPrint = b1b2b3     r1 = 7    Thread.getName()  = Thread-12
charsToPrint = b1b2b3     r1 = 9    Thread.getName()  = Thread-12
r=9: Thread.currentThread().sleep(500);  Thread.currentThread().getName() = Thread-12
charsToPrint = b1b2b3     r1 = 7    Thread.getName()  = Thread-12
charsToPrint = b1b2b3     r1 = 9    Thread.getName()  = Thread-12
r=9: Thread.currentThread().sleep(500);  Thread.currentThread().getName() = Thread-12
charsToPrint = b1b2b3     r1 = 4    Thread.getName()  = Thread-12
Thread.getName()  = Thread-12 & Thread.currentThread().setPriority(Thread.MIN_PRIORITY)
charsToPrint = b1b2b3     r1 = 2    Thread.getName()  = Thread-12
Thread.getName()  = Thread-12 & Thread.currentThread().setPriority(Thread.MAX_PRIORITY)
charsToPrint = b1b2b3     r1 = 2    Thread.getName()  = Thread-12
Thread.getName()  = Thread-12 & Thread.currentThread().setPriority(Thread.MIN_PRIORITY)
charsToPrint = b1b2b3     r1 = 7    Thread.getName()  = Thread-12
charsToPrint = b1b2b3     r1 = 4    Thread.getName()  = Thread-12
Thread.getName()  = Thread-12 & Thread.currentThread().setPriority(Thread.MAX_PRIORITY)
charsToPrint = b1b2b3     r1 = 9    Thread.getName()  = Thread-12
r=9: Thread.currentThread().sleep(500);  Thread.currentThread().getName() = Thread-12
charsToPrint = b1b2b3     r1 = 7    Thread.getName()  = Thread-12
charsToPrint = b1b2b3     r1 = 10    Thread.getName()  = Thread-12
r=10: Thread.currentThread().stop());  Thread.currentThread().getName() = Thread-12


 */



