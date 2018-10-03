public class SPL {
    private Matriks M;

    public boolean NolBawah() {
        int j = 0;
        int i = M.getBrs();
        boolean allNol = true;

        while (j < M.getCol()) {
            if (M.get(i,j) != 0) {
                allNol = false;
            }
            j++;
        }

        return allNol;
    }

    public boolean adaHasil() {
        int j = M.getCol()+1;
        int i = M.getBrs();
        boolean ada;

        if (NolBawah()) {
            if (M.get(i,j) == 0) {
                return true;
            }
            else
                return false;
        }
        else
            return true;
    }

    public int selisihBrsKol() {

        return M.getCol() - M.getBrs();
    }

    public void penyelesaianSimetris(double[] X) {
        int i = M.getBrs();
        int j;
        int k = M.getCol()-1;

        while (i != 0) {
            j = M.getCol()-1;

            if (i == M.getBrs()) {
                X[k] = M.get(i,j+1);
            }
            else {
                X[k] = 0;
                while (j != i) {
                    X[k] = X[k] - (M.get(i,j) * X[j]);
                    j--;
                }
                X[k] = X[k] + M.get(i,j+1);
            }

            i--;
            k--;
        }
    }

//    public void parametrik(char[][] hasil) {
//        char variabel = 'a';
//        double[] X;
//        char[] Y;
//        int i = M.getBrs();
//        int j = M.getCol();
//        int k = M.getCol();
//
//        while (j != i) {
//            Y[j] = X;
//            X++;
//            j--;
//        }
//    }
//
//    public void matriksTinggi(Matriks M) {
//        int i = M.getBrs();
//        int j = M.getCol();
//
//        while (i != j) {
//            M.getBrs() = M.getBrs()-1;
//            i--;
//        }
//    }

}
