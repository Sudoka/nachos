package nachos.threads;

import nachos.machine.*;

/**
 *  * A Tester for the KThread class. 
 *   * Really, this tests the join() implementation.
 *    */
public class KThreadTest {
        
        private static class joiner implements Runnable {
                    joiner(String name, KThread joinTo){
                                    this.name = name;
                                                this.joinTo = joinTo;
                                                        }
                            
                            public void run(){
                                            if(joinTo != null){
                                                                System.out.println(name + " joining to "+ joinTo.toString());
                                                                                joinTo.join();
                                                                                                System.out.println(name + " joined to "+ joinTo.toString());

                                                                                                                System.out.println(name + "done");
                                                                                                                            }

                                                    }
                                    
                                    private KThread joinTo;
                                            private String name;
                                                    
                                                    
                                                }
            
            
            private static class PingTest implements Runnable {
                        PingTest(int which) {
                                        this.which = which;
                                                }
                                
                                public void run() {
                                                for (int i=0; i<5; i++) {
                                                                System.out.println("*** thread " + which + " looped "
                                                                                               + i + " times");
                                                                            KThread.yield();
                                                                                        }
                                                            System.out.println("*** thread " + which + " done ");
                                                                        
                                                                    }

                                        private int which;
                                                }
                public static void runTest() {
                            KThread a = new KThread(new PingTest(1));
                                    a.setName("(forked) thread1");
                                            a.fork();
                                                    
                                                    KThread b = new KThread(new PingTest(2));
                                                            b.setName("(forked) thread2");
                                                                    
                                                                    KThread c = new KThread(new joiner("thread 3", a));
                                                                            c.setName("string 3");
                                                                                    KThread d = new KThread(new joiner("thread 4", b));
                                                                                            d.setName("string 4");
                                                                                                    
                                                                                                    
                                                                                                    c.fork();
                                                                                                            b.fork();
                                                                                                                    b.join();
                                                                                                                            d.fork();
                                                                                                                                    
                                                                                                                                    
                                                                                                                                }
                    
}


