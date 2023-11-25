package com.newenergy.arfors.pcpult;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int serverPort;

    public UDPClient(String serverIp, int serverPort) {
        try {
            this.serverAddress = InetAddress.getByName(serverIp);
            this.serverPort = serverPort;
            this.socket = new DatagramSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendData(String message) {
        try {
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            socket.send(sendPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}
