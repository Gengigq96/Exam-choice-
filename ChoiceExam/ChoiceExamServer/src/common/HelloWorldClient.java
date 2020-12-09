package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by eloigabal on 12/10/2019.
 */
public interface HelloWorldClient extends Remote {

    public String notifyRegist() throws RemoteException;
    public String getID();
    public void notifyExamStarted() throws RemoteException;
    public Integer notifyStartExam(String[] pregunta) throws RemoteException;
    public void notifyGrade(String nota) throws RemoteException;
}
