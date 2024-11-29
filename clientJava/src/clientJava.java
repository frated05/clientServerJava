import java.io.*;
import java.net.*;

public class clientJava {
    public static void main(String[] args) {
        String hostname = "10.130.1.118"; // Nome host o IP del server
        int port = 12347; // Porta del server

        try (Socket socket = new Socket(hostname, port)) {
            // Stream di input/output
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage;
            String serverMessage;

            System.out.println("Connesso al server. Pronto per comunicare...");

            while (true) {
                // Scrive un messaggio al server
                System.out.print("Client: ");
                clientMessage = consoleReader.readLine();
                writer.println(clientMessage);

                if (clientMessage.equalsIgnoreCase("stop")) {
                    System.out.println("Connessione chiusa dal client.");
                    break;
                }
                

                // Legge la risposta dal server
                serverMessage = reader.readLine();
                System.out.println(serverMessage);
            }
        } catch (UnknownHostException ex) {
            System.out.println("Server non trovato: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Errore I/O: " + ex.getMessage());
            //test
        }
    }
}