import java.util.ArrayList;
import java.util.Iterator;

public abstract class Solver {
    protected Token[] hasil;
    protected Matriks M;
    protected boolean konsisten;
    protected int jumlahUnknown;
    private int[] rowMatriks;

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

            j = 0;

            while ((j<jumlahUnknown) && sebarisNol){
                if(M.get(i,j)!=0){
                    sebarisNol =false;
                }
                j++;
            }

            if(sebarisNol && (M.get(i,j)!=0)){
                //semua nol kecuali kolom terakhir
                konsisten = false;
            }
            i++;
        }

        return konsisten;
    }

    private void initHasil(){
        for (int i = 0; i < jumlahUnknown; i++) {
            hasil[i] = new Token();
        }
    }

    public void solveAll(){
        //KAMUS LOKAL
        int i,j;


        //ALGORITMA
        this.konsisten = isKonsisten();

        if(konsisten){
            initHasil();
            rowMatriks = new int[jumlahUnknown];

            for (i = 0; i < M.getBrs(); i++) {
                j = 0;
                int jumlahNonZero;
                int lokasiLeading;

                jumlahNonZero = 0;
                lokasiLeading = 0;

                while(j<jumlahUnknown){
                    if(M.get(i,j)!=0){
                        jumlahNonZero++;
                        if(jumlahNonZero==1){
                            lokasiLeading = j;
                        }
                    }
                    j++;
                }

                //Hanya satu yang bukan 0 (leading)
                if(jumlahNonZero == 1){
                    // Jika angka biasa, set hasil angka tersebut langsung
                    hasil[lokasiLeading] = new Token(1,M.get(i,jumlahUnknown));
                }else if(jumlahNonZero > 1){
                    // Jika parameter
                    rowMatriks[lokasiLeading] = i;
                    hasil[lokasiLeading] = new Token(2,M.get(i,jumlahUnknown),lokasiLeading);
                }

            }
        }
    }

    protected Token getParametrik(int unknownKe){
        Output.logln("getParametrixText");
        Output.logln("Operasi di unknown ke "+unknownKe);

        if(hasil[unknownKe].type==1){
            Output.logln("langsung keluar angka "+hasil[unknownKe].angka);
            return new Token(1,hasil[unknownKe].angka);
        }else if(hasil[unknownKe].type==0){
            Output.logln("langsung keluar param X"+unknownKe);
            return new Token(0,1,unknownKe);
        }else{
            Output.logln("Proses rekursif");
            Token returnToken = new Token();
            returnToken.type = 2;
            returnToken.unknownKe = unknownKe;

            ArrayList<Token> arrToken = new ArrayList<>();

            Token baseToken = new Token(1,hasil[unknownKe].angka);
            Output.logln("add base = "+hasil[unknownKe].angka);
            arrToken.add(baseToken);

            for (int i = unknownKe+1; i < jumlahUnknown; i++) {
                Output.logln("Cek ke kanan pada pos "+i);
                Token anakToken = getParametrik(i);
                if(anakToken.type==0){
                    Output.logln("angka dapetnya "+anakToken.angka);
                    anakToken.angka = anakToken.angka * (-1) * M.get(rowMatriks[unknownKe],i);
                    arrToken.add(anakToken);
                }else if(anakToken.type==1){
                    Output.logln("param dapetnya X"+anakToken.unknownKe);
                    anakToken.angka = anakToken.angka * (-1) * M.get(rowMatriks[unknownKe],i);
                    arrToken.add(anakToken);
                }else{
                    Output.logln("rekursif dapetnya");
                    for (int j = 0; j < anakToken.daftarToken.size(); j++) {
                        anakToken.daftarToken.get(j).angka = anakToken.daftarToken.get(j).angka * (-1) * M.get(rowMatriks[unknownKe],i);
                    }
                    arrToken.addAll(anakToken.daftarToken);
                }

            }
            Output.logln("selesai cek anak");
            Output.logln("ISI ARRAY");

            for (Token isiii: arrToken) {
                Output.logln(Integer.toString(isiii.type));
                if(isiii.type==1){
                    Output.log("Angka = ");
                    Output.logln(Double.toString(isiii.angka));
                }else if(isiii.type==0){
                    Output.log("Param = X");
                    Output.logln(Integer.toString(isiii.unknownKe));
                }


            }

            Output.logln("FINISHHHH tinggal eval");

            //EVALUASI SEMUA (1/2 angka)
            double tempAngka = 0;
            Iterator<Token> itr = arrToken.iterator();

            while (itr.hasNext()){
                Token tempToken = itr.next();
                if(tempToken.type==1){
                    tempAngka = tempAngka + tempToken.angka;
                    itr.remove();
                }
            }

            Token angkaToken = new Token(1,tempAngka);

            arrToken.add(angkaToken);

            //EVALUASI SEMUA(2/2 PARAM)
            double[] tempJumlahParam = new double[jumlahUnknown];
            Iterator<Token> itr2 = arrToken.iterator();

            while (itr2.hasNext()){
                Token tempToken = itr2.next();
                if(tempToken.type==0){
                    tempJumlahParam[tempToken.unknownKe] += tempToken.angka;
                    itr2.remove();
                }
            }

            for (int z = 0; z < jumlahUnknown; z++) {
                if(tempJumlahParam[z]!=0){
                    Token tempUnknownToken = new Token(0,tempJumlahParam[z],z);

                    arrToken.add(tempUnknownToken);
                }
            }

            returnToken.daftarToken = arrToken;

            return returnToken;
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
