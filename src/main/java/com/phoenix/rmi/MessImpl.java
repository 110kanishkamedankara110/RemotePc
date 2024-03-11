/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phoenix.rmi;

import com.phoenix.gui.Home;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author BLACKBOX
 */
public class MessImpl extends UnicastRemoteObject implements Mess {

    static Robot r;

    @Override
    public void call(int keyCode) {

        new Thread(() -> {
            try {
                r.keyPress(keyCode);
            } catch (Exception ex) {
                Logger.getLogger(MessImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();

    }
    @Override
    public void release(int keyCode) {

        new Thread(() -> {
            try {
                r.keyRelease(keyCode);
            } catch (Exception ex) {
                Logger.getLogger(MessImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();

    }

    public MessImpl() throws RemoteException {
        try {
            r = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(MessImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void cast(ImageIcon ii) throws RemoteException {
        com.phoenix.gui.Home.home.jLabel1.setIcon(ii);
    }

    @Override
    public void connect(String det) throws RemoteException {
        Home.home.connect(det, "Server");

    }

    @Override
    public void mouseMove(float x, float y) throws RemoteException {
        new Thread(() -> {
            try {
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                r.mouseMove((int) (dim.width * x), (int) (dim.height * y));

            } catch (Exception ex) {
                Logger.getLogger(MessImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    @Override
    public void mouseClick(int button) throws RemoteException {
        new Thread(() -> {
            try {

                if (button == 1) {
                    r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                }
                if (button == 3) {
                    r.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                }

            } catch (Exception ex) {
                Logger.getLogger(MessImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    @Override
    public void mouseRelease(int button) throws RemoteException {

        new Thread(() -> {
            try {

                if (button == 1) {
                    System.out.println("Released.." + button);

                    r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                }
                if (button == 3) {
                    System.out.println("Released.." + button);

                    r.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                }

            } catch (Exception ex) {
                Logger.getLogger(MessImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    @Override
    public void mouseDragg(float x, float y, int button) throws RemoteException {
        new Thread(() -> {
            try {

                
                r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
              
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                r.mouseMove((int) (dim.width * x), (int) (dim.height * y));
            } catch (Exception ex) {
                Logger.getLogger(MessImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

}
