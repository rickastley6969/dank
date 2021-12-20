import java.util.Scanner;
import java.lang.Math;

class hillCipher {
    int[][] MultiMod(int a[][], int b[][], int r1, int r2, int c1, int c2, int m) {
        int[][] c = new int[r1][c2];
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < r2; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                    c[i][j] = c[i][j] % m;
                }
            }
        }
        return c;
    }

    int[][] numEncod(String s, int rp, int cp) {
        int res[][] = new int[rp][cp];
        int x = 0;
        for (int j = 0; j < cp; j++) {
            for (int i = 0; i < rp; i++) {
                if (x < s.length()) {
                    res[i][j] = s.charAt(x) - 97;
                } else {
                    res[i][j] = 23;
                }
                x++;
            }
        }
        return res;
    }

    String charEncod(int[][] ct, int rk, int cp) {
        String ctxt = "";
        for (int j = 0; j < cp; j++) {
            for (int i = 0; i < rk; i++) {
                char c = (char) (ct[i][j] + 97);
                ctxt += c;
            }
        }
        return ctxt;
    }

    int deter(int[][] k, int m) {
        int det = ((k[0][0] * k[1][1]) - (k[1][0] * k[0][1]));
        int mo = Math.floorMod(det, m);
        return mo;
    }

    int multInv(int d, int m) {
        for (int x = 1; x < m; x++)
            if (((d % m) * (x % m)) % m == 1) {
                System.out.println("Multiplicative Inverse of the determinant is: " + x);
                return x;
            }
        return 1;

    }

    int[][] adj(int[][] k, int m) {
        int temp;
        temp = k[0][0];
        k[0][0] = k[1][1] % m;
        k[1][1] = temp % m;
        k[0][1] = -1 * k[0][1] % m;
        k[1][0] = -1 * k[1][0] % m;
        System.out.println("Adjoint of the matrix : ");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println(k[i][j]);
            }
        }
        return k;
    }

    int[][] scalarMulti(int detIn, int adjo[][], int rk, int ck, int m) {
        int[][] mult = new int[rk][ck];
        for (int i = 0; i < rk; i++) {
            for (int j = 0; j < ck; j++) {
                int valuem = detIn * adjo[i][j];
                mult[i][j] = Math.floorMod(valuem, m);
            }
        }
        return mult;
    }

    int[][] keyInv(int[][] k, int rk, int ck, int m) {
        int det = deter(k, m);
        System.out.println("The value of the determinant is :" + det);
        int detInv = multInv(det, m);
        int adjo[][] = adj(k, m);
        System.out.println("Inverse of the Key Matrix is: ");
        int[][] sMulti = scalarMulti(detInv, adjo, rk, ck, m);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println(sMulti[i][j]);
            }
        }
        return sMulti;
    }

    void print(int arr[][], int r, int c) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.println(arr[i][j]);
            }

        }
    }
}

class hillCipherTest {
    public static void main(String[] args) {
        Scanner ip = new Scanner(System.in);
        int[][] k = new int[2][2];

        int rk = 2;
        int ck = 2;
        int m = 26;
        hillCipher obj = new hillCipher();

        System.out.println(
                "1. Encryption \n2.Decryption\n3.Known Plain text and Cipher text Attack\nEnter your choice: ");
        int ch = ip.nextInt();
        ip.nextLine();
        if (ch == 1) {
            System.out.println("Enter Plain Text: ");
            String plain = ip.nextLine();
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    System.out.println("Enter Key: " + i + " " + j);
                    k[i][j] = ip.nextInt();

                }
            }

            int rp = ck;
            int cp = (int) Math.ceil((double) plain.length() / rp);
            int[][] plainencoded = obj.numEncod(plain, rp, cp);

            int[][] ctn = obj.MultiMod(k, plainencoded, rk, rp, ck, cp, m);
            System.out.println("The encrypted cipher text is: ");
            System.out.println(obj.charEncod(ctn, rk, cp));

        }

        if (ch == 2) {
            System.out.println("Enter Cipher Text: ");
            String cipher = ip.nextLine();
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    System.out.println("Enter Key: " + i + " " + j);
                    k[i][j] = ip.nextInt();

                }
            }

            System.out.println("-----");
            obj.print(k, 2, 2);
            System.out.println("-----");
            int[][] kinv = obj.keyInv(k, rk, ck, m);
            int rc = rk;
            System.out.println("-----");
            // obj.print(kinv, 2, 2);
            // System.out.println("-----");
            int cc = (int) Math.ceil((double) cipher.length() / rc);
            int[][] ctt = obj.numEncod(cipher, rc, cc);
            // obj.print(ctt, rc, cc);
            int[][] ptn = obj.MultiMod(kinv, ctt, rk, rc, ck, cc, m);
            System.out.println("Resultant plain matrix is: ");
            obj.print(ptn, rc, cc);
            System.out.println("The decrypted plain text is:");
            System.out.println(obj.charEncod(ptn, rk, cc));
        }

        if (ch == 3) {
            System.out.println("Enter Plain Text: ");
            String plain = ip.nextLine();
            System.out.println("Enter Cipher Text: ");
            String cipher = ip.nextLine();
            int rc = rk;

            int cc = (int) Math.ceil((double) cipher.length() / rc);
            int cp = (int) Math.ceil((double) plain.length() / rc);

            int[][] ptt = obj.numEncod(plain, rc, cp);
            int[][] ctt = obj.numEncod(cipher, rc, cc);
            int[][] cinv = obj.keyInv(ctt, rc, cc, m);
            System.out.println("Inverse of the Cipher text matrix is:");
            obj.print(cinv, 2, cc);
            int[][] kinverse = obj.MultiMod(ptt, cinv, 2, 2, cp, cc, m);
            System.out.println("Key inverse : ");
            obj.print(kinverse, 2, cc);
            System.out.println("Key matrix is : ");
            int[][] keymatrix = obj.keyInv(kinverse, rc, cc, m);

        }

    }
}