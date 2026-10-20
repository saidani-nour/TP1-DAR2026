# TP1-DAR2026 : Sockets en mode connecté TCP

**Nom :** Nour SAIDANI  
**Classe :** LSI 3  
**Matière :** Développement d'applications réparties

# Activité 1-1 : Première connexion Client/Serveur

## Vérification de Git et de Java

Description : la commande `git --version` affiche la version de Git installée, ce qui confirme que Git est bien installé.

Description : Java 8 ne reconnaît pas `--version`, j'ai donc utilisé `java -version`. Java 1.8.0_471 est installé.

## Serveur

![Serveur en attente](images/serveur-attente.png)

Le serveur a créé un ServerSocket sur le port 1234 et affiche son message d'attente. Il reste bloqué sur la méthode accept() tant qu'aucun client ne se connecte.

## Client

![Console client](images/client-console.png)
![Console serveur](images/serveur-console.png)

Le client a créé un socket vers localhost sur le port 1234. La connexion TCP a été établie : le client affiche qu'il est connecté, et la méthode accept() du serveur s'est débloquée, affichant « un client est connecté ». Les deux sockets sont ensuite fermés.

### Test avec une adresse IP

![Client avec IP](images/client-ip.png)
![Serveur avec IP](images/serveur-ip.png)

localhost est le nom symbolique de l'adresse de bouclage 127.0.0.1. Le résultat est identique, la connexion fonctionne de la même façon.

# Activité 1-2 : Service de calculatrice
# Activité 1-2 : Service de calculatrice

## Étape 2 : Service de multiplication par 5

### Première version (points 1 à 3)

**Code du serveur :**

![Code serveur](images/code-serveur1-2.png)

```java
package serverPackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Je suis un serveur en attente la connexion d'un client ");
            Socket socket = serverSocket.accept();
            System.out.println("un client est connecté");

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            int x = in.read();
            int resultat = x * 5;
            System.out.println("Reçu : " + x + " -> envoyé : " + resultat);
            out.write(resultat);
            out.flush();

            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

**Code du client :**

![Code client](images/code-client1-2.png)

```java
package clientPackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Je suis un client pas encore connecté…");
            Socket socket = new Socket("127.0.0.1", 1234);
            System.out.println("je suis un client connecté");

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            Scanner sc = new Scanner(System.in);
            System.out.print("Entrez un entier x : ");
            int x = sc.nextInt();

            out.write(x);
            out.flush();

            int resultat = in.read();
            System.out.println("Résultat (x * 5) = " + resultat);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

**Test avec x = 7 :**

![x = 7](images/etape2-positif.png)

**Test avec x = -4 :**

![x = -4](images/etape2-negatif.png)

**Test avec x = 0 :**

![x = 0](images/etape2-zero.png)

**Interprétation :** pour x = 7, le serveur renvoie 35, et pour x = 0 il renvoie 0 : ces résultats sont corrects. Pour x =