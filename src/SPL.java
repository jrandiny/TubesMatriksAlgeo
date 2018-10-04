public class SPL extends Solver{

    public void setM(MatriksSPL MIn) {
        M = MIn;
        jumlahUnknown = M.getCol()-1;
        hasil = new Token[jumlahUnknown];
    }

    @Override
    public void outputHasil(){
        if(konsisten){
            for (int i = 0; i < hasil.length; i++) {
                Output.print("X"+i+" = ");
                if(hasil[i].type==1){
                    Output.print(Double.toString(hasil[i].angka));
                }else if(hasil[i].type==0){
                    Output.print("bebas");
                }else{
                    Token resultToken = getParametrik(i);
                    for (Token isi:resultToken.daftarToken) {
                        if(isi.type==1){
                            Output.print(" + "+isi.angka+" ");
                        }else if(isi.type==0){
                            Output.print(" + "+isi.angka +"X"+isi.unknownKe+" ");
                        }
                    }
                }

                Output.println();

            }
        }else{
            Output.println("Tidak konsisten, tidak dapat diselesaikan");
        }

    }


}
