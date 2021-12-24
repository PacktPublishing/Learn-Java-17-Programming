package com.packt.learnjava.ch11_network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;


// Can be run only after TcpServer is started
public class TcpClient {
    public static void main(String[] args) {
        try(Socket s = new Socket("localhost",3333);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in))){

            String prompt = "Say something: ";
            System.out.print(prompt);
            String msg;
            while ((msg = console.readLine()) != null) {
                dout.writeUTF( msg);
                dout.flush();
                if (msg.equalsIgnoreCase("end")) {
                    break;
                }

                msg = dis.readUTF();
                System.out.println("Server said: " +msg);
                if (msg.equalsIgnoreCase("end")) {
                    break;
                }
                System.out.print(prompt);
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
