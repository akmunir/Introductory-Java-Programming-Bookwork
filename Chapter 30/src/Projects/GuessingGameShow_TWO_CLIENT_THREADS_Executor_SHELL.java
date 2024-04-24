package Projects;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * GuessingGameShow_PlayerThread:  A GameShowPlayer keeps guessing for a number, 1-max, with higher or lower, it adjusting accordingly,
 *      and it 'sleep' (int) Math.abs(actualNumber-guessingNumber)*100 before guessing again.
 *  (1)'implements' the Runnable Interface
 *
 *  (2) Has each of the following fields:
 *      1 String for the name
 *      3 ints for the actualNumber, guessNumber, and guessCount
 *      2 ints for low + high
 *  (3) Has a constructor to assign each of the fields above.
 *  (4) run():
 *          * Have a loop 'while (guessNumber!=actualNumber)' that keeps trying to guess the actualNumber via a binary guess approach.
 *          *  Output:
 *                 - name, guessNumber, guessCount, and clue (too LOW, too HIGH), and the thread itself
 *  (5) Finally, when it gets it right, Output the final guessCount and the actualNumber
 */
// ---------------------------------------------------------------------------------------------------------------------------
// threadGuessCount_Comparator: compare one thread's guessCount to another
 class threadGuessCount_Comparator_SHELL implements Comparator<GuessingGameShow_PlayerThread>
{
    @Override
    public int compare(GuessingGameShow_PlayerThread p1, GuessingGameShow_PlayerThread p2) {
        if (p1.getGuessCount() == p2.getGuessCount())
            return 0;
        else if (p1.getGuessCount() < p2.getGuessCount())
            return -1;
        else
            return 1;
    }
}

// ---------------------------------------------------------------------------------------------------------------------------

/**  GuessingGameShow_TWO_CLIENT_THREADS_Executor
 *
 *  >>> See Liang Book 10th Edition Sec 30.6 Thread Pools <<<
 *
 *     CLIENT:      (Same as GuessingGameShowONE_CLIENT)
 *                  3 GuessingGameShow_Player's are trying to guess a number 1-max,
 *                  and see who is the fastest at getting it right.  With each guess,
 *                  the player hits a delay/sleep for a random amount of time.
 *
 *     Directions:   (New Part)
 *                  (1) Create an ExecutorService and put numberOfPlayers (10) threads into it,
 *                       remembering that they run automatically.
 *                  (2) Create threadArray with size numberOfPlayers and put each
 *                       GuessingGameShow_PlayerThread into it.
 *                  (3) After the guessing is all done, sort threadArray via a
 *                       threadGuessCount_Comparator() you design that compares each
 *                       GuessingGameShow_PlayerThread's GuessCount accordingly.
 */
public class GuessingGameShow_TWO_CLIENT_THREADS_Executor_SHELL
{

    public static void main(String[] args)
    {
        int randomNum = 0;
        int numPlayers = 10;
        ExecutorService executor = Executors.newFixedThreadPool(numPlayers);
        GuessingGameShow_PlayerThread[] threadArray = new GuessingGameShow_PlayerThread[numPlayers];
        Thread t1 = null;
        GuessingGameShow_PlayerThread player = null;
        int count = 0;
        for (int i = 0; i < numPlayers; i++)
        {
            randomNum = (int) (Math.random() * 10000000) + 1;
            player = new GuessingGameShow_PlayerThread("p" + (count),randomNum, 10000000);
            t1 = new Thread(player);
            threadArray[i] = player;
            executor.execute(t1);
            count++;
        }
        executor.shutdown();
        while(!executor.isTerminated()) {
        }

        threadGuessCount_Comparator_SHELL comparator = new threadGuessCount_Comparator_SHELL();
        Arrays.sort(threadArray, comparator);
        System.out.print(threadArray[0].getName() + " has a GuessCount of = " + threadArray[0].getGuessCount());
        for (int i = 1; i < threadArray.length; i++)
        {
            System.out.print(" , " + threadArray[i].getName() + " has a GuessCount of = " + threadArray[i].getGuessCount());
        }
        System.out.println();
        System.out.println(threadArray[0].getName() +  " is the Winner!");


    } // main

}  //  GuessingGameShow_CLIENT_THREADS_Executor

