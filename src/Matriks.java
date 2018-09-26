import java.util.Scanner;

public class Matriks {
    private double[][] isi;
    private final int NMAX = 100;
    private final int IDXUNDEF = -99;

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

    public void Tambah(int row,int rowLain){
        this.Tambah(row,rowLain,1);
    }

    public void Tambah(int row, int rowLain, int kali){
        //nambahin row 1 dgn row lainnya
        for (int j = 0; j < isi[row].length; j++) {
            isi[row][j] = isi[row][j] + kali*isi[rowLain][j];
        }
    }

    public void Kali(int row, int X){
        //kali isi dari row dgn X
        for (int j = 0; j < isi[row].length; j++) {
            isi[row][j] = isi[row][j] * X;
        }
    }

    public void TukarRow(int row1, int row2){
        //nuker isi row1 dengan isi row2
        double[][] temp = new double[isi.length][isi[1].length];

        for (int j = 0; j < isi[row1].length; j++) {
            temp[row1][j] = isi[row1][j];
            isi[row1][j] = isi[row2][j];
            isi[row2][j] = temp[row1][j];
        }
    }

    public int nolBanyak(){
        //cari baris/row yang 0 nya paling banyak
        int max = -999;
        int NMax,row;

        row = IDXUNDEF;

        for (int i = 0; i < isi.length; i++) {
            NMax = 0;
            for (int j = 0; j < isi[i].length; j++) {
                if (isi[i][j] == 0) {
                    NMax = NMax + 1;
                }
            }
            if (max < NMax) {
                max = NMax;
                row = i;
            }
        }
        return row;
    }
}
