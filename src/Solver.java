import java.util.ArrayList;
import java.util.Iterator;

public abstract class Solver {
    // KAMUS
    protected Token[] hasil;
    protected Matriks M;
    protected boolean konsisten;
    protected int jumlahUnknown;
    private int[] rowMatriks;

    public abstract void outputHasil();

    /**
     * Mengeset Matriks
     */
    public void setM(Matriks MIn) {
        M = MIn;
        jumlahUnknown = M.getCol()-1;
        hasil = new Token[M.getCol()-1];
    }

    /**
     * Mengecek kekonsistenan matriks
     */
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

    /**
     * Menyimpan hasil di tempat sementara
     */
    private void initHasil(){
        for (int i = 0; i < jumlahUnknown; i++) {
            hasil[i] = new Token();
        }
    }

    /**
     * Menyelesaikan persamaan matriks
     */
    public void solveAll(){
        //KAMUS LOKAL
        int i,j;
        int jumlahNonZero;
        int lokasiLeading;


        //ALGORITMA
        this.konsisten = isKonsisten();

        if(konsisten){
            initHasil();
            rowMatriks = new int[jumlahUnknown];

            for (i = 0; i < M.getBrs(); i++) {
                j = 0;

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

    /**
     * Rekursif untuk mendapatkan sebuah unknown
     * @param unknownKe posisi unknown
     * @return token yang berisi isi dari unknown yang dicari
     */
    protected Token getParametrik(int unknownKe){
        // KAMUS LOKAL
        int i,j,k;
        Token returnToken;
        double[] tempJumlahParam; // Menyimpan hasil merge semua anak token (tipe variabel (0))
        double tempAngka;      // Menyimpan hasil merge semua anak token (tipe angka (1))
        Iterator<Token> itr;   // Iterator untuk nanti merge semua anak token
        Iterator<Token> itr2;  // Iterator untuk nanti merge semua anak token

        // ALGORTIMA

        // Basis, jika tipe angka atau parameter
        if(hasil[unknownKe].type==1){
            return new Token(1,hasil[unknownKe].angka);
        }else if(hasil[unknownKe].type==0){
            return new Token(0,1,unknownKe);
        }else{
            // Jika tipernya butuh direkursi

            // buat returnToken yang nanti akan diisi
            returnToken = new Token();
            returnToken.type = 2;
            returnToken.unknownKe = unknownKe;

            // arraylist token untuk
            ArrayList<Token> arrToken = new ArrayList<>();

            // Tambahkan base (nilai y) (untuk nanti dikurang/tambah dengan nilai lain
            Token baseToken = new Token(1,hasil[unknownKe].angka);
            arrToken.add(baseToken);

            // Rekursifkan semua unknown sebelah kanannya
            for (i = unknownKe+1; i < jumlahUnknown; i++) {
                Token anakToken = getParametrik(i);

                // Cek tipe lalu gabungkan ke arrToken
                if(anakToken.type==0){
                    anakToken.angka = anakToken.angka * (-1) * M.get(rowMatriks[unknownKe],i);
                    arrToken.add(anakToken);
                }else if(anakToken.type==1){
                    anakToken.angka = anakToken.angka * (-1) * M.get(rowMatriks[unknownKe],i);
                    arrToken.add(anakToken);
                }else{
                    for (j = 0; j < anakToken.daftarToken.size(); j++) {
                        anakToken.daftarToken.get(j).angka = anakToken.daftarToken.get(j).angka * (-1) * M.get(rowMatriks[unknownKe],i);
                    }
                    arrToken.addAll(anakToken.daftarToken);
                }

            }

            // Evaluasi isi arrToken dan gabungkan semua yang angka (tipe 1)
            tempAngka = 0;
            itr = arrToken.iterator();

            while (itr.hasNext()){
                Token tempToken = itr.next();
                if(tempToken.type==1){
                    tempAngka = tempAngka + tempToken.angka;
                    itr.remove();
                }
            }

            Token angkaToken = new Token(1,tempAngka);

            arrToken.add(angkaToken);

            // Evaluasi isi arrToken dan gabungkan semua variabel (tipe 0)
            tempJumlahParam = new double[jumlahUnknown];
            itr2 = arrToken.iterator();

            while (itr2.hasNext()){
                Token tempToken = itr2.next();
                if(tempToken.type==0){
                    tempJumlahParam[tempToken.unknownKe] += tempToken.angka;
                    itr2.remove();
                }
            }


            for (k = 0; k < jumlahUnknown; k++) {
                if(tempJumlahParam[k]!=0){
                    Token tempUnknownToken = new Token(0,tempJumlahParam[k],k);

                    arrToken.add(tempUnknownToken);
                }
            }

            returnToken.daftarToken = arrToken;

            return returnToken;
        }
    }
}
