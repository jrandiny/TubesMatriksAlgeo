public class SPL extends Solver{

    /**
     * Mengeluarkan output hasil dari Sistem Persamaan Linear
     */
    @Override
    public void outputHasil(){
        if(konsisten){
            for (int i = 0; i < hasil.length; i++) {
                Output.print("X"+i+" = ");
                if(hasil[i].type==1){
                    Output.print(String.format("%.2f",hasil[i].angka));
                }else if(hasil[i].type==0){
                    Output.print("bebas");
                }else{
                    Token resultToken = getParametrik(i);

                    boolean first = true;

                    for (Token isi:resultToken.daftarToken) {
                        if(first){
                            first = false;
                        }else{
                            Output.print(" + ");
                        }

                        if(isi.type==1){
                            Output.print(String.format("%.2f ",isi.angka));
                        }else if(isi.type==0){
                            Output.print(String.format("( %.2f * X%d )",isi.angka,isi.unknownKe));
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
