/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package projeto;
import java.rmi.*;
/**
 *
 * @author João
 */
public interface InterfaceDados extends Remote{
    //BaseDados bD = new BaseDados();
    
    public void leFicheiro() throws RemoteException;
    public String funcaoA() throws RemoteException;
    public Projeto funcaoB() throws RemoteException;
    public BaseDados getBD() throws RemoteException;
    public void setBD(BaseDados bD) throws RemoteException;
}
