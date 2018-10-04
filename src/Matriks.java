public abstract class Matriks {
    protected double[][] isi;
    protected int NKol;
    protected int NBrs;
    protected boolean solved;
    protected final int IDXUNDEF = -99;

    /**
     * Constuctor
     */
    public Matriks(){
        solved = false;
        NBrs = 0;
        NKol = 0;
    }

    /**
     * Membaca input matriks dari file
     * @param namaFile nama file input
     */
    public abstract void bacaFile(String namaFile);

    /**
     * Membaca matriks dari terminal/console
     */
    public abstract void baca();


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
        for (int j = 0; j < getCol(); j++) {
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
    public void Gauss() {
        double tempLead;
        int baris = 0;
        int kolom = baris;
        while (baris < getBrs() && kolom < getCol()) {
            Output.logln();
            Output.logln("START ROW BARU " + baris);

            int k = baris;
            int l = kolom;
            Output.logln("CEK 0");
            if (NolSebaris(baris)) {

            } else {
                while (get(k, l) == 0) {
                    Output.logln("0 detected, cek bukan 0 di " + k + "," + l);
                    k++;
                    Output.logln("K++ -> " + k + "/" + getBrs());
                    if (k >= getBrs()) {
                        k = baris;
                        l++;
                        kolom++;
                        Output.logln("Increment kolom ke " + l);
                        if (l > getCol() - 2) {
                            Output.logln("ERRORRRRRR");
                        }
                    }
                }

                if (k != baris) {
                    Output.logln("TUKAR CALLED " + baris + " dengan " + k);
                    TukarRow(baris, k);
                    print();
                }

                tempLead = get(baris, kolom);

                Kali(baris, (1 / tempLead));

                Output.logln("MATRIKS SUDAH DIKALI");
                print();

                int j = baris + 1;
                while (j < getBrs()) {
                    Tambah(j, baris, -get(j, kolom));
                    j++;
                }

                System.out.println("HASIL PROSES");
                print();
            }


            baris++;
            kolom++;

        }
    }


//  public  void GaussJordanEl()
//  {
//    int i, j, k, l, m, n, o, p, q, row, col;
//    int max, div;
//
//    row = getBrs();
//    col = getCol();
//    if (row > col){
//      i = row;
//    }
//    else{
//        i = col;
//    }
//    for (j = 0; j < i; j++){
//      max = j;
//      for (k = j + 1; k < i; k++){
//        if (Math.abs(isi[k][j]) > Math.abs(isi[max][j])){
//            max = k;
//        }
//      }
//      double[] temp1;
//      temp1 = M[j];
//      M[j] = M[max];
//      M[max] = temp1;
//      double temp2;
//      temp2 = N[j];
//      N[j] = N[max];
//      N[max] = temp2;
//
//      //Agar nilai pivot menjadi 1
//      l = j;
//      while ((l < col) && (isi[j][l] == 0)){
//        l++;
//      }
//      if (l < col){
//          div = isi[j][l];
//          for (m = j; m < col; m++){
//                isi[j][m] = isi[j][m] / div;
//          }
//         N[j] = N[j] / div;
//      }
//
//      //Agar elemen-elemen di bawah pivot bernilai 0
//      for (n = j + 1; n < row; n++){
//            div = isi[n][l] / isi[j][l];
//            for (o = 0; o < col; o++){
//                isi[n][o] = isi[n][o] - (div * isi[j][o]);
//            }
//            N[n] = N[n] - (div * N[j]);
//
//      }
//
//    }
//
//    boolean stop = false;
//    for (p = 0; p < col; p++){
//        q = row - 1;
//        while ((isi[q][p] != 1) && (q >= 0)){
//            if ((p == col - 1) && (isi[q][p] == 0)){
//                stop = true;
//                break;
//            }
//            q--;
//        }
//      if (stop) break;
//      double temp3;
//      if (q != 0){
//          for (i = 0; i < q; i++){
//              temp3 = isi[i][p] / isi[q][p];
//              for (j = 0; j < col; j++){
//                  isi[i][j] = isi[i][j] - (temp3 * isi[q][j]);
//              }
//              N[i] = N[i] - (temp3 * N[q]);
//          }
//      }
//    }
//  }

}
