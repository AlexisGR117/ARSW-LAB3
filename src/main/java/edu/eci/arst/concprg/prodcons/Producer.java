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
    private int dataSeed;
    private Random rand = null;

    public Producer(Queue<Integer> queue) {
        this.queue = queue;
        rand = new Random(System.currentTimeMillis());
        dataSeed = rand.nextInt(100);
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (queue) {
                    while (!queue.offer(dataSeed)) queue.wait();
                    System.out.println("Producer added " + dataSeed);
                    queue.notifyAll();
                    Thread.sleep(1000);
                    dataSeed = dataSeed + rand.nextInt(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
