package server;

import common.HelloWorldClient;
import common.HelloWorldServer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by eloigabal on 05/10/2019.
 */

public class HelloWorldImplementation extends UnicastRemoteObject implements HelloWorldServer {
    private boolean started = false;
    private boolean end = false;
    private String [][] quests;
    private ArrayList<HelloWorldClient> clients = new ArrayList<HelloWorldClient>();
    private HashMap<String,Integer[]> grade = new HashMap<String,Integer[]>();//ID,QUEST,POINTS
    public HelloWorldImplementation() throws RemoteException {
        quests = new String[3][5];
        String csvFile = "resources/test.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        Integer index = 0;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                quests[index] = line.split(cvsSplitBy);
                index++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public void register(HelloWorldClient client) throws RemoteException {
        if (!started) {
            System.out.println("Registering Student, Nº registered students: "+clients.size());

            this.grade.put(client.notifyRegist(), new Integer[]{0,0});
            this.clients.add(client);
        }else{
            client.notifyExamStarted();
        }
    }
    public void startExam() throws RemoteException{
        started = true;
        notify_StartExam();
    }

    @Override
    public void endExam() throws RemoteException {
        end = true;
    }

    public void notify_StartExam() throws RemoteException{
        for (HelloWorldClient client : clients){
            new Thread("" + client.getID()){
                public void run(){
                    while(grade.get(client.getID())[0] != quests.length && !end) {
                        try {
                            sendAnswer(client.getID(), client.notifyStartExam(getQuest(client.getID())));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        client.notifyGrade(client.getID()+" note: "+grade.get(client.getID())[1]+"/"+quests.length);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            }
    }
    public String[] getQuest(String id)throws RemoteException{
        Integer[] quest = this.grade.get(id);
        String[] pregunta = Arrays.copyOfRange(this.quests[quest[0]],0,4);
        return pregunta;
    }
    public void sendAnswer(String id, Integer resp)throws RemoteException{
        Integer[] quest = this.grade.get(id);
        if (this.quests[quest[0]][4].equals(resp.toString())){
            quest[1]++;
        }
        quest[0]++;
        this.grade.put(id, quest);

    }
}
