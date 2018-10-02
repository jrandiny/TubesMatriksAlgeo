import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Matriks {
    private double[][] isi;
    private int NKol;
    private int NBrs;
    private boolean solved;
    private final int NMAX = 100;
    private final int IDXUNDEF = -99;

    public Matriks(int row, int col){
        NBrs = 0;
        NKol = 0;
        solved = false;
        isi = new double[row][col];
    }

    public Matriks(){
        solved = false;
        NBrs = 0;
        NKol = 0;
        isi = new double[NMAX][NMAX];
    }

    public void bacaFile(String namaFile){
        File file = new File(namaFile);
        int row = 0;
        ArrayList<ArrayList<Double>> temp = new ArrayList<ArrayList<Double>>();

        try {
            Scanner scn = new Scanner(file).useDelimiter(" ");

            while(scn.hasNextLine()){
                temp.add(new ArrayList<Double>());
                while(scn.hasNextDouble()){
                    temp.get(row).add(scn.nextDouble());
                }
                row++;
                scn.nextLine();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        if(row != 0){
            NBrs = row;
            NKol = temp.get(0).size();

            isi = new double[NBrs][NKol];

            for (int i = 0; i < temp.size(); i++) {
                for (int j = 0; j < temp.get(0).size(); j++) {
                    isi[i][j] = temp.get(i).get(j);
                }
            }

        }

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

        NBrs = scn.nextInt();
        NKol = scn.nextInt();

        isi = new double[NBrs][NKol];

        for (int i = 0; i < isi.length; i++) {
            for (int j = 0; j < isi[i].length; j++) {
                content = scn.nextDouble();
                isi[i][j] = content;
            }
        }
    }

    public int getBrs(){
        return NBrs;
    }

    public int getCol(){
        return NKol;
    }

    public double get(int row, int col){
        return isi[row][col];
    }

    public void set(int row, int col, double isi){
        this.isi[row][col] = isi;
    }

    public boolean isSolved(){
        return this.solved;
    }

    public void Tambah(int row,int rowLain){
        this.Tambah(row,rowLain,1);
    }

    private void Tambah(int row, int rowLain, int kali){
        //nambahin row 1 dgn row lainnya
        for (int j = 0; j < isi[row].length; j++) {
            isi[row][j] = isi[row][j] + kali*isi[rowLain][j];
        }
    }

    private void Kali(int row, int X){
        //kali isi dari row dgn X
        for (int j = 0; j < isi[row].length; j++) {
            isi[row][j] = isi[row][j] * X;
        }
    }

    private void TukarRow(int row1, int row2){
        //nuker isi row1 dengan isi row2
        double[][] temp = new double[isi.length][isi[1].length];

        for (int j = 0; j < isi[row1].length; j++) {
            temp[row1][j] = isi[row1][j];
            isi[row1][j] = isi[row2][j];
            isi[row2][j] = temp[row1][j];
        }
    }

    private int nolBanyak(){
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
    
    public void Gauss(float[][] M, float[] T) {
		int i, j, k, p, brs, kol, x;
		int max;
		float temp;

		brs = M.length; kol = M[0].length;
		if (brs>kol) {
                x=kol;}
        else {
                x=brs;};

		p=0;
		for (k=0; k<x; k++) {
			//Mencari baris yg memuat elemen max
			max = k;
			for (i=k; i<brs; i++) {
				if (Math.abs(M[i][k])>Math.abs(M[max][k])) {
					max = i;
				}
			}

			//Menukar baris yang ditunjuk dengan baris yg memuat elemen max
			TukarRow(k,max);
			temp = T[k];
			T[k] = T[max];
			T[max] = temp;

            //Mencari kolom dalam baris k yang elemennya bukan 0
			p = k;
			while (p<kol && M[k][p]==0) {
				p++;
			}
			if (p<kol) {          //jika l<n maka paling tidak elemen di baris k dan kolom terakhir adalah bukan 0
				//Membuat elemen di baris k kolom awal menjadi 1
				temp = M[k][1];
				if (temp!=0) {
					T[k] = T[k] /  temp;
					for (j=k; j<kol; j++) {
						M[k][j] = M[k][j] / temp;
					}
				}
				//Membuat elemen-elemen di bawah elemen 1 menjadi 0
				for (i = k+1; i < brs; i++) {
					temp = M[i][p] / M[k][p];
					T[i] = T[i] - (temp * T[k]);
					Tambah(i,k,-temp);
					}
				}
			}
		}
    
    public void GaussJordan(float[][] M, float[] T) {
		int i, j, k, p, brs, kol, x;
		int max;
		float temp;

		Gauss(M,T);
		for (k=0; k<kol; k++) {
			i=brs-1;
			while (i>=0 && M[i][k]!=1) {
				i--;
			}

			if (i!=0 && i>=0) {
				for (p=0; p<i; p++) {
					temp = M[p][k] / M[i][k];
					T[p] = T[p] - (temp * T[i]);
					Tambah(p,i,-temp);
				}
			}
		} 
		}

}
