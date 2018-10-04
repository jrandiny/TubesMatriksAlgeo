public abstract class Solver {
    protected Token[] hasil;
    protected Matriks M;
    protected boolean konsisten;

    public abstract void outputHasil();

    private boolean isKonsisten(){
        //KAMUS
        boolean konsisten;
        boolean sebarisNol;
        int i,j;

        // ALGORITMA

        konsisten = true;
        i = 0;
        while(konsisten && i<M.getBrs()){
            sebarisNol = true;
            for (j = 0; j < M.getCol(); j++) {
                if(M.get(i,j)!=0){
                    sebarisNol =false;
                }

                if((j ==  M.getCol()-1)&&sebarisNol){
                    //semua nol kecuali kolom terakhir
                    konsisten = false;
                }
            }
            i++;
        }

        return konsisten;
    }

    public void solveAll(){
        //KAMUS LOKAL


        //ALGORITMA
        this.konsisten = isKonsisten();

        if(konsisten){
            penyelesaianSimetris();
        }
    }

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
