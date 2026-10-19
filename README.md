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