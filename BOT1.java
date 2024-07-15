package Hard;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class BOT1 {
    private static final String ADDRESS = "localhost";
    private static final int PORT = 8765;
    
    public static void main(String[] args) {
        try (Socket socket = new Socket(ADDRESS, PORT)) {
            new Thread(new ReadMessage(socket)).start();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            @SuppressWarnings("resource")
            Scanner scan = new Scanner(System.in);
            
            while (true) {
                String message = scan.nextLine();
                out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReadMessage implements Runnable {
    private Socket socket;

    public ReadMessage(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
