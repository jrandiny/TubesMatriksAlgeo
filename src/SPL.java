public class SPL {
    private Matriks M;
    private int[] NilaiX;

    public boolean NolBawah() {
        int j = 0;
        int i = M.getBrs();
        boolean allNol = true;

        while (j <= M.getCol()) {
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
        int j = M.getCol();
        int k = M.getCol();

        while (i != 0) {
            if (i == M.getBrs()) {
                X[k] = M.get(i,j+1);
            }
            else {
                j = M.getCol();
                X[k] = 0;
                while (j != i) {
                    X[k] = X[k] - (M.get(i,j) * X[j]);
                    j--;
                }
                X[k] = X[k] + M.get(i,j+1);
            }
            System.out.println(X[k]);
            i--;
            k--;
        }
    }

    public void parametrik(double[] X) {

    }
}
