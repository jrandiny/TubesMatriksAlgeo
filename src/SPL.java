public class SPL extends Solver{
//    private MatriksSPL M;


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


}
