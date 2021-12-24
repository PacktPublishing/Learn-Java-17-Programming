package com.packt.learnjava.ch11_network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args){
        try(Socket s = new ServerSocket(3333).accept();
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in))){
            while(true){
                String msg = dis.readUTF();
                System.out.println("Client said: " + msg);
                if("end".equalsIgnoreCase(msg)){
                    break;
                }

                System.out.print("Say something: ");
                msg = console.readLine();
                dout.writeUTF(msg);
                dout.flush();
                if("end".equalsIgnoreCase(msg)){
                    break;
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
