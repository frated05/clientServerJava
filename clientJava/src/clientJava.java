import java.io.*;
import java.net.*;

public class clientJava {
    public static void main(String[] args) {
        String hostname = "10.130.1.118"; // Nome host o IP del server
        int port = 12345; // Porta del server
        
        try (Socket socket = new Socket(hostname, port)) {
            // Stream di input/output
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // Invia un messaggio al server
            String message = "Ciao, server!";
            writer.println(message);
            System.out.println("Messaggio inviato: " + message);

            // Legge la risposta dal server
            String response = reader.readLine();
            System.out.println("Risposta dal server: " + response);
        } catch (UnknownHostException ex) {
            System.out.println("Server non trovato: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Errore I/O: " + ex.getMessage());
        }
    }
}