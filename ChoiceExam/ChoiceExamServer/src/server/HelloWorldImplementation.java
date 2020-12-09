package server;

import common.HelloWorldClient;
import common.HelloWorldServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by eloigabal on 05/10/2019.
 */

public class HelloWorldImplementation extends UnicastRemoteObject implements HelloWorldServer {
    private boolean started = false;
    public HelloWorldImplementation() throws RemoteException {}

    private ArrayList<HelloWorldClient> clients = new ArrayList<HelloWorldClient>();

    public void register(HelloWorldClient client) throws RemoteException {
        if (!started) {
            System.out.println("Registering Student, Nº registered students: "+clients.size());
            client.notifyRegist();
            this.clients.add(client);
        }else{
            client.notifyExamStarted();
        }
    }
    public void startExam() throws RemoteException{
        started = true;
        notify_StartExam();
    }

    public void notify_StartExam() throws RemoteException{
        for (HelloWorldClient client : clients){
            client.notifyStartExam();
        }
    }
}
