import java.util.ArrayList;
import java.util.Iterator;

public class SPL extends Solver{
//    private MatriksSPL M;


    public void setM(MatriksSPL MIn) {
        M = MIn;
        hasil = new Token[M.getCol()-1];
    }

    @Override
    public void outputHasil(){
        if(konsisten){
            for (int i = 0; i < hasil.length; i++) {
                Output.print("X"+i+" = ");
                if(hasil[i].type==1){
                    Output.println(Double.toString(hasil[i].angka));
                }else if(hasil[i].type==0){
                    Output.println("bebas");
                }else{
                    Output.println("WIP");
                }

            }
        }else{
            Output.println("Tidak konsisten, tidak dapat diselesaikan");
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
//                System.out.println("TBD");
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
        }

    }

    private Token getParametrikText(int jumlahUnknown,double[] isiParam, int[] unknownType,int unknownKe, int[] rowMatriks, Matriks MIn){
        Output.logln("getParametrixText");
        Output.logln("Operasi di unknown ke "+unknownKe);

        if(unknownType[unknownKe]==1){
            Output.logln("langsung keluar angka "+isiParam[unknownKe]);
            Token returnToken = new Token();
            returnToken.angka = isiParam[unknownKe];
            returnToken.type = 1;

            return returnToken;
        }else if(unknownType[unknownKe]==0){
            Output.logln("langsung keluar param X"+unknownKe);
            Token returnToken = new Token();
            returnToken.type = 0;
            returnToken.angka = 1;
            returnToken.unknownKe = unknownKe;

            return returnToken;
        }else{
            Output.logln("Proses rekursif");
            Token returnToken = new Token();
            returnToken.type = 2;
            returnToken.unknownKe = unknownKe;

            ArrayList<Token> arrToken = new ArrayList<>();

            Token baseToken = new Token();
            baseToken.type = 1;
            baseToken.angka = isiParam[unknownKe];
            Output.logln("add base = "+isiParam[unknownKe]);
            arrToken.add(baseToken);

            for (int i = unknownKe+1; i < jumlahUnknown; i++) {
                Output.logln("Cek ke kanan pada pos "+i);
                Token anakToken = getParametrikText(jumlahUnknown,isiParam,unknownType,i,rowMatriks,MIn);
                if(anakToken.type==0){
                    Output.logln("angka dapetnya "+anakToken.angka);
                    anakToken.angka = anakToken.angka * (-1) * MIn.get(rowMatriks[unknownKe],i);
                    arrToken.add(anakToken);
                }else if(anakToken.type==1){
                    Output.logln("param dapetnya X"+anakToken.unknownKe);
                    anakToken.angka = anakToken.angka * (-1) * MIn.get(rowMatriks[unknownKe],i);
                    arrToken.add(anakToken);
                }else{
                    Output.logln("rekursif dapetnya");
                    for (int j = 0; j < anakToken.daftarToken.size(); j++) {
                        anakToken.daftarToken.get(j).angka = anakToken.daftarToken.get(j).angka * (-1) * MIn.get(rowMatriks[unknownKe],i);
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
