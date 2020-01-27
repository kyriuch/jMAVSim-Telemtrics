/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.net.DatagramPacket;

/**
 *
 * @author root
 */
public class Message {
    private byte payloadLength;
    private byte systemId;
    private byte componentId;
    private byte messageId;
    private byte[] payload;
    private short crc;
    
    public Message(DatagramPacket datagramPacket) {
        constructMessage(datagramPacket.getData(), datagramPacket.getLength());
    }
    
    private void constructMessage(byte[] data, int length) {
        payloadLength = data[1];
        systemId = data[3];
        componentId = data[4];
        messageId = data[5];
        payload = new byte[payloadLength];
        
        for(int i = 6; i < length - 2; i++) 
        {
            payload[i - 6] = data[i];
        }
        
        crc = (short)(data[length - 2] << 8 | data[length - 1]);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        
        ret.append("[Length ").append(payloadLength).append(" - ");
        ret.append("System ID ").append(systemId).append(" - ");
        ret.append("Component ID ").append(componentId).append(" - ");
        ret.append("Message ID ").append(messageId).append(" - ");
        ret.append("CRC ").append(String.format("%04X", crc)).append("]\n[Payload ");
        
        for(int i = 0; i < payload.length; i++) 
        {
            ret.append(String.format("%02X", payload[i]));
            
            if(i + 1 < payload.length) {
                ret.append(" ");
            }
        }
        
        return ret.append("]\n").toString();
    }
}
