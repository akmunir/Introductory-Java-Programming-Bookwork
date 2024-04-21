package Projects;

// GuessingGameShow_PlayerThread  and GuessingGameShowONE_CLIENT

// GuessingGameShow_PlayerThread  and GuessingGameShowONE_CLIENT_SHELL

/**
 * GuessingGameShow_PlayerThread:  A GameShowPlayer keeps guessing for a number, 1-max, with higher or lower, it adjusts accordingly,
 *
 *  It must do the following:
 *  (1)'implements' the Runnable Interface done
 *  (2) Has each of the following fields: done
 *      1 String for the name
 *      3 ints for the actualNumber, guessNumber, and guessCount
 *      2 ints for low + high
 *  (3) Has a constructor to assign each of the fields above. done
 *  (4) run():
 *          * Have a loop 'while (guessNumber!=actualNumber)' that keeps trying to guess the actualNumber via a binary guess approach.
 *          *  Output:
 *                 - name, guessNumber, guessCount, and clue (too LOW, too HIGH), and the thread itself
 *  (5) Finally, when it gets it right, Output the final guessCount and the actualNumber
 */

   class GuessingGameShow_PlayerThread implements Runnable
  {
      private String name;
      private int actualNumber, guessNumber, guessCount, low, high;

      public GuessingGameShow_PlayerThread(String name, int actualNumber, int high)
      {
        this.name = name;
        this.actualNumber = actualNumber;
        this.guessNumber = 0;
        this.guessCount = 0;
        this.low = 1;
        this.high = high;
      }
      public void run()
      {
          String hint = "";
        while (low <= high)
        {
            guessNumber = (low + high) / 2;
            guessCount++;
            if (guessNumber > actualNumber)
            {
                high = guessNumber - 1;
                hint = "HIGH";

            } else if (guessNumber < actualNumber) {
                hint = "LOW";
                low = guessNumber + 1;
            } else
                break;
            try {
                Thread.sleep((int) (Math.random() * 1000) + 1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(name + " Guess Number: " + guessNumber + " Guess Count: " + guessCount + " Too " + hint + " Thread: " + Thread.currentThread().getName());
        }
          System.out.println(">>>>>>>>>>>>>>>> " + name + " Got the Number! Guess Number: " + guessNumber + " Actual Number: " + actualNumber + " in " + guessCount + " tries >>>>>>>>>>>>>>>");
      }

// >>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<
// >>>>>>>>>> YOUR CODE HERE <<<<<<<<<<
// >>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<
  }


// ============================================================================
/**
 * GuessingGameShowONE_CLIENT: GuessingGameShow_Player's (3) are trying to guess a number 1-max,
 *                          and see who is the fastest at getting it right.  With each guess,
 *                          the player hits a delay/sleep for a random amount of time.
 *
 *     Directions: Create the following
 *          - 3 random numbers ranging from 1 - max (10000000)
 *          - 3 Runnables GuessingGameShow_PlayerThread("P1",n1,max) // For P2 + P3 too
 *          - 3 Threads for each and start() them
 *
 *     OUTPUT:  Possible output located at bottom of program:
 */


public class GuessingGameShowONE_CLIENT_THREADS_SHELL
{
    public static void main(String[] args)
    {
        int n1 = (int) (Math.random() * Integer.MAX_VALUE) + 1;
        int n2 = (int) (Math.random() * Integer.MAX_VALUE) + 1;
        int n3 = (int) (Math.random() * Integer.MAX_VALUE) + 1;
        Thread p1 = new Thread(new GuessingGameShow_PlayerThread("P1", n1, 1000000000));
        Thread p2 = new Thread(new GuessingGameShow_PlayerThread("P2", n2, 1000000000));
        Thread p3 = new Thread(new GuessingGameShow_PlayerThread("P3", n3, 1000000000));
        p1.start();
        p2.start();
        p3.start();

    } // main

}  // GuessingGameShowONE_CLIENT_THREADS

/*

Possible OUTPUT:
n1 = 23818693
n2 = 27993318
n3 = 91068742
P3: guessNumber = 50000000	 guessCount = 1 too LOW!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 75000000	 guessCount = 2 too LOW!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 87500000	 guessCount = 3 too LOW!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 93750000	 guessCount = 4 too HIGH!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 90625000	 guessCount = 5 too LOW!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 92187500	 guessCount = 6 too HIGH!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91406250	 guessCount = 7 too HIGH!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91015625	 guessCount = 8 too LOW!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91210937	 guessCount = 9 too HIGH!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91113281	 guessCount = 10 too HIGH!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91064453	 guessCount = 11 too LOW!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91088867	 guessCount = 12 too HIGH!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91076660	 guessCount = 13 too HIGH!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91070556	 guessCount = 14 too HIGH!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91067504	 guessCount = 15 too LOW!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91069030	 guessCount = 16 too HIGH!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91068267	 guessCount = 17 too LOW!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91068648	 guessCount = 18 too LOW!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91068839	 guessCount = 19 too HIGH!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91068743	 guessCount = 20 too HIGH!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91068695	 guessCount = 21 too LOW!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91068719	 guessCount = 22 too LOW!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91068731	 guessCount = 23 too LOW!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91068737	 guessCount = 24 too LOW!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91068740	 guessCount = 25 too LOW!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91068741	 guessCount = 26 too LOW!  Thread: GuessingGameShow_PlayerThread@10393eb
P3: guessNumber = 91068742	 guessCount = 27 Thread: GuessingGameShow_PlayerThread@10393eb
>>>>> P3: You got with guessCount = 27    actualNumber = 91068742
P1: guessNumber = 50000000	 guessCount = 1 too HIGH!  Thread: GuessingGameShow_PlayerThread@13a1291
P2: guessNumber = 50000000	 guessCount = 1 too HIGH!  Thread: GuessingGameShow_PlayerThread@29b13b
P1: guessNumber = 25000000	 guessCount = 2 too HIGH!  Thread: GuessingGameShow_PlayerThread@13a1291
P2: guessNumber = 25000000	 guessCount = 2 too LOW!  Thread: GuessingGameShow_PlayerThread@29b13b
P1: guessNumber = 12500000	 guessCount = 3 too LOW!  Thread: GuessingGameShow_PlayerThread@13a1291
P2: guessNumber = 37500000	 guessCount = 3 too HIGH!  Thread: GuessingGameShow_PlayerThread@29b13b
P1: guessNumber = 18750000	 guessCount = 4 too LOW!  Thread: GuessingGameShow_PlayerThread@13a1291
P2: guessNumber = 31250000	 guessCount = 4 too HIGH!  Thread: GuessingGameShow_PlayerThread@29b13b
P1: guessNumber = 21875000	 guessCount = 5 too LOW!  Thread: GuessingGameShow_PlayerThread@13a1291
P2: guessNumber = 28125000	 guessCount = 5 too HIGH!  Thread: GuessingGameShow_PlayerThread@29b13b
P1: guessNumber = 23437500	 guessCount = 6 too LOW!  Thread: GuessingGameShow_PlayerThread@13a1291
P2: guessNumber = 26562500	 guessCount = 6 too LOW!  Thread: GuessingGameShow_PlayerThread@29b13b
P1: guessNumber = 24218750	 guessCount = 7 too HIGH!  Thread: GuessingGameShow_PlayerThread@13a1291
P2: guessNumber = 27343750	 guessCount = 7 too LOW!  Thread: GuessingGameShow_PlayerThread@29b13b
P2: guessNumber = 27734375	 guessCount = 8 too LOW!  Thread: GuessingGameShow_PlayerThread@29b13b
P2: guessNumber = 27929687	 guessCount = 9 too LOW!  Thread: GuessingGameShow_PlayerThread@29b13b
P1: guessNumber = 23828125	 guessCount = 8 too HIGH!  Thread: GuessingGameShow_PlayerThread@13a1291
P2: guessNumber = 28027343	 guessCount = 10 too HIGH!  Thread: GuessingGameShow_PlayerThread@29b13b
P1: guessNumber = 23632812	 guessCount = 9 too LOW!  Thread: GuessingGameShow_PlayerThread@13a1291
P2: guessNumber = 27978515	 guessCount = 11 too LOW!  Thread: GuessingGameShow_PlayerThread@29b13b
P1: guessNumber = 23730468	 guessCount = 10 too LOW!  Thread: GuessingGameShow_PlayerThread@13a1291
P2: guessNumber = 28002929	 guessCount = 12 too HIGH!  Thread: GuessingGameShow_PlayerThread@29b13b
P1: guessNumber = 23779296	 guessCount = 11 too LOW!  Thread: GuessingGameShow_PlayerThread@13a1291
P2: guessNumber = 27990722	 guessCount = 13 too LOW!  Thread: GuessingGameShow_PlayerThread@29b13b
P2: guessNumber = 27996825	 guessCount = 14 too HIGH!  Thread: GuessingGameShow_PlayerThread@29b13b
P2: guessNumber = 27993773	 guessCount = 15 too HIGH!  Thread: GuessingGameShow_PlayerThread@29b13b
P2: guessNumber = 27992247	 guessCount = 16 too LOW!  Thread: GuessingGameShow_PlayerThread@29b13b
P2: guessNumber = 27993010	 guessCount = 17 too LOW!  Thread: GuessingGameShow_PlayerThread@29b13b
P2: guessNumber = 27993391	 guessCount = 18 too HIGH!  Thread: GuessingGameShow_PlayerThread@29b13b
P2: guessNumber = 27993200	 guessCount = 19 too LOW!  Thread: GuessingGameShow_PlayerThread@29b13b
P2: guessNumber = 27993295	 guessCount = 20 too LOW!  Thread: GuessingGameShow_PlayerThread@29b13b
P2: guessNumber = 27993343	 guessCount = 21 too HIGH!  Thread: GuessingGameShow_PlayerThread@29b13b
P2: guessNumber = 27993319	 guessCount = 22 too HIGH!  Thread: GuessingGameShow_PlayerThread@29b13b
P2: guessNumber = 27993307	 guessCount = 23 too LOW!  Thread: GuessingGameShow_PlayerThread@29b13b
P2: guessNumber = 27993313	 guessCount = 24 too LOW!  Thread: GuessingGameShow_PlayerThread@29b13b
P2: guessNumber = 27993316	 guessCount = 25 too LOW!  Thread: GuessingGameShow_PlayerThread@29b13b
P2: guessNumber = 27993317	 guessCount = 26 too LOW!  Thread: GuessingGameShow_PlayerThread@29b13b
P2: guessNumber = 27993318	 guessCount = 27 Thread:
GuessingGameShow_PlayerThread@29b13b
>>>>> P2: You got with guessCount = 27    actualNumber = 27993318
P1: guessNumber = 23803710	 guessCount = 12 too LOW!  Thread: GuessingGameShow_PlayerThread@13a1291
P1: guessNumber = 23815917	 guessCount = 13 too LOW!  Thread: GuessingGameShow_PlayerThread@13a1291
P1: guessNumber = 23822021	 guessCount = 14 too HIGH!  Thread: GuessingGameShow_PlayerThread@13a1291
P1: guessNumber = 23818969	 guessCount = 15 too HIGH!  Thread: GuessingGameShow_PlayerThread@13a1291
P1: guessNumber = 23817443	 guessCount = 16 too LOW!  Thread: GuessingGameShow_PlayerThread@13a1291
P1: guessNumber = 23818206	 guessCount = 17 too LOW!  Thread: GuessingGameShow_PlayerThread@13a1291
P1: guessNumber = 23818587	 guessCount = 18 too LOW!  Thread: GuessingGameShow_PlayerThread@13a1291
P1: guessNumber = 23818778	 guessCount = 19 too HIGH!  Thread: GuessingGameShow_PlayerThread@13a1291
P1: guessNumber = 23818682	 guessCount = 20 too LOW!  Thread: GuessingGameShow_PlayerThread@13a1291
P1: guessNumber = 23818730	 guessCount = 21 too HIGH!  Thread: GuessingGameShow_PlayerThread@13a1291
P1: guessNumber = 23818706	 guessCount = 22 too HIGH!  Thread: GuessingGameShow_PlayerThread@13a1291
P1: guessNumber = 23818694	 guessCount = 23 too HIGH!  Thread: GuessingGameShow_PlayerThread@13a1291
P1: guessNumber = 23818688	 guessCount = 24 too LOW!  Thread: GuessingGameShow_PlayerThread@13a1291
P1: guessNumber = 23818691	 guessCount = 25 too LOW!  Thread: GuessingGameShow_PlayerThread@13a1291
P1: guessNumber = 23818692	 guessCount = 26 too LOW!  Thread: GuessingGameShow_PlayerThread@13a1291
P1: guessNumber = 23818693	 guessCount = 27 Thread: GuessingGameShow_PlayerThread@13a1291
>>>>> P1: You got with guessCount = 27    actualNumber = 23818693

 */

