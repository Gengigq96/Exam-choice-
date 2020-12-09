package Client;

import common.HelloWorldClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * Created by eloigabal on 12/10/2019.
 */
public class HelloWorldClientImpl extends UnicastRemoteObject implements HelloWorldClient{
    String universityID = "";
    public HelloWorldClientImpl() throws RemoteException {}

    public void notifyRegist() throws RemoteException{
        while (universityID.length()<4) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your University ID: ");
            universityID = sc.nextLine();
        }

        System.out.println("Client registered, waiting for notification");
    }
    public void notifyExamStarted() throws RemoteException{

        System.out.println("The exam has already started");

    }
    public void notifyStartExam() throws RemoteException{

        System.out.println("EXAM START");

    }

}
