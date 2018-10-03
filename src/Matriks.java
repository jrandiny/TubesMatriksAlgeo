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

    /**
     * Membaca input interpolasi dari file
     * @param namaFile Nama file interpolasi
     */
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

    /**
     * Membaca input file SPL
     * @param namaFile Nama file matriks SPL
     */
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

    /**
     * Mencetak isi matriks ke layar
     */
    public void print(){
        for (int i = 0; i < NBrs; i++) {
            for (int j = 0; j < NKol; j++) {
                System.out.print(this.get(i,j)+" ");
            }
            System.out.println();
        }
    }

    /**
     * Membaca SPL dari terminal
     */
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

    /**
     * Membaca input interpolasi dari terminal
     */
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

    /**
     * Mengembalikan jumlah baris
     * @return jumlah baris
     */
    public int getBrs(){
        return NBrs;
    }

    /**
     * Mengembalikan jumlah kolom (termasuk augmented)
     * @return jumlah kolom
     */
    public int getCol(){
        return NKol;
    }

    /**
     * Mengembalikan isi matriks di posisi tertentu
     * @param row baris
     * @param col kolom
     * @return isi matriks
     */
    public double get(int row, int col){
        return isi[row][col];
    }

    /**
     * Mengisi matriks di posisi tertentu
     * @param row baris
     * @param col kolom
     * @param isi data yang ingin dimasukkan
     */
    public void set(int row, int col, double isi){
        this.isi[row][col] = isi;
    }

    /**
     * Apakah matriks sudah digauss atau gauss-jordan
     * @return solved
     */
    public boolean isSolved(){
        return this.solved;
    }

    /**
     * Menambah baris matriks ke baris lain
     * @param row baris yang ditambah
     * @param rowLain baris yang digunakan untuk menambah
     */
    public void Tambah(int row,int rowLain){
        this.Tambah(row,rowLain,1);
    }

    /**
     * Menambah baris matriks ke baris lain dengan dikali juga
     * @param rowDitambah baris yang ditambah
     * @param rowPenambah baris yang digunakan untuk menambah
     * @param kali faktor pengali
     */
    private void Tambah(int rowDitambah, int rowPenambah, double kali){
        //nambahin row 1 dgn row lainnya
        for (int j = 0; j < isi[rowDitambah].length; j++) {
            isi[rowDitambah][j] = isi[rowDitambah][j] + kali*isi[rowPenambah][j];
        }
    }

    /**
     * Mengali baris dengan konstanta tertentu
     * @param row baris yang ingin dikali
     * @param X faktor pengali
     */
    private void Kali(int row, double X){
        //kali isi dari row dgn X
        for (int j = 0; j < isi[row].length; j++) {
            isi[row][j] = isi[row][j] * X;
        }
    }

    /**
     * Menukar baris
     * @param row1 baris yang ingin ditukar
     * @param row2 baris yang ingin ditukar
     */
    private void TukarRow(int row1, int row2){
        //nuker isi row1 dengan isi row2
        double[][] temp = new double[isi.length][isi[1].length];

        for (int j = 0; j < isi[row1].length; j++) {
            temp[row1][j] = isi[row1][j];
            isi[row1][j] = isi[row2][j];
            isi[row2][j] = temp[row1][j];
        }
    }

    /**
     * Cari indeks baris dengan nol paling banyak
     * @return indeks
     */
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

    /**
     * Mengembalikan true jika 1 baris nol semua
     * @param row baris yang ingin dicek
     * @return true jika nol semua
     */
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

    /**
     * Megubah matriks menjadi echelon form
     */
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
        
        public class GaussJordanEl()

{

  public  void GaussJordanEl()
  {
    int i, j, k, l, m, n, o, p, q, row, col;
    int max, div;
    
    row = getBrs();
    col = getCol();
    if (row > col){
      i = row;
    }
    else{
        i = col;
    }
    for (j = 0; j < i; j++){
      max = j;
      for (k = j + 1; k < i; k++){
        if (Math.abs(isi[k][j]) > Math.abs(isi[max][j])){
            max = k;
        }
      }
      double[] temp1;
      temp1 = M[j];
      M[j] = M[max];
      M[max] = temp1;
      double temp2;
      temp2 = N[j];
      N[j] = N[max];
      N[max] = temp2;

      //Agar nilai pivot menjadi 1
      l = j;
      while ((l < col) && (isi[j][l] == 0)){
        l++;
      }
      if (l < col){
          div = isi[j][l];
          for (m = j; m < col; m++){
                isi[j][m] = isi[j][m] / div;
          }
         N[j] = N[j] / div;
      }

      //Agar elemen-elemen di bawah pivot bernilai 0
      for (n = j + 1; n < row; n++){
            div = isi[n][l] / isi[j][l];
            for (o = 0; o < col; o++){
                isi[n][o] = isi[n][o] - (div * isi[j][o]);
            }
            N[n] = N[n] - (div * N[j]);

      }

    }

    boolean stop = false;
    for (p = 0; p < col; p++){
        q = row - 1;
        while ((isi[q][p] != 1) && (q >= 0)){
            if ((p == col - 1) && (isi[q][p] == 0)){
                stop = true;
                break;
            }
            q--;
        }
      if (stop) break;
      double temp3;
      if (q != 0){
          for (i = 0; i < q; i++){
              temp3 = isi[i][p] / isi[q][p];
              for (j = 0; j < col; j++){
                  isi[i][j] = isi[i][j] - (temp3 * isi[q][j]);
              }
              N[i] = N[i] - (temp3 * N[q]);
          }
      }
    }
  }
        }
    }

}
