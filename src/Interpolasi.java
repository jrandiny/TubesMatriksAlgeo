public class Interpolasi extends Solver{

    @Override
    public void outputHasil() {
        int i = 0;

        Output.print("f(x) = ");

        Output.print(Double.toString(hasil[i].angka));
        Output.print(" + ");
        i++;

        while (i < M.getBrs()-1) {
            Output.print(Double.toString(hasil[i].angka));
            Output.print("x^");
            Output.print(Integer.toString(i));
            Output.print(" + ");
            i++;
        }

        Output.print(Double.toString(hasil[i].angka));
        Output.print("x^");
        Output.print(Integer.toString(i));
    }




}
