/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author root
 */
public class MessageConsumer implements Runnable {

    private ConcurrentLinkedQueue<Message> queue;
    private boolean isRunning = false;
    private List<IMessageReceiver> messageReceivers = new ArrayList<>();
    
    public MessageConsumer(ConcurrentLinkedQueue<Message> queue) {
        this.queue = queue;
    }
    
    @Override
    public void run() {
        isRunning = true;
        
        while(isRunning) {
            if(!queue.isEmpty()) {
                Message message = queue.poll();
                
                if(!messageReceivers.isEmpty()) {
                    for(int i = 0; i < messageReceivers.size(); i++) {
                        messageReceivers.get(i).ReceiveMessage(message);
                    }
                }
            }
        }
    }
    
    public void shutdown() {
        isRunning = false;
    }
    
    public void addMessageReceiver(IMessageReceiver messageReceiver) {
        messageReceivers.add(messageReceiver);
    }
}
