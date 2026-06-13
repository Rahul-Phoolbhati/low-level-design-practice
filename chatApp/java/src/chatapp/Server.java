package chatapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
        public static void main(String[] args) {
            try {
                ServerSocket server = new ServerSocket(3000);

                Socket s = server.accept();
                System.out.println(s);
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String msg;
                while((msg = br.readLine()) != null){
                    System.out.println(msg);
                    if(msg.equals("999")){
                        break;
                    }
                }

                server.close();
                s.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
}
