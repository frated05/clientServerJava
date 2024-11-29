import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        int port = 12345; // Porta del server
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server in ascolto sulla porta " + port);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connessione accettata da: " + clientSocket.getInetAddress());

                // Stream di input/output
                InputStream input = clientSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                
                OutputStream output = clientSocket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                // Legge il messaggio dal client
                String message = reader.readLine();
                System.out.println("Messaggio ricevuto: " + message);

                // Risponde al client
                writer.println("Risposta dal server: " + message.toUpperCase());

                // Chiude la connessione
                clientSocket.close();
            }
        } catch (IOException ex) {
            System.out.println("Errore nel server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
