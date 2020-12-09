package common;

import common.HelloWorldClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by eloigabal on 05/10/2019.
 */
public interface HelloWorldServer extends Remote{
    void register(HelloWorldClient client) throws RemoteException;
    void startExam() throws RemoteException;
    void endExam() throws RemoteException;
    void notify_StartExam() throws RemoteException;
    void sendAnswer(String id, Integer resp) throws RemoteException;
}
