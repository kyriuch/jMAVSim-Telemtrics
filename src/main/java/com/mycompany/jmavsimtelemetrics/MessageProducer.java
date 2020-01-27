/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jmavsimtelemetrics;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author root
 */
public class MessageProducer implements Runnable {
    
    private ConcurrentLinkedQueue<Message> queue;
    private boolean isRunning = false;
    private DatagramSocket datagramSocket;
    
    public MessageProducer(ConcurrentLinkedQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {     
        isRunning = true;
        
        try {
            datagramSocket = new DatagramSocket(14550);
            
            byte[] receive = new byte[512];
            
            while(isRunning) {
                DatagramPacket datagramPacket = new DatagramPacket(receive, receive.length);
                
                try {
                    datagramSocket.receive(datagramPacket);
                    
                    if(datagramPacket.getLength() > 0) {
                        queue.add(new Message(datagramPacket));
                    }
                } catch(IOException ex) {
                    ex.printStackTrace();
                } 
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
    }
    
    public void shutdown() {
        isRunning = false;
        
        if(datagramSocket != null) {
            datagramSocket.close();
        }
    }
}
