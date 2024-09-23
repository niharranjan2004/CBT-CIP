package ChatWorld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    Socket socket;
    BufferedReader br ;
    PrintWriter out;

    public Client(String address,int serverPort){

        try{
            System.out.println("sending request to server");
            socket=new Socket(address,serverPort);

            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream(),true);

            startReading();
            startWriting();
        } catch( UnknownHostException e ) {
            throw new RuntimeException(e);
        } catch( IOException e ) {
            throw new RuntimeException(e);
        }
    }

    private void startWriting() {
        Runnable r2 = () -> {
            System.out.println("Writer Started...");
            while( true ) {
                try {
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String content = br1.readLine();
                    out.println(content);
                    out.flush();
                }

                catch( RuntimeException | IOException e ) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(r2).start();
    }

    private void startReading() {
        Runnable r1 = () -> {
            System.out.println("reader started...");
            while( true ) {
                try {
                    String message = br.readLine();
                    if( message.equals("Exist") ) {
                        System.out.println("Client Terminate the chat");
                        break;
                    }
                    System.out.println("Client:- "+message);
                } catch( IOException e ) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(r1).start();
    }

    public static void main( String[] args ) {
        String serverAddress="localHost";
        int serverPort=1435;
        new Client(serverAddress,serverPort);
    }
}