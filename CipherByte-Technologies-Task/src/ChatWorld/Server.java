package ChatWorld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    public Server() throws IOException {
        server = new ServerSocket(1435);
        System.out.println("server is ready to connect");
        System.out.println("waiting.....");
        socket = server.accept();

        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());


        starReading();
        startWriting();
    }

    public void starReading() {
        Runnable r1 = () -> {
            System.out.println("reader started...");
            while( true ) {
                try {
                    String message = br.readLine();
                    if( message.equals("Exist") ) {
                        System.out.println("Client Terminate the chat");
                        break;
                    }
                    System.out.println("Server:- "+message);
                } catch( IOException e ) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(r1).start();

    }

    public static void main( String[] args ) throws IOException {
        System.out.println("this server is going to start");
        new Server();
    }

    public void startWriting() {
        Runnable r2 = () -> {
            System.out.println("Writer Started...");
            while( true ) {
                try {
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String content = br1.readLine();
                    out.println(content);
                    out.flush();
                } catch( RuntimeException | IOException e ) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(r2).start();
    }
}