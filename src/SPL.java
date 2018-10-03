import java.util.ArrayList;
import java.util.Iterator;

public class SPL {
    private Matriks M;

    private boolean NolBawah() {
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
            return M.get(i, j) == 0;
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

    public void parametrik(){
        String[] hasil = new String[M.getBrs()];
        int jumlahUnknown = M.getCol()-1;
        int[] unknownParam = new int[jumlahUnknown];
        double[] isiParam = new double[jumlahUnknown];
        int[] rowMatriks = new int[jumlahUnknown];

        //Cek unknown yang tidak diketahui
        for (int i = 0; i < M.getBrs(); i++) {
            int j = 0;
            int jumlahNonZero;
            int lokasiLeading;

            jumlahNonZero = 0;
            lokasiLeading = 0;

            while((j<jumlahUnknown)){
                if(M.get(i,j)!=0){
                    jumlahNonZero++;
                    if(jumlahNonZero==1){
                        lokasiLeading = j;
                    }
                }
                j++;
            }

            isiParam[lokasiLeading] = M.get(i,jumlahUnknown);

            if(jumlahNonZero==1){
                unknownParam[lokasiLeading] = 1;
            }else{
                rowMatriks[lokasiLeading] = i;
                unknownParam[lokasiLeading] = 2;
            }
        }

        System.out.println("Unknown yang diketahui");
        for (int i = 0; i < jumlahUnknown; i++) {
            System.out.print(unknownParam[i]);
            if(unknownParam[i]==1){
                System.out.print(" "+isiParam[i]);
            }
            System.out.println();
        }

        //cetakkk
        for (int i = jumlahUnknown-1; i >=0; i--) {
            System.out.print("X"+i+" = ");
            if(unknownParam[i]==0){
                System.out.print("arbitrary");
            }else if(unknownParam[i]==1){
                System.out.print(Double.toString(isiParam[i]));
            }else{
                System.out.println("TBD");
                Token resultToken = getParametrikText(jumlahUnknown,isiParam,unknownParam,i,rowMatriks,M);
                for (Token isi:resultToken.daftarToken) {
                    if(isi.type==1){
                        System.out.print(" + "+isi.angka+" ");
                    }else if(isi.type==0){
                        System.out.print(" + "+isi.angka +"X"+isi.unknownKe+" ");
                    }
                }
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }

    }

    private Token getParametrikText(int jumlahUnknown,double[] isiParam, int[] unknownType,int unknownKe, int[] rowMatriks, Matriks MIn){
        System.out.println("getParametrixText");
        System.out.println("Operasi di unknown ke "+unknownKe);

        if(unknownType[unknownKe]==1){
            System.out.println("langsung keluar angka "+isiParam[unknownKe]);
            Token returnToken = new Token();
            returnToken.angka = isiParam[unknownKe];
            returnToken.type = 1;

            return returnToken;
        }else if(unknownType[unknownKe]==0){
            System.out.println("langsung keluar param X"+unknownKe);
            Token returnToken = new Token();
            returnToken.type = 0;
            returnToken.angka = 1;
            returnToken.unknownKe = unknownKe;

            return returnToken;
        }else{
            System.out.println("Proses rekursif");
            Token returnToken = new Token();
            returnToken.type = 2;
            returnToken.unknownKe = unknownKe;

            ArrayList<Token> arrToken = new ArrayList<>();

            Token baseToken = new Token();
            baseToken.type = 1;
            baseToken.angka = isiParam[unknownKe];
            System.out.println("add base = "+isiParam[unknownKe]);
            arrToken.add(baseToken);

            for (int i = unknownKe+1; i < jumlahUnknown; i++) {
                System.out.println("Cek ke kanan pada pos "+i);
                Token anakToken = getParametrikText(jumlahUnknown,isiParam,unknownType,i,rowMatriks,MIn);
                if(anakToken.type==0){
                    System.out.println("angka dapetnya "+anakToken.angka);
                    anakToken.angka = anakToken.angka * (-1) * MIn.get(rowMatriks[unknownKe],i);
                    arrToken.add(anakToken);
                }else if(anakToken.type==1){
                    System.out.println("param dapetnya X"+anakToken.unknownKe);
                    anakToken.angka = anakToken.angka * (-1) * MIn.get(rowMatriks[unknownKe],i);
                    arrToken.add(anakToken);
                }else{
                    System.out.println("rekursif dapetnya");
                    for (int j = 0; j < anakToken.daftarToken.size(); j++) {
                        anakToken.daftarToken.get(j).angka = anakToken.daftarToken.get(j).angka * (-1) * MIn.get(rowMatriks[unknownKe],i);
                    }
                    arrToken.addAll(anakToken.daftarToken);
                }

            }
            System.out.println("selesai cek anak");
            System.out.println("ISI ARRAY");

            for (Token isiii: arrToken) {
                System.out.println(isiii.type);
                if(isiii.type==1){
                    System.out.print("Angka = ");
                    System.out.println(isiii.angka);
                }else if(isiii.type==0){
                    System.out.print("Param = X");
                    System.out.println(isiii.unknownKe);
                }


            }

            System.out.println("FINISHHHH tinggal eval");

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

            Token angkaToken = new Token();
            angkaToken.angka = tempAngka;
            angkaToken.type = 1;

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
                    Token tempUnknownToken = new Token();
                    tempUnknownToken.type = 0;
                    tempUnknownToken.angka = tempJumlahParam[z];
                    tempUnknownToken.unknownKe = z;

                    arrToken.add(tempUnknownToken);
                }
            }

            returnToken.daftarToken = arrToken;

            return returnToken;
        }
    }
}
