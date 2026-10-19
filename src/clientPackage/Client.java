package clientPackage;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            // La première étape
            System.out.println("Je suis un client pas encore connecté…");

            // Connexion au serveur : adresse + MÊME port que le serveur
            //Socket socket = new Socket("localhost", 1234);
            Socket socket = new Socket("127.0.0.1", 1234);
            System.out.println("je suis un client connecté");

            // La dernière étape : fermer le socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}