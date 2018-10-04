public abstract class Solver {
    protected Token[] hasil;
    protected Matriks M;

    public abstract void outputHasil();

    /**
     * Mencari solusi matriks yang simetris
     * Prekondisi = Matriks kotak
     */
    public void penyelesaianSimetris(){
        int barisMatriks = M.getBrs() - 1;
        int kolomMatriks;
        int nomorArray = M.getCol()- 2;



        while (barisMatriks != -1) {
            kolomMatriks = M.getCol()-2;

            if (barisMatriks == M.getBrs()-1) {
                hasil[nomorArray] = new Token(1,M.get(barisMatriks,kolomMatriks+1));
            }
            else {
                hasil[nomorArray] = new Token(1,0);
                while (kolomMatriks != barisMatriks) {
                    hasil[nomorArray].angka = hasil[nomorArray].angka - (M.get(barisMatriks,kolomMatriks) * hasil[kolomMatriks].angka);
                    kolomMatriks--;
                }
                hasil[nomorArray].angka = hasil[nomorArray].angka + ( M.get(barisMatriks,M.getCol()-1));
            }

            barisMatriks--;
            nomorArray--;
        }
    }
}
