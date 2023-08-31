/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arst.concprg.prodcons;

import java.util.Queue;
import java.util.Random;

/**
 * @author hcadavid
 */
public class Producer extends Thread {

    private final Queue<Integer> queue;
    private final long stockLimit;
    private int dataSeed = 0;
    private Random rand = null;

    public Producer(Queue<Integer> queue, long stockLimit) {
        this.queue = queue;
        queue.stream().limit(12);
        rand = new Random(System.currentTimeMillis());
        this.stockLimit = stockLimit;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (queue) {
                    while (queue.size() == stockLimit) {
                        System.out.println("Está Llena");
                        queue.wait();
                    }
                    Thread.sleep(1000);
                    dataSeed = dataSeed + rand.nextInt(100);
                    System.out.println("Producer added " + dataSeed);
                    queue.add(dataSeed);
                    queue.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
