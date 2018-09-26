import java.io.Console;
import java.util.Scanner;

public class Matriks {
    private double[][] isi;
    private final int NMAX = 100;

    public Matriks(int row, int col){
        isi = new double[row][col];
    }

    public Matriks(){
        isi = new double[NMAX][NMAX];
    }

    public void print(){
        for (int i = 0; i < isi.length; i++) {
            for (int j = 0; j < isi[i].length; j++) {
                System.out.print(this.get(i,j)+" ");
            }
            System.out.println();
        }
    }

    public void baca(){
        Scanner scn = new Scanner(System.in);
        double content;

        for (int i = 0; i < isi.length; i++) {
            for (int j = 0; j < isi[i].length; j++) {
                content = scn.nextDouble();
                this.set(i,j,content);
            }
        }
    }

    public double get(int row, int col){
        return isi[row][col];
    }

    public void set(int row, int col, double isi){
        this.isi[row][col] = isi;
    }
}
