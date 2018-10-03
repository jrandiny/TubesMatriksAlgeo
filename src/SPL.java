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

    public void setM(Matriks MIn) {
        M = MIn;
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

    public void penyelesaianSimetris() {
        int barisMatriks = M.getBrs() - 1;
        int kolomMatriks;
        int nomorArray = M.getCol()- 2;

        double[] X = new double[10];

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

        for (int i = 0; i < 4; i++) {
            System.out.println(X[i]);
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
