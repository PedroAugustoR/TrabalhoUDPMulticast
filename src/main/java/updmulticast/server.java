/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package updmulticast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class server {
    MulticastSocket socket = null;
    byte[] buffer = null;
    DatagramSocket receivePacket = null;
    Scanner scan = null; 
    
    public static void main(String[] args){
        server server = new server ();
        server.initializeVaribale();
        server.connecting();
    }
    
    private void initializeVaribale(){
        try {
            socket = new MulticastSocket();
            buffer = new byte[Constants.BUFFER_SIZE];
            scan = new Scanner (System.in);
            
            log ("executando ervidor.");
        }catch (SocketException ex) {
            log ("initializeVaribale : "+ ex.toString());       
        }
        catch (IOException ex) {
            log ("initializeVaribale : "+ ex.toString());
        }
    }
    private String readFromKeyboard(){
        String line = scan.nextLine();
        return line;
    }
    
    private void send (String message){
        try{
            InetAddress ip = InetAddress.getByName(Constants.IP);
            
            buffer = message.getBytes();
            DatagramPacket packetSend = new DatagramPacket(buffer, buffer.length, ip, Constants.PORT);
            
            socket.send(packetSend);
            log("Mensagem enviada");
            
        }catch(IOException ex){
            log("Enviar : "+ ex.toString());
        }
    }
    private void connecting(){
        while (true){
            String data = readFromKeyboard();
            send(data);
            
            buffer = new byte[Constants.BUFFER_SIZE];
        }
    }
    private void log(String message){
        System.out.println(message);
    }
 
}
