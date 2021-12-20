import java.security.MessageDigest;
import java.util.*;

public class hash
{
    static String[] combi_of_2words()       //it combines the words like aa,ab,ac,...
    {
        String[] arr = new String[676];
        int x = 0 ;
        for(int i=65;i<91;i++)
        {
            for(int j=65;j<91;j++)
            {
                char a  = (char) (i);
                char b  = (char) (j);
                String pattern = ""+a+b;
                arr[x] = pattern;
                x++;
            }
        }
        return arr;
    }

    static String sha256(final String base) {        //string to a hash
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    static String[] hash_combinations(String[] array)  //getting all combination words converting to hash by using sha256
    {
        String[] has_array = new String[676];
        for(int i=0;i<array.length;i++)
        {
            String hash = sha256(array[i]);
            has_array[i] = hash;
        }
        return has_array;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] combinations_array = combi_of_2words();

        System.out.println("Generating the Hash for the Combination of 2 words\n");
        String[] hash_combinations_array  = hash_combinations(combinations_array);
        while(true) {
            System.out.println("\n1-Enter Plain text to Hash \n2-Hash to Plain Text \n3.Exit");
            int ch = scan.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Enter the password to get hash: ");
                    String has_input = scan.next();
                    System.out.println("The hash for this password is : " + sha256(has_input));
                    break;
                case 2:
                    System.out.println("Enter the hash to get the text : ");
                    String hash = scan.next();
                    for (int i = 0; i < hash_combinations_array.length; i++) {
                        if (hash_combinations_array[i].equals(hash))
                            System.out.println("The Cracked Password is : " + combinations_array[i] + "\n" + "Location in the dictionary : " + i);
                        }
                case 3:
                    System.exit(0);
            }
        }

    }
}
