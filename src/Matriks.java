import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
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

    public void bacaFileInterpolasi(String namaFile){
        File file = new File(namaFile);

        int titik = 0;
        ArrayList<Double[]> temp = new ArrayList<Double[]>();

        try {
            Scanner scn = new Scanner(file);

            while (scn.hasNextLine()){
                String line = scn.nextLine();
                temp.add(new Double[2]);

                Scanner linescn = new Scanner(line);

                temp.get(titik)[0] = linescn.nextDouble();
                temp.get(titik)[1] = linescn.nextDouble();

                titik++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(titik != 0){
            isi = new double[titik][titik+1];
            NKol = titik + 1;
            NBrs = titik;

            for (int i = 0;i<temp.size();i++){
                for(int kolom = 0;kolom<titik;kolom++){
                    isi[i][kolom] = Math.pow(temp.get(i)[0],kolom);
                }
                isi[i][titik] = temp.get(i)[1];
            }
        }
    }

    public void bacaFileSPL(String namaFile){
        int row = 0;
        ArrayList<ArrayList<Double>> temp = new ArrayList<ArrayList<Double>>();

        try {
            File file = new File(namaFile);

            Scanner scn = new Scanner(file);

            while(scn.hasNextLine()){
                String line = scn.nextLine();

                temp.add(new ArrayList<Double>());

                Scanner linescn = new Scanner(line);

                while(linescn.hasNextDouble()){
                    temp.get(row).add(linescn.nextDouble());
                }

                row++;
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
        for (int i = 0; i < NBrs; i++) {
            for (int j = 0; j < NKol; j++) {
                System.out.print(this.get(i,j)+" ");
            }
            System.out.println();
        }
    }

    public void bacaSPL(){
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

    public void bacaInterPolasi(){
        Scanner scn = new Scanner(System.in);
        double content;

        int jumlah = scn.nextInt();
        double[][] temp = new double[jumlah][2];

        for (int i = 0; i < jumlah; i++) {
            temp[i][0] = scn.nextDouble();
            temp[i][1] = scn.nextDouble();
        }

        NBrs = jumlah;
        NKol = jumlah +1;

        isi = new double[NBrs][NKol];

        for (int i = 0;i<jumlah;i++){
            for(int kolom = 0;kolom<jumlah;kolom++){
                isi[i][kolom] = Math.pow(temp[i][0],kolom);
            }
            isi[i][jumlah] = temp[i][1];
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

    private void Tambah(int rowDitambah, int rowPenambah, double kali){
        //nambahin row 1 dgn row lainnya
        for (int j = 0; j < isi[rowDitambah].length; j++) {
            isi[rowDitambah][j] = isi[rowDitambah][j] + kali*isi[rowPenambah][j];
        }
    }

    private void Kali(int row, double X){
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

    public boolean NolSebaris(int row) {
        int j = 0;
        int i = row;
        boolean allNol = true;

        while (j < getCol()) {
            if (get(i,j) != 0) {
                allNol = false;
            }
            j++;
        }

        return allNol;
    }

    public void Gauss(){
        double tempLead;
        int baris = 0;
        int kolom = baris;
        while(baris<getBrs()&&kolom<getCol()){
            System.out.println();
            System.out.println("START ROW BARU "+baris);

            int k = baris;
            int l = kolom;
            System.out.println("CEK 0");
            if(NolSebaris(baris)){

            }else{
                while (get(k,l)==0){
                    System.out.println("0 detected, cek bukan 0 di "+k+","+l);
                    k++;
                    System.out.println("K++ -> "+k+"/"+getBrs());
                    if(k>=getBrs()){
                        k = baris;
                        l++;
                        kolom++;
                        System.out.println("Increment kolom ke "+l);
                        if(l>getCol()-2){
                            System.out.println("ERRORRRRRR");
                        }
                    }
                }

                if(k != baris){
                    System.out.println("TUKAR CALLED "+baris+" dengan "+k);
                    TukarRow(baris,k);
                    print();
                }

                tempLead = get(baris,kolom);

                Kali(baris,(1/tempLead));

                System.out.println("MATRIKS SUDAH DIKALI");
                print();

                int j = baris+1;
                while (j<getBrs()){
                    Tambah(j,baris,-get(j,kolom));
                    j++;
                }

                System.out.println("HASIL PROSES");
                print();
            }


            baris++;
            kolom++;

        }
    }

//    public void Gauss(float[][] M, float[] T) {
//		int i, j, k, p, brs, kol, x;
//		int max;
//		float temp;
//
//		brs = M.length; kol = M[0].length;
//		if (brs>kol) {
//                x=kol;}
//        else {
//                x=brs;};
//
//		p=0;
//		for (k=0; k<x; k++) {
//			//Mencari baris yg memuat elemen max
//			max = k;
//			for (i=k; i<brs; i++) {
//				if (Math.abs(M[i][k])>Math.abs(M[max][k])) {
//					max = i;
//				}
//			}
//
//			//Menukar baris yang ditunjuk dengan baris yg memuat elemen max
//			TukarRow(k,max);
//			temp = T[k];
//			T[k] = T[max];
//			T[max] = temp;
//
//            //Mencari kolom dalam baris k yang elemennya bukan 0
//			p = k;
//			while (p<kol && M[k][p]==0) {
//				p++;
//			}
//			if (p<kol) {          //jika l<n maka paling tidak elemen di baris k dan kolom terakhir adalah bukan 0
//				//Membuat elemen di baris k kolom awal menjadi 1
//				temp = M[k][1];
//				if (temp!=0) {
//					T[k] = T[k] /  temp;
//					for (j=k; j<kol; j++) {
//						M[k][j] = M[k][j] / temp;
//					}
//				}
//				//Membuat elemen-elemen di bawah elemen 1 menjadi 0
//				for (i = k+1; i < brs; i++) {
//					temp = M[i][p] / M[k][p];
//					T[i] = T[i] - (temp * T[k]);
//					Tambah(i,k,-temp);
//					}
//				}
//			}
//		}
    
//    public void GaussJordan(float[][] M, float[] T) {
//		int i, j, k, p, brs, kol, x;
//		int max;
//		float temp;
//
//		Gauss(M,T);
//		for (k=0; k<kol; k++) {
//			i=brs-1;
//			while (i>=0 && M[i][k]!=1) {
//				i--;
//			}
//
//			if (i!=0 && i>=0) {
//				for (p=0; p<i; p++) {
//					temp = M[p][k] / M[i][k];
//					T[p] = T[p] - (temp * T[i]);
//					Tambah(p,i,-temp);
//				}
//			}
//		}
//		}

}
