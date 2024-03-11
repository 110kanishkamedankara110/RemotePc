/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phoenix.rmi;

import java.awt.image.BufferedImage;
import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.ImageIcon;

/**
 *
 * @author BLACKBOX
 */
public interface Mess extends Remote{
    public void call(int keyCode)throws RemoteException; 
    public void release(int keyCode)throws RemoteException; 
    public void cast(ImageIcon ii)throws RemoteException;
    public void connect(String det)throws RemoteException;
    public void mouseMove(float x, float y)throws RemoteException;
    public void mouseClick(int button)throws RemoteException;
    public void mouseRelease(int button)throws RemoteException;
    public void mouseDragg(float x, float y,int button)throws RemoteException;

}
