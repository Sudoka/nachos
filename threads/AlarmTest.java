package nachos.threads;

import nachos.machine.*;
import java.util.Random;


/**
 *  * A Tester for the Alarm class
 *   */
public class AlarmTest {

	private static class interrupter implements Runnable {

		private String name;

		private int length;


		public interrupter(String name, int length) {
			this.name = name;

			this.length = length;
		} 


		public void run() {

			System.out.println("** "+ name +": starting.");

			System.out.println("** "+ name +": waiting until time "+
					(Machine.timer().getTime() + length));
			ThreadedKernel.alarm.waitUntil(length);
			System.out.println("** "+ name +": woken! (time = "+Machine.timer().getTime()+")");


		}


	}

	public static void runTest() {

		System.out.println("**** Alarm testing begins ****");

		/* Create 10 interrupters */
		KThread interrupterThreads[] = new KThread[10];
		for (int i=0; i < 10; i++) {
			interrupterThreads[i] = new KThread(new interrupter("interrupterThread #"+i, 1000*i));
			interrupterThreads[i].setName("HiccupThread #"+i);
			interrupterThreads[i].fork();  
		}

		/* suspend the main thread until the maximum possible time */
		ThreadedKernel.alarm.waitUntil(10000 * (11) * 2);

		System.out.println("**** Alarm testing end ****");

	}


}
