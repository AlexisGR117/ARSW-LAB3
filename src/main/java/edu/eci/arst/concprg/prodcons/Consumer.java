/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arst.concprg.prodcons;

import java.util.Queue;

/**
 * @author hcadavid
 */
public class Consumer extends Thread {

    private final Queue<Integer> queue;


    public Consumer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        System.out.println("Está vacía");
                        queue.wait();
                    }
                    Thread.sleep(1000);
                    System.out.println("Consumer consumes " + queue.poll());
                    queue.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }

        }
    }
}
