/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package updmulticast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

/**
 *
 * @author pedro
 */
public class Cliente {
    MulticastSocket socket = null;
    byte[] buffer = null;
    DatagramPacket packet = null;
    InetAddress ip = null;
    
    public static void main(String[] args){
        
    }
    
    private void initializeVaribale(){
        try{
            socket = new MulticastSocket(Constants.PORT);
            ip = InetAddress.getByName(Constants.IP);
            buffer = new byte[Constants.BUFFER_SIZE];
            
        }catch(SocketException ex){
            log("initializeVarible : "+ ex.toString());
        }catch(IOException ex){
            log("initializeVarible : "+ ex.toString());
        }
    }
    private String receveData(){
        String line ="";
        try{
            packet= new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            line = new String (packet.getData(),0,packet.getLength());
            
        }catch(IOException ex){
            log("receiveData : "+ ex.toString());
        }
        return line;
    }
    
    private void joinGroup(){
        try{
            socket.joinGroup(ip);
            log("Localizando");
        }catch (IOException ex) {
            log("joinGroup : "+ ex.toString());
        }
    }
    private void connecting(){
        joinGroup();
        while (true) {
            String line = receveData();
            log("Recebido ; "+ line);
            
        }
    }
    private void log(String message){
        System.out.println("message");
    }
}
