public class SPL extends Solver{

    /**
     * Mengeluarkan output hasil dari Sistem Persamaan Linear
     */
    @Override
    public void outputHasil(){
        // KAMUS
        int i;
        boolean first;

        // ALGORITMA
        if(konsisten){
            for (i = 0; i < hasil.length; i++) {
                Output.print("X"+i+" = ");
                if(hasil[i].type==1){
                    Output.print(String.format("%.2f",hasil[i].angka));
                }else if(hasil[i].type==0){
                    Output.print("bebas");
                }else{
                    Token resultToken = getParametrik(i);

                    first = true;

                    for (Token isi:resultToken.daftarToken) {
                        // Untuk pertama, tidak usah tambahin tanda +
                        if(first){
                            first = false;
                        }else{
                            Output.print(" + ");
                        }

                        // Jika unknown, tampilkan dengan kurung dan X
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
