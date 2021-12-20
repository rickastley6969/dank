import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;
public class Client {
    public Client(String ip, int port) throws Exception{
        Scanner input = new Scanner(System.in);
        Socket s = new Socket(ip, port);
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        System.out.println("Server is ready now");
        System.out.println("Enter your choice as 1 or 2 or 3 or 4"); //getting input from user
        System.out.println("Enter 1 for Encryption \nEnter 2 for Decryption \nEnter 3 for Brute Force Attack \nEnter 4 for Frequency analysis attack");
        int choice=input.nextInt();
        String text= "";
        int key=0;
        if(choice==1){
            System.out.println("Enter the plain text: ");
            text = input.next();
            System.out.println("Enter the key: ");
            key = input.nextInt();
        }
        if(choice == 2){
            System.out.println("Enter the cipher text to decode: ");
            text = input.next();
            System.out.println("Enter the key: ");
            key = input.nextInt();
        }
        if(choice == 3){
            System.out.println("Enter the cipher text to do Brute Force Attack");
            text = input.next();
        }
        if(choice == 4 ){
            System.out.println("Enter the cipher text to do FA attack ");
            text = input.next();
        }
        if(choice !=4) {
            String join = text + ":" + String.valueOf(key) + ":" + String.valueOf(choice);
            out.writeUTF(join);
        }
        if(choice == 4){
            String join = text + ":" + '0' + ":"+ String.valueOf(choice);
            out.writeUTF(join);
        }
        String join = text +":"+ String.valueOf(key)+":" + String.valueOf(choice);
        out.writeUTF(join);
        out.close();
    }
    public static void main(String[] args) throws Exception{
        Client cc = new Client("localhost", 1810);
    }
}