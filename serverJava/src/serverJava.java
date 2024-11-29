import java.io.*;
import java.net.*;

public class serverJava {
    public static void main(String[] args) {
        int port = 12346; // Porta del server

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server in ascolto sulla porta " + port);

            Socket clientSocket = serverSocket.accept();
            System.out.println("Connessione accettata da: " + clientSocket.getInetAddress());

            // Stream di input/output
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage;
            String serverMessage;

            System.out.println("Pronto per comunicare con il client...");

            while (true) {
                // Legge il messaggio dal client
                clientMessage = reader.readLine();
                if (clientMessage == null || clientMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Il client ha chiuso la connessione.");
                    break;
                }
                System.out.println("Client: " + clientMessage);

                // Scrive una risposta al client
                System.out.print("Server: ");
                serverMessage = consoleReader.readLine();
                writer.println(serverMessage);

                if (serverMessage.equalsIgnoreCase("stop")) {
                    System.out.println("Connessione chiusa dal server.");
                    break;
                }
            }

            clientSocket.close();
        } catch (IOException ex) {
            System.out.println("Errore nel server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
