package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by eloigabal on 12/10/2019.
 */
public interface HelloWorldClient extends Remote {

    public void notifyRegist() throws RemoteException;
    public void notifyExamStarted() throws RemoteException;
    public void notifyStartExam() throws RemoteException;
}
