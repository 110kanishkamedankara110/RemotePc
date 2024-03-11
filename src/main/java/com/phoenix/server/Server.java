/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phoenix.server;

import com.github.alexdlaird.ngrok.NgrokClient;
import com.github.alexdlaird.ngrok.protocol.CreateTunnel;
import com.github.alexdlaird.ngrok.protocol.Proto;
import com.github.alexdlaird.ngrok.protocol.Tunnel;
import com.phoenix.gui.Home;
import com.phoenix.rmi.Mess;
import com.phoenix.rmi.MessImpl;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BLACKBOX
 */
public class Server {

    public static void startServer() {
        new Thread(() -> {
            try {
                int port = (int) (Math.random() * (9999 - 1000) + 1000);
        
                final NgrokClient ngrokClient = new NgrokClient.Builder().build();
                ngrokClient.setAuthToken("2dPiQB0CBkmG2G10aXxmQs2AfnK_TUVFu6umJcAgSnjhBuHB");
                final CreateTunnel sshCreateTunnel = new CreateTunnel.Builder()
                        .withProto(Proto.TCP)
                        .withAddr(1099)
                        .build();
                final Tunnel sshTunnel = ngrokClient.connect(sshCreateTunnel);

                Registry reg = LocateRegistry.createRegistry(1099);
                Mess mess = new MessImpl();
                reg.bind("message", mess);
                Home.url = sshTunnel.getPublicUrl().replace("tcp://", "");
                
                System.setProperty("java.rmi.server.hostname", sshTunnel.getPublicUrl().split(":")[1].substring(2));
                
                System.out.println("Server Started At : " + sshTunnel.getPublicUrl().replace("tcp://", ""));
                
                

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }

}
