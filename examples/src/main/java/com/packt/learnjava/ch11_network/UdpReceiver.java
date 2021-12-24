package com.packt.learnjava.ch11_network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpReceiver {
    public static void main(String[] args){
        try(DatagramSocket ds = new DatagramSocket(3333)){
            DatagramPacket dp = new DatagramPacket(new byte[30], 30);
            ds.receive(dp);
            int i = 1;
            for(byte b: dp.getData()){
                System.out.print(Character.toString(b));
                if(i++ == dp.getLength()){
                    break;
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
