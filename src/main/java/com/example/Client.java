package com.example;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    String nomeServer = "127.0.0.1";
    int portaServer = 6789;
    Socket mioSocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutadalserver;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;

    public Socket connetti(){
        System.out.println("2- CLIENT partito in esecuzione");
        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            mioSocket = new Socket(nomeServer, portaServer);
            outVersoServer = new DataOutputStream(mioSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("host non riconosciuto");
            // TODO: handle exception
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
            System.out.println("errore durante la conessione");
            System.exit(1);
        }
        return mioSocket;
    }

    public void comunica(){
        try {
            System.out.println("4 inserisci la stampa da trasmettere al server"+'\n');
            stringaUtente = tastiera.readLine();
            System.out.println("5- invio la stringa al server e attendo");
            outVersoServer.writeBytes(stringaUtente+ '\n');
            stringaRicevutadalserver= inDalServer.readLine();
            System.out.println("8- risposta dal server"+'\n'+ stringaRicevutadalserver);
            System.out.println("9- CLIENT: termina elaborazione e chiude connessione");
            mioSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server");
            System.exit(portaServer);
        }
    }

 
}
