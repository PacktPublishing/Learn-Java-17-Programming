package com.packt.learnjava.ch11_network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpReceiver2 {
    public static void main(String[] args){
        try(DatagramSocket ds = new DatagramSocket(3333)){
            DatagramPacket dp = new DatagramPacket(new byte[30], 30);
            while (true){
                ds.receive(dp);
                for(byte b: dp.getData()){
                    System.out.print(Character.toString(b));
                }
                System.out.println();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
