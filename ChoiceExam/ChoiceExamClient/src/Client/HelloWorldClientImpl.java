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
    public String getID() throws RemoteException{
        return universityID;
    }
    public String notifyRegist() throws RemoteException{
        while (universityID.length()<4) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your University ID: ");
            universityID = sc.nextLine();
        }

        System.out.println("Client registered, waiting for notification");
        return universityID;
    }
    public void notifyExamStarted() throws RemoteException{

        System.out.println("The exam has already started");

    }
    public Integer notifyStartExam(String[] pregunta) throws RemoteException{
        Integer resposta = 0;
        for (int i = 0; i< pregunta.length;i++){
            System.out.println(pregunta[i]);
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your answer: ");
        resposta = sc.nextInt();
        return resposta;

    }
    public void notifyGrade(String nota) throws RemoteException{
        System.out.println(nota);
    }

}
