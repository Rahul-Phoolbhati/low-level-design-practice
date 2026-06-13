package chatapp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
           Socket s = null;
            try {
                s = new Socket("localhost", 3000);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
                String msg;
                while((msg = reader.readLine()) != null){
                    pw.println(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                s.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
    }    

    
}