/*
* Possible OUTPUT:      // NOTE: the prior guessCounts got deleted from the buffer due to space.
P1: guessNumber = 117187     guessCount = 7 too LOW!  Thread: GuessingGameShow_PlayerThread@a04ed6
P1: guessNumber = 121093     guessCount = 8 too HIGH!  Thread: GuessingGameShow_PlayerThread@a04ed6
P1: guessNumber = 119140     guessCount = 9 too HIGH!  Thread: GuessingGameShow_PlayerThread@a04ed6
P1: guessNumber = 118163     guessCount = 10 too HIGH!  Thread: GuessingGameShow_PlayerThread@a04ed6
P1: guessNumber = 117675     guessCount = 11 too LOW!  Thread: GuessingGameShow_PlayerThread@a04ed6
P1: guessNumber = 117919     guessCount = 12 too LOW!  Thread: GuessingGameShow_PlayerThread@a04ed6
P1: guessNumber = 118041     guessCount = 13 too LOW!  Thread: GuessingGameShow_PlayerThread@a04ed6
P1: guessNumber = 118102     guessCount = 14 too LOW!  Thread: GuessingGameShow_PlayerThread@a04ed6
P1: guessNumber = 118132     guessCount = 15 too LOW!  Thread: GuessingGameShow_PlayerThread@a04ed6
P1: guessNumber = 118147     guessCount = 16 too HIGH!  Thread: GuessingGameShow_PlayerThread@a04ed6
P1: guessNumber = 118139     guessCount = 17 too LOW!  Thread: GuessingGameShow_PlayerThread@a04ed6
P1: guessNumber = 118143     guessCount = 18 too LOW!  Thread: GuessingGameShow_PlayerThread@a04ed6
P1: guessNumber = 118145     guessCount = 19 Thread: GuessingGameShow_PlayerThread@a04ed6
>>>>> P1: You got with guessCount = 19    actualNumber = 118145
P7: guessNumber = 500000     guessCount = 1 too LOW!  Thread: GuessingGameShow_PlayerThread@7cbe89
P7: guessNumber = 750000     guessCount = 2 too HIGH!  Thread: GuessingGameShow_PlayerThread@7cbe89
P7: guessNumber = 625000     guessCount = 3 too LOW!  Thread: GuessingGameShow_PlayerThread@7cbe89
P6: guessNumber = 500000     guessCount = 1 too LOW!  Thread: GuessingGameShow_PlayerThread@1d3168b
P6: guessNumber = 750000     guessCount = 2 too HIGH!  Thread: GuessingGameShow_PlayerThread@1d3168b
P2: guessNumber = 500000     guessCount = 1 too LOW!  Thread: GuessingGameShow_PlayerThread@457f7b
P6: guessNumber = 625000     guessCount = 3 too LOW!  Thread: GuessingGameShow_PlayerThread@1d3168b
P6: guessNumber = 687500     guessCount = 4 too HIGH!  Thread: GuessingGameShow_PlayerThread@1d3168b
P6: guessNumber = 656250     guessCount = 5 too HIGH!  Thread: GuessingGameShow_PlayerThread@1d3168b
P6: guessNumber = 640625     guessCount = 6 too LOW!  Thread: GuessingGameShow_PlayerThread@1d3168b
P6: guessNumber = 648437     guessCount = 7 too LOW!  Thread: GuessingGameShow_PlayerThread@1d3168b
P6: guessNumber = 652343     guessCount = 8 too HIGH!  Thread: GuessingGameShow_PlayerThread@1d3168b
P6: guessNumber = 650390     guessCount = 9 too LOW!  Thread: GuessingGameShow_PlayerThread@1d3168b
P6: guessNumber = 651366     guessCount = 10 too LOW!  Thread: GuessingGameShow_PlayerThread@1d3168b
P6: guessNumber = 651854     guessCount = 11 too LOW!  Thread: GuessingGameShow_PlayerThread@1d3168b
P6: guessNumber = 652098     guessCount = 12 too HIGH!  Thread: GuessingGameShow_PlayerThread@1d3168b
P10: guessNumber = 500000    guessCount = 1 too HIGH!  Thread: GuessingGameShow_PlayerThread@1777005
P10: guessNumber = 250000    guessCount = 2 too LOW!  Thread: GuessingGameShow_PlayerThread@1777005
P10: guessNumber = 375000    guessCount = 3 too HIGH!  Thread: GuessingGameShow_PlayerThread@1777005
P10: guessNumber = 312500    guessCount = 4 too LOW!  Thread: GuessingGameShow_PlayerThread@1777005
P10: guessNumber = 343750    guessCount = 5 too HIGH!  Thread: GuessingGameShow_PlayerThread@1777005
P10: guessNumber = 328125    guessCount = 6 too HIGH!  Thread: GuessingGameShow_PlayerThread@1777005
P10: guessNumber = 320312    guessCount = 7 too HIGH!  Thread: GuessingGameShow_PlayerThread@1777005
P10: guessNumber = 316406    guessCount = 8 too LOW!  Thread: GuessingGameShow_PlayerThread@1777005
P10: guessNumber = 318359    guessCount = 9 too LOW!  Thread: GuessingGameShow_PlayerThread@1777005
P10: guessNumber = 319335    guessCount = 10 too LOW!  Thread: GuessingGameShow_PlayerThread@1777005
P10: guessNumber = 319823    guessCount = 11 too LOW!  Thread: GuessingGameShow_PlayerThread@1777005
P10: guessNumber = 320067    guessCount = 12 too LOW!  Thread: GuessingGameShow_PlayerThread@1777005
P10: guessNumber = 320189    guessCount = 13 too LOW!  Thread: GuessingGameShow_PlayerThread@1777005
P10: guessNumber = 320250    guessCount = 14 too HIGH!  Thread: GuessingGameShow_PlayerThread@1777005
P10: guessNumber = 320219    guessCount = 15 too LOW!  Thread: GuessingGameShow_PlayerThread@1777005
P10: guessNumber = 320234    guessCount = 16 too HIGH!  Thread: GuessingGameShow_PlayerThread@1777005
P10: guessNumber = 320226    guessCount = 17 too HIGH!  Thread: GuessingGameShow_PlayerThread@1777005
P10: guessNumber = 320222    guessCount = 18 Thread: GuessingGameShow_PlayerThread@1777005
>>>>> P10: You got with guessCount = 18    actualNumber = 320222
P5: guessNumber = 500000     guessCount = 1 too HIGH!  Thread: GuessingGameShow_PlayerThread@561840
P5: guessNumber = 250000     guessCount = 2 too LOW!  Thread: GuessingGameShow_PlayerThread@561840
P5: guessNumber = 375000     guessCount = 3 too LOW!  Thread: GuessingGameShow_PlayerThread@561840
P5: guessNumber = 437500     guessCount = 4 too HIGH!  Thread: GuessingGameShow_PlayerThread@561840
P5: guessNumber = 406250     guessCount = 5 too HIGH!  Thread: GuessingGameShow_PlayerThread@561840
P5: guessNumber = 390625     guessCount = 6 too HIGH!  Thread: GuessingGameShow_PlayerThread@561840
P5: guessNumber = 382812     guessCount = 7 too LOW!  Thread: GuessingGameShow_PlayerThread@561840
P5: guessNumber = 386718     guessCount = 8 too HIGH!  Thread: GuessingGameShow_PlayerThread@561840
P5: guessNumber = 384765     guessCount = 9 too LOW!  Thread: GuessingGameShow_PlayerThread@561840
P5: guessNumber = 385741     guessCount = 10 too LOW!  Thread: GuessingGameShow_PlayerThread@561840
P5: guessNumber = 386229     guessCount = 11 too LOW!  Thread: GuessingGameShow_PlayerThread@561840
P5: guessNumber = 386473     guessCount = 12 too LOW!  Thread: GuessingGameShow_PlayerThread@561840
P5: guessNumber = 386595     guessCount = 13 too LOW!  Thread: GuessingGameShow_PlayerThread@561840
P5: guessNumber = 386656     guessCount = 14 too HIGH!  Thread: GuessingGameShow_PlayerThread@561840
P5: guessNumber = 386625     guessCount = 15 too HIGH!  Thread: GuessingGameShow_PlayerThread@561840
P5: guessNumber = 386610     guessCount = 16 too LOW!  Thread: GuessingGameShow_PlayerThread@561840
P5: guessNumber = 386617     guessCount = 17 too HIGH!  Thread: GuessingGameShow_PlayerThread@561840
P5: guessNumber = 386613     guessCount = 18 too LOW!  Thread: GuessingGameShow_PlayerThread@561840
P6: guessNumber = 651976     guessCount = 13 too LOW!  Thread: GuessingGameShow_PlayerThread@1d3168b
P6: guessNumber = 652037     guessCount = 14 too HIGH!  Thread: GuessingGameShow_PlayerThread@1d3168b
P6: guessNumber = 652006     guessCount = 15 too LOW!  Thread: GuessingGameShow_PlayerThread@1d3168b
P6: guessNumber = 652021     guessCount = 16 too HIGH!  Thread: GuessingGameShow_PlayerThread@1d3168b
P6: guessNumber = 652013     guessCount = 17 too LOW!  Thread: GuessingGameShow_PlayerThread@1d3168b
P6: guessNumber = 652017     guessCount = 18 too HIGH!  Thread: GuessingGameShow_PlayerThread@1d3168b
P6: guessNumber = 652015     guessCount = 19 too LOW!  Thread: GuessingGameShow_PlayerThread@1d3168b
P6: guessNumber = 652016     guessCount = 20 Thread: GuessingGameShow_PlayerThread@1d3168b
>>>>> P6: You got with guessCount = 20    actualNumber = 652016
P9: guessNumber = 500000     guessCount = 1 too HIGH!  Thread: GuessingGameShow_PlayerThread@124f39f
P9: guessNumber = 250000     guessCount = 2 too LOW!  Thread: GuessingGameShow_PlayerThread@124f39f
P9: guessNumber = 375000     guessCount = 3 too HIGH!  Thread: GuessingGameShow_PlayerThread@124f39f
P9: guessNumber = 312500     guessCount = 4 too HIGH!  Thread: GuessingGameShow_PlayerThread@124f39f
P2: guessNumber = 750000     guessCount = 2 too HIGH!  Thread: GuessingGameShow_PlayerThread@457f7b
P2: guessNumber = 625000     guessCount = 3 too LOW!  Thread: GuessingGameShow_PlayerThread@457f7b
P2: guessNumber = 687500     guessCount = 4 too LOW!  Thread: GuessingGameShow_PlayerThread@457f7b
P2: guessNumber = 718750     guessCount = 5 too LOW!  Thread: GuessingGameShow_PlayerThread@457f7b
P2: guessNumber = 734375     guessCount = 6 too LOW!  Thread: GuessingGameShow_PlayerThread@457f7b
P2: guessNumber = 742187     guessCount = 7 too LOW!  Thread: GuessingGameShow_PlayerThread@457f7b
P2: guessNumber = 746093     guessCount = 8 too HIGH!  Thread: GuessingGameShow_PlayerThread@457f7b
P2: guessNumber = 744140     guessCount = 9 too LOW!  Thread: GuessingGameShow_PlayerThread@457f7b
P2: guessNumber = 745116     guessCount = 10 too LOW!  Thread: GuessingGameShow_PlayerThread@457f7b
P2: guessNumber = 745604     guessCount = 11 too HIGH!  Thread: GuessingGameShow_PlayerThread@457f7b
P2: guessNumber = 745360     guessCount = 12 too LOW!  Thread: GuessingGameShow_PlayerThread@457f7b
P2: guessNumber = 745482     guessCount = 13 Thread: GuessingGameShow_PlayerThread@457f7b
>>>>> P2: You got with guessCount = 13    actualNumber = 745482
P7: guessNumber = 687500     guessCount = 4 too HIGH!  Thread: GuessingGameShow_PlayerThread@7cbe89
P7: guessNumber = 656250     guessCount = 5 too LOW!  Thread: GuessingGameShow_PlayerThread@7cbe89
P7: guessNumber = 671875     guessCount = 6 too HIGH!  Thread: GuessingGameShow_PlayerThread@7cbe89
P7: guessNumber = 664062     guessCount = 7 too LOW!  Thread: GuessingGameShow_PlayerThread@7cbe89
P7: guessNumber = 667968     guessCount = 8 too HIGH!  Thread: GuessingGameShow_PlayerThread@7cbe89
P7: guessNumber = 666015     guessCount = 9 too LOW!  Thread: GuessingGameShow_PlayerThread@7cbe89
P7: guessNumber = 666991     guessCount = 10 too LOW!  Thread: GuessingGameShow_PlayerThread@7cbe89
P7: guessNumber = 667479     guessCount = 11 too LOW!  Thread: GuessingGameShow_PlayerThread@7cbe89
P7: guessNumber = 667723     guessCount = 12 too LOW!  Thread: GuessingGameShow_PlayerThread@7cbe89
P7: guessNumber = 667845     guessCount = 13 too HIGH!  Thread: GuessingGameShow_PlayerThread@7cbe89
P7: guessNumber = 667784     guessCount = 14 too HIGH!  Thread: GuessingGameShow_PlayerThread@7cbe89
P7: guessNumber = 667753     guessCount = 15 too HIGH!  Thread: GuessingGameShow_PlayerThread@7cbe89
P7: guessNumber = 667738     guessCount = 16 too LOW!  Thread: GuessingGameShow_PlayerThread@7cbe89
P7: guessNumber = 667745     guessCount = 17 too LOW!  Thread: GuessingGameShow_PlayerThread@7cbe89
P7: guessNumber = 667749     guessCount = 18 too LOW!  Thread: GuessingGameShow_PlayerThread@7cbe89
P7: guessNumber = 667751     guessCount = 19 too LOW!  Thread: GuessingGameShow_PlayerThread@7cbe89
P7: guessNumber = 667752     guessCount = 20 Thread: GuessingGameShow_PlayerThread@7cbe89
>>>>> P7: You got with guessCount = 20    actualNumber = 667752
P3: guessNumber = 500000     guessCount = 1 too LOW!  Thread: GuessingGameShow_PlayerThread@139a3a7
P3: guessNumber = 750000     guessCount = 2 too HIGH!  Thread: GuessingGameShow_PlayerThread@139a3a7
P3: guessNumber = 625000     guessCount = 3 too HIGH!  Thread: GuessingGameShow_PlayerThread@139a3a7
P3: guessNumber = 562500     guessCount = 4 too HIGH!  Thread: GuessingGameShow_PlayerThread@139a3a7
P3: guessNumber = 531250     guessCount = 5 too HIGH!  Thread: GuessingGameShow_PlayerThread@139a3a7
P3: guessNumber = 515625     guessCount = 6 too LOW!  Thread: GuessingGameShow_PlayerThread@139a3a7
P3: guessNumber = 523437     guessCount = 7 too LOW!  Thread: GuessingGameShow_PlayerThread@139a3a7
P3: guessNumber = 527343     guessCount = 8 too LOW!  Thread: GuessingGameShow_PlayerThread@139a3a7
P3: guessNumber = 529296     guessCount = 9 too LOW!  Thread: GuessingGameShow_PlayerThread@139a3a7
P3: guessNumber = 530273     guessCount = 10 too HIGH!  Thread: GuessingGameShow_PlayerThread@139a3a7
P3: guessNumber = 529784     guessCount = 11 too LOW!  Thread: GuessingGameShow_PlayerThread@139a3a7
P3: guessNumber = 530028     guessCount = 12 too LOW!  Thread: GuessingGameShow_PlayerThread@139a3a7
P3: guessNumber = 530150     guessCount = 13 too LOW!  Thread: GuessingGameShow_PlayerThread@139a3a7
P3: guessNumber = 530211     guessCount = 14 too HIGH!  Thread: GuessingGameShow_PlayerThread@139a3a7
P3: guessNumber = 530180     guessCount = 15 too HIGH!  Thread: GuessingGameShow_PlayerThread@139a3a7
P3: guessNumber = 530165     guessCount = 16 too HIGH!  Thread: GuessingGameShow_PlayerThread@139a3a7
P3: guessNumber = 530157     guessCount = 17 too LOW!  Thread: GuessingGameShow_PlayerThread@139a3a7
P3: guessNumber = 530161     guessCount = 18 too LOW!  Thread: GuessingGameShow_PlayerThread@139a3a7
P3: guessNumber = 530163     guessCount = 19 Thread: GuessingGameShow_PlayerThread@139a3a7
>>>>> P3: You got with guessCount = 19    actualNumber = 530163
P8: guessNumber = 500000     guessCount = 1 too LOW!  Thread: GuessingGameShow_PlayerThread@cbae5f
P8: guessNumber = 750000     guessCount = 2 too HIGH!  Thread: GuessingGameShow_PlayerThread@cbae5f
P8: guessNumber = 625000     guessCount = 3 too LOW!  Thread: GuessingGameShow_PlayerThread@cbae5f
P8: guessNumber = 687500     guessCount = 4 too HIGH!  Thread: GuessingGameShow_PlayerThread@cbae5f
P8: guessNumber = 656250     guessCount = 5 too LOW!  Thread: GuessingGameShow_PlayerThread@cbae5f
P8: guessNumber = 671875     guessCount = 6 too HIGH!  Thread: GuessingGameShow_PlayerThread@cbae5f
P8: guessNumber = 664062     guessCount = 7 too LOW!  Thread: GuessingGameShow_PlayerThread@cbae5f
P8: guessNumber = 667968     guessCount = 8 too HIGH!  Thread: GuessingGameShow_PlayerThread@cbae5f
P8: guessNumber = 666015     guessCount = 9 too LOW!  Thread: GuessingGameShow_PlayerThread@cbae5f
P8: guessNumber = 666991     guessCount = 10 too HIGH!  Thread: GuessingGameShow_PlayerThread@cbae5f
P8: guessNumber = 666503     guessCount = 11 too LOW!  Thread: GuessingGameShow_PlayerThread@cbae5f
P8: guessNumber = 666747     guessCount = 12 too HIGH!  Thread: GuessingGameShow_PlayerThread@cbae5f
P8: guessNumber = 666625     guessCount = 13 too LOW!  Thread: GuessingGameShow_PlayerThread@cbae5f
P8: guessNumber = 666686     guessCount = 14 too HIGH!  Thread: GuessingGameShow_PlayerThread@cbae5f
P8: guessNumber = 666655     guessCount = 15 too LOW!  Thread: GuessingGameShow_PlayerThread@cbae5f
P8: guessNumber = 666670     guessCount = 16 too HIGH!  Thread: GuessingGameShow_PlayerThread@cbae5f
P8: guessNumber = 666662     guessCount = 17 too HIGH!  Thread: GuessingGameShow_PlayerThread@cbae5f
P8: guessNumber = 666658     guessCount = 18 too HIGH!  Thread: GuessingGameShow_PlayerThread@cbae5f
P8: guessNumber = 666656     guessCount = 19 too LOW!  Thread: GuessingGameShow_PlayerThread@cbae5f
P8: guessNumber = 666657     guessCount = 20 Thread: GuessingGameShow_PlayerThread@cbae5f
>>>>> P8: You got with guessCount = 20    actualNumber = 666657
P4: guessNumber = 500000     guessCount = 1 too HIGH!  Thread: GuessingGameShow_PlayerThread@7119a5
P4: guessNumber = 250000     guessCount = 2 too HIGH!  Thread: GuessingGameShow_PlayerThread@7119a5
P4: guessNumber = 125000     guessCount = 3 too LOW!  Thread: GuessingGameShow_PlayerThread@7119a5
P4: guessNumber = 187500     guessCount = 4 too HIGH!  Thread: GuessingGameShow_PlayerThread@7119a5
P4: guessNumber = 156250     guessCount = 5 too LOW!  Thread: GuessingGameShow_PlayerThread@7119a5
P4: guessNumber = 171875     guessCount = 6 too HIGH!  Thread: GuessingGameShow_PlayerThread@7119a5
P4: guessNumber = 164062     guessCount = 7 too HIGH!  Thread: GuessingGameShow_PlayerThread@7119a5
P4: guessNumber = 160156     guessCount = 8 too HIGH!  Thread: GuessingGameShow_PlayerThread@7119a5
P4: guessNumber = 158203     guessCount = 9 too LOW!  Thread: GuessingGameShow_PlayerThread@7119a5
P4: guessNumber = 159179     guessCount = 10 too LOW!  Thread: GuessingGameShow_PlayerThread@7119a5
P4: guessNumber = 159667     guessCount = 11 too HIGH!  Thread: GuessingGameShow_PlayerThread@7119a5
P4: guessNumber = 159423     guessCount = 12 too HIGH!  Thread: GuessingGameShow_PlayerThread@7119a5
P4: guessNumber = 159301     guessCount = 13 too HIGH!  Thread: GuessingGameShow_PlayerThread@7119a5
P4: guessNumber = 159240     guessCount = 14 too LOW!  Thread: GuessingGameShow_PlayerThread@7119a5
P4: guessNumber = 159270     guessCount = 15 too HIGH!  Thread: GuessingGameShow_PlayerThread@7119a5
P9: guessNumber = 281250     guessCount = 5 too HIGH!  Thread: GuessingGameShow_PlayerThread@124f39f
P9: guessNumber = 265625     guessCount = 6 too LOW!  Thread: GuessingGameShow_PlayerThread@124f39f
P9: guessNumber = 273437     guessCount = 7 too LOW!  Thread: GuessingGameShow_PlayerThread@124f39f
P9: guessNumber = 277343     guessCount = 8 too HIGH!  Thread: GuessingGameShow_PlayerThread@124f39f
P9: guessNumber = 275390     guessCount = 9 too LOW!  Thread: GuessingGameShow_PlayerThread@124f39f
P9: guessNumber = 276366     guessCount = 10 too LOW!  Thread: GuessingGameShow_PlayerThread@124f39f
P9: guessNumber = 276854     guessCount = 11 too LOW!  Thread: GuessingGameShow_PlayerThread@124f39f
P9: guessNumber = 277098     guessCount = 12 too HIGH!  Thread: GuessingGameShow_PlayerThread@124f39f
P9: guessNumber = 276976     guessCount = 13 too LOW!  Thread: GuessingGameShow_PlayerThread@124f39f
P9: guessNumber = 277037     guessCount = 14 too HIGH!  Thread: GuessingGameShow_PlayerThread@124f39f
P9: guessNumber = 277006     guessCount = 15 Thread: GuessingGameShow_PlayerThread@124f39f
>>>>> P9: You got with guessCount = 15    actualNumber = 277006
P5: guessNumber = 386615     guessCount = 19 too LOW!  Thread: GuessingGameShow_PlayerThread@561840
P5: guessNumber = 386616     guessCount = 20 Thread: GuessingGameShow_PlayerThread@561840
>>>>> P5: You got with guessCount = 20    actualNumber = 386616
P4: guessNumber = 159255     guessCount = 16 too LOW!  Thread: GuessingGameShow_PlayerThread@7119a5
P4: guessNumber = 159262     guessCount = 17 too LOW!  Thread: GuessingGameShow_PlayerThread@7119a5
P4: guessNumber = 159266     guessCount = 18 Thread: GuessingGameShow_PlayerThread@7119a5
>>>>> P4: You got with guessCount = 18    actualNumber = 159266


     Sort and Print threadArray:
Name = P2: ActualNumber = 745482   GuessNumber = 745482   GuessCount = 13   gpt/this = GuessingGameShow_PlayerThread@457f7b
Name = P9: ActualNumber = 277006   GuessNumber = 277006   GuessCount = 15   gpt/this = GuessingGameShow_PlayerThread@124f39f
Name = P4: ActualNumber = 159266   GuessNumber = 159266   GuessCount = 18   gpt/this = GuessingGameShow_PlayerThread@7119a5
Name = P10: ActualNumber = 320222   GuessNumber = 320222   GuessCount = 18   gpt/this = GuessingGameShow_PlayerThread@1777005
Name = P1: ActualNumber = 118145   GuessNumber = 118145   GuessCount = 19   gpt/this = GuessingGameShow_PlayerThread@a04ed6
Name = P3: ActualNumber = 530163   GuessNumber = 530163   GuessCount = 19   gpt/this = GuessingGameShow_PlayerThread@139a3a7
Name = P5: ActualNumber = 386616   GuessNumber = 386616   GuessCount = 20   gpt/this = GuessingGameShow_PlayerThread@561840
Name = P6: ActualNumber = 652016   GuessNumber = 652016   GuessCount = 20   gpt/this = GuessingGameShow_PlayerThread@1d3168b
Name = P7: ActualNumber = 667752   GuessNumber = 667752   GuessCount = 20   gpt/this = GuessingGameShow_PlayerThread@7cbe89
Name = P8: ActualNumber = 666657   GuessNumber = 666657   GuessCount = 20   gpt/this = GuessingGameShow_PlayerThread@cbae5f
*/



