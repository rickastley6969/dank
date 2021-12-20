import java.math.*;
import java.util.*;

import static java.lang.System.exit;

class RSA{
    Scanner input = new Scanner(System.in);
    BigInteger e,d,n;
    protected static final String ALPHABET="abcdefghijklmnopqrstuvwxyz";
    protected static BigInteger GCD(BigInteger a,BigInteger b){

        return a.gcd(b);
    }
    protected static BigInteger modInv(BigInteger a,BigInteger m){

        return a.modInverse(m);
    }
    public void keyGeneration(BigInteger p,BigInteger q,BigInteger e)
    {
        BigInteger one=new BigInteger("1");
        BigInteger n=p.multiply(q);
        BigInteger phi=(p.subtract(one)).multiply(q.subtract(one));

        if(!GCD(e,phi).equals(one))
        {
            System.out.println("error");
            //return null;
        }
        BigInteger d=modInv(e,phi);
        System.out.println("public key (e,n): "+"("+e+" , " +n+")");
        System.out.println("private key (d,n) : "+"("+d+" , " +n+")");
//        BigInteger[]keys=new BigInteger[3];
//        keys[0]=e;
//        keys[1]=d;
//        keys[2]=n;
        this.e=e;
        this.d=d;
        this.n=n;
//       return keys;
    }
    protected static BigInteger modPow(BigInteger base,BigInteger exp,BigInteger m){

        return base.modPow(exp,m);
    }
    public BigInteger[]encrypt(String plaintxt)   //m-power(e) mod n
    {
        char[]plaintext=plaintxt.toCharArray();
        BigInteger[]ciphertext=new BigInteger[plaintext.length];
        for(int i=0;i<plaintext.length;i++){
            int m=ALPHABET.indexOf(plaintext[i]);
            ciphertext[i]=modPow(new BigInteger(m+""),e,n);
        }
        return ciphertext;
    }
    public String decrypt(BigInteger[] ciphertxt){    //c-power(d) mod n
        StringBuilder plaintext=new StringBuilder();
        char temp;
        for(int i=0;i<ciphertxt.length;i++){
            temp=ALPHABET.charAt(modPow(ciphertxt[i],d,n).intValue());
            plaintext.append(temp);
        }
        return plaintext.toString();
    }
    public void rsa_encryption(){
        System.out.println("Enter text to encrypt: ");
        BigInteger[] txt = encrypt(input.nextLine());
        System.out.println("The cipher text is: ");
        for (int i = 0; i < txt.length; i++) {
            System.out.print(txt[i] + " ");
        }
        System.out.println(" ");
    }
    public void rsa_decryption(){
        System.out.println("\nEnter the length of the text to decrypt: ");
        BigInteger[] ciphertext = new BigInteger[Integer.parseInt(input.nextLine())];
        System.out.println("Enter the text to decrypt: ");
        for (int i = 0; i < ciphertext.length; i++) {
            ciphertext[i] = new BigInteger(input.nextLine());
        }

        String str = decrypt(ciphertext);
        System.out.println("The plain text is: " + str);
        input.close();
    }
}
public class rsamain{
    public static void main(String[]args) {
        RSA rsa = new RSA();
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            try {
                System.out.println("Enter your choice:\n 1-KEY GENERATION\n 2-ENCRYPTTION\n 3-DECRYPTION\n 4-EXIT");
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Enter the value of p: ");
                        BigInteger p = input.nextBigInteger();
                        System.out.println("Enter the value of q:");
                        BigInteger q = input.nextBigInteger();
                        System.out.println("Enter e value (public key): ");
                        BigInteger e = input.nextBigInteger();
                        rsa.keyGeneration(new BigInteger(p + ""), new BigInteger(q + ""), new BigInteger(e + ""));
                        break;
                    case 2:
                        rsa.rsa_encryption();
                        break;
                    case 3:
                        rsa.rsa_decryption();
                        break;
                    case 4:
                        flag = false;

                }
            }
            catch(Exception e){
                exit(0);
            }
        }
    }
}