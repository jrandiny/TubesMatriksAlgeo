public class Interpolasi extends Solver{

    /**
     * Menampilkan output hasil interpolasi
     */
    @Override
    public void outputHasil() {
        int i = 0;

        Token resultToken;

        Output.print("f(x) = ");

        while (i < M.getBrs()-1) {
            if(hasil[i].type==2){
                resultToken = getParametrik(i);
                Output.print(Double.toString(resultToken.daftarToken.get(0).angka));
            }else{
                Output.print(Double.toString(hasil[i].angka));
            }

            if(i!=0){
                Output.print("x^");
                Output.print(Integer.toString(i));
            }
            Output.print(" + ");
            i++;
        }

        Output.print(Double.toString(hasil[i].angka));
        Output.print("x^");
        Output.print(Integer.toString(i));
    }

    /**
     * Mencoba angka x ke hasil interpolasi
     * @param x x yang mau dimasukkan
     */
    public void cobaHasil(double x){
        Output.print(String.format("f(%.2f) = ",x));
        double total = 0;
        Token resultToken;

        for (int i = 0; i < jumlahUnknown; i++) {
            if(hasil[i].type==2){
                resultToken = getParametrik(i);
                total = total + (Math.pow(x,i) * resultToken.daftarToken.get(0).angka);
            }else{
                total = total + (Math.pow(x,i) * hasil[i].angka);
            }
        }

        Output.println(String.format("%.2f",total));

    }




}
