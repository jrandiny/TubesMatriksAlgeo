public class Interpolasi {
    private Matriks M;
    private double[] X = new double[100];

    public void setArray(double[] Arr) {
        X = Arr;
    }

    public double[] getNArray() {
        return X;
    }

    public void setM(Matriks MIn) {
        M = MIn;
    }

    public void penyelesaian() {
        int barisMatriks = M.getBrs() - 1;
        int kolomMatriks;
        int nomorArray = M.getCol()- 2;


        while (barisMatriks != -1) {
            kolomMatriks = M.getCol()-2;

            if (barisMatriks == M.getBrs()-1) {
                X[nomorArray] = M.get(barisMatriks,kolomMatriks+1);
            }
            else {
                X[nomorArray] = 0;
                while (kolomMatriks != barisMatriks) {
                    X[nomorArray] = X[nomorArray] - (M.get(barisMatriks,kolomMatriks) * X[kolomMatriks]);
                    kolomMatriks--;
                }
                X[nomorArray] = X[nomorArray] + M.get(barisMatriks,M.getCol()-1);
            }

            barisMatriks--;
            nomorArray--;
        }
    }

    public void tulisPenyelesaian() {

        int i = 0;

        System.out.print("f(x)=");

        System.out.print(X[i]);
        System.out.print("+");
        i++;

        while (i < M.getBrs()-1) {
            System.out.print(X[i]);
            System.out.print("x^");
            System.out.print(i);
            System.out.print("+");
            i++;
        }

        System.out.print(X[i]);
        System.out.print("x^");
        System.out.print(i);
    }
}
