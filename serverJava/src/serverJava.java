import java.io.*;
import java.net.*;

public class serverJava {
    public static void main(String[] args) {
        int port = 12345; // Porta del server

        try (ServerSocket serverSocket = new ServerSocket(port)) {
        	String clientMessage;
            String serverMessage;
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        	
            System.out.println("Server in ascolto sulla porta " + port);

            Socket clientSocket = serverSocket.accept();
            System.out.println("Connessione richiesta da: " + clientSocket.getInetAddress());
            serverMessage = consoleReader.readLine();
            while(serverMessage.equalsIgnoreCase("rifiuta")) {
            
            	clientSocket.close();
            	System.out.println("Server in ascolto sulla porta " + port);

            	clientSocket = serverSocket.accept();
                System.out.println("Connessione richiesta da: " + clientSocket.getInetAddress());
                serverMessage = consoleReader.readLine();
                
            }
            
            if(serverMessage.equalsIgnoreCase("accetta")) {
            // Stream di input/output
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            

            

            System.out.println("Pronto per comunicare con il client...");

            while (true) {
                // Legge il messaggio dal client
                clientMessage = reader.readLine();
                if (clientMessage == null || clientMessage.equalsIgnoreCase("stop")) {
                    System.out.println("Il client ha chiuso la connessione.");
                    break;
                }
                System.out.println("Client: " + clientMessage);

                // Scrive una risposta al client
                //System.out.print("Server: ");
                //serverMessage = consoleReader.readLine();
                //writer.println(serverMessage);
                //xc
                writer.println(clientSocket.getInetAddress() + ":" +  clientSocket.getPort());
                
            }

            clientSocket.close();
            }
            
        } catch (IOException ex) {
            System.out.println("Errore nel server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
