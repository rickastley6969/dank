import java.io.DataInputStream;
import java.net.*;
import java.util.Scanner;
class ex_no_1{
    String alphabets = "abcdefghijklmnopqrstuvwxyz";
    Scanner input = new Scanner(System.in);
    public String caeser_encryption(int key , String text){
        String result ="";
        for(int i=0;i<text.length();i++){
            int char_pos = alphabets.indexOf(text.charAt(i));
            char res = alphabets.charAt((char_pos+key)%26); //formula for encryption
            result +=res;
        }
        return result;
    }
    public String caeser_decryption(int key , String text){
        String result ="";
        for(int i=0;i<text.length();i++){
            int char_pos = alphabets.indexOf(text.charAt(i));
            if(char_pos-key >=0) {
                char res = alphabets.charAt((char_pos - key) % 26); //formula for encryption
                result +=res;
            }
            if(char_pos-key < 0){
                int after_mod26 = (char_pos-key)+26;
                char res = alphabets.charAt(after_mod26);
                result +=res;
            }
        }
        return result;
    }
    public void BruteForceAttack(String text){
        for (int i = 0; i < 26; i++) {
            System.out.println("text : " + caeser_decryption(i, text) + " => Key : " + i);
        }
    }
    public char FindMostFreq(String text){
        char freq_char =' ';
        for(int i=0;i<text.length();i++){
            char temp = text.charAt(i);
            int max_count=0;
            int current_count = 0;
            for(int j=0 ; j<text.length();j++){
                if(temp == text.charAt(j)){
                    current_count++;
                }
            }
            if(current_count>max_count) {
                max_count = current_count;
                freq_char = temp;
            }
        }
        return freq_char;
    }
    public void FrequencyAnalysisAttack(String text){
        char freq_occured = FindMostFreq(text);
        int iter = 0;
        for(int i=alphabets.indexOf(freq_occured);i<26;i++){
            int temp = alphabets.indexOf(freq_occured);
            int key = (temp-(i+1));
            String result = caeser_decryption(key , text);
            System.out.println("text : " + result + " Key value: "+ (key+26));
            System.out.println("Do you want to continue further (y/n)");
            String in = input.next();
            if(in.toLowerCase().charAt(0)=='y'){
                continue;
            }
            else
                break;
        }
    }
}
public class Server {
    public Server(int port) throws Exception
    {
        ServerSocket aa = new ServerSocket(port);
        Socket s = aa.accept();
        DataInputStream in = new DataInputStream(s.getInputStream());
        String result = (String)in.readUTF();
        String arr[] = result.split(":");
        String text = arr[0].toLowerCase();
        int key = Integer.parseInt(arr[1]);
        int choice = Integer.parseInt(arr[2]);
        ex_no_1 bb = new ex_no_1();
        if(choice == 1){
            String enc_answer = bb.caeser_encryption(key, text);
            System.out.println(enc_answer);
        }
        if(choice == 2) {
            String dec_answer = bb.caeser_decryption(key, text);
            System.out.println(dec_answer);
        }
        if(choice == 3) {
            bb.BruteForceAttack(text);
        }
        if(choice == 4) {
            bb.FrequencyAnalysisAttack(text);
        }
    }
    public static void main(String[] args) throws Exception{
        Server sc = new Server(1810);
    }
}