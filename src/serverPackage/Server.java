package serverPackage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    private static final String IP = "192.168.1.18";   // ←  (machine A)
    private static final int PORT = 1234;
    public static void main(String[] args) {
        try {ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(InetAddress.getByName(IP), PORT));
            System.out.println("Je suis un serveur en attente la connexion d'un client sur " + IP + ":" + PORT);
            Socket socket = serverSocket.accept();
            System.out.println("un client est connecté : " + socket.getInetAddress().getHostAddress());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while (true) {int x = in.readInt();
                if (x == 0) {
                    System.out.println("0 reçu : fin de la communication");
                    break;}
                int resultat = x * 5;
                System.out.println("Reçu : " + x + " -> envoyé : " + resultat);
                out.writeInt(resultat);
                out.flush();}
            in.close();
            out.close();
            socket.close();
            serverSocket.close();
            System.out.println("Serveur fermé");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}