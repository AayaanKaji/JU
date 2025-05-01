package Q03_GET_Page_JavaSocket;

import java.io.*;
import java.net.*;

public class GetPage {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java HttpSocketClient <hostname> <port>");
            return;
        }

        String hostname = args[0];
        int port = 0;

        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid port number: " + args[1]);
            return;
        }

        String filePath = "/AayaanKaji/Q01-Intro/first.html";

        // The HTTP GET request
        String request = "GET " + filePath + " HTTP/1.0\r\n"
                         + "Host: " + hostname + "\r\n"
                         + "Connection: close\r\n\r\n";

        try {
            Socket socket = new Socket(hostname, port);
            
            OutputStream os = socket.getOutputStream();
            PrintWriter out = new PrintWriter(os);
            
            out.print(request);
            out.flush();

            InputStream is = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            socket.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
