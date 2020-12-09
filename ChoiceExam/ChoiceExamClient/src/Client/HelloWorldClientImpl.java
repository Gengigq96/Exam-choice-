package Client;

import common.HelloWorldClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by eloigabal on 12/10/2019.
 */
public class HelloWorldClientImpl extends UnicastRemoteObject implements HelloWorldClient{
    public HelloWorldClientImpl() throws RemoteException {}

    public void notifyHello(String st) throws RemoteException{
        System.out.println("Client recieved "+st+" message from server");
    }

}
