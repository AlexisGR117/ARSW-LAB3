/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arst.concprg.prodcons;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StartProduction {


    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedBlockingQueue<>();


        Producer producer = new Producer(queue, 5);
        Consumer consumer = new Consumer(queue);
        //let the producer create products for 5 seconds (stock).
        producer.start();
        consumer.start();

    }


}