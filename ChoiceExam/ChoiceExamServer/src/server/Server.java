package server;

import common.HelloWorldServer;

import java.io.*;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Created by eloigabal on 05/10/2019.
 */

public class Server {
    public static boolean start = false;
    private static Registry startRegistry(Integer port)
            throws RemoteException {
        if(port == null) {
            port = 1099;
        }
        try {
            Registry registry = LocateRegistry.createRegistry(port);
            registry.list( );
            return registry;
        }
        catch (RemoteException ex) {
            // No valid registry at that port.
            Registry registry= LocateRegistry.createRegistry(port);
            return registry;
        }
    }
    public static class Interrupted {
        //variable to change when start


        private static class Interrupt extends Thread {
            String interrupt_key = null;
            Object semaphore = null;

            //semaphore must be the syncronized object
            private Interrupt(Object semaphore, String interrupt_key) {
                this.semaphore = semaphore;
                this.interrupt_key = interrupt_key;
            }

            public void run() {
                while (true) {
                    //read the key
                    Scanner scanner = new Scanner(System.in);
                    String x = scanner.nextLine();
                    System.out.println(x);
                    if (x.equals(this.interrupt_key)) {
                        //if is the key we expect, change the variable, notify and return(finish thread)
                        synchronized (this.semaphore) {
                            start = true;
                            this.semaphore.notify();
                            return;
                        }
                    }
                }
            }
        }
    }
    public static void main(String args[]) {
        System.err.println("Server ready. register Students");
        try {
            Registry registry = startRegistry(null);
            server.HelloWorldImplementation obj = new server.HelloWorldImplementation();
            registry.bind("Hello", (HelloWorldServer) obj);
            String start_word = "start";
            Interrupted.Interrupt interrupt = new Interrupted.Interrupt(obj, start_word);
            //The tread starts reading for the key
            interrupt.start();
            synchronized (obj){
                while(!start){
                    System.out.println("Write \""+ start_word+"\" to start the exam");

                    obj.wait();
                    obj.startExam();
                }
            }
            start = false;
            String End_word = "end";
            Interrupted.Interrupt interruptEnd = new Interrupted.Interrupt(obj, End_word);
            //The tread starts reading for the key
            interruptEnd.start();
            synchronized (obj){
                while(!start){
                    System.out.println("Write \""+ End_word+"\" to start the exam");

                    obj.wait();
                    obj.endExam();
                }
            }

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString()); e.printStackTrace();
        }


    }
}

