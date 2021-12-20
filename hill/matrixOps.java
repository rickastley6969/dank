public class matrixOps {

    static final int N = 4;

    static void getCofactor(int A[][], int temp[][], int p, int q, int n) {
        int i = 0, j = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    temp[i][j++] = A[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    static int determinant(int A[][], int n) {
        int D = 0; 

        if (n == 1)
            return A[0][0];

        int[][] temp = new int[N][N]; 

        int sign = 1; 

        for (int f = 0; f < n; f++) {
            getCofactor(A, temp, 0, f, n);
            D += sign * A[0][f] * determinant(temp, n - 1);

            sign = -sign;
        }

        return D;
    }

    static void adjoint(int A[][], int[][] adj) {
        int sign = 1;
        int[][] temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                getCofactor(A, temp, i, j, N);

                sign = ((i + j) % 2 == 0) ? 1 : -1;

                adj[j][i] = (sign) * (determinant(temp, N - 1));
            }
        }
    }

    static boolean inverse(int A[][], float[][] inverse) {
        int det = determinant(A, N);
        if (det == 0) {
            System.out.print("Singular matrix, can't find its inverse");
            return false;
        }

        int[][] adj = new int[N][N];
        adjoint(A, adj);

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                inverse[i][j] = adj[i][j] / (float) det;

        return true;
    }


    static void display(int A[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(A[i][j] + " ");
            System.out.println();
        }
    }

    static void display(float A[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.printf("%.6f ", A[i][j]);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int A[][] = { { 5, -2, 2, 7 }, { 1, 0, 0, 3 }, { -3, 1, 5, 0 }, { 3, -1, -9, 4 } };

        int[][] adj = new int[N][N]; 

        float[][] inv = new float[N][N]; 

        System.out.print("Input matrix is :\n");
        display(A);

        System.out.print("\nThe Adjoint is :\n");
        adjoint(A, adj);
        display(adj);

        System.out.print("\nThe Inverse is :\n");
        if (inverse(A, inv))
            display(inv);

    }
}

