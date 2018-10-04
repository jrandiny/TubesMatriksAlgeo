public abstract class Matriks {
    protected double[][] isi;
    protected int NKol;
    protected int NBrs;

    /**
     * Constuctor
     */
    public Matriks(){
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
     * Mengembalikan true jika 1 baris nol semua
     * @param row baris yang ingin dicek
     * @return true jika nol semua
     */
    private boolean NolSebaris(int row) {
        int j = 0;
        boolean allNol = true;

        while (j < getCol()) {
            if (get(row,j) != 0) {
                allNol = false;
            }
            j++;
        }

        return allNol;
    }

    /**
     * Megubah matriks menjadi echelon form
     */
    public void gauss() {
        double tempLead;
        int baris = 0;
        int kolom = baris;
        while (baris < getBrs() && kolom < getCol()) {
            Output.logln();
            Output.logln("START ROW BARU " + baris);

            int k = baris;
            int l = kolom;
            Output.logln("CEK 0");
            if (!NolSebaris(baris)) {
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
                }

                tempLead = get(baris, kolom);

                Kali(baris, (1 / tempLead));

                Output.logln("MATRIKS SUDAH DIKALI");

                int j = baris + 1;
                while (j < getBrs()) {
                    Tambah(j, baris, -get(j, kolom));
                    j++;
                }

            }

            baris++;
            kolom++;

        }
    }

    /**
     * Mengubah matriks menjadi Matriks Gauss Jordan
     */
    public void gaussJordan() {
        gauss(); //matriksnya di gauss dulu
        int i = getBrs()-2; //sebenernya ini ga penting, tapi uda terlanjur bikin
        int j = getCol()-2; //kolom matriks
        int baris; //baris yang bakal dikurangin
        int barisKurang; //baris yang jadi pengurang (nilai M[barisKurang][j] harus 1

        while (j >= 0) { //stop saat j < 0

            barisKurang = getBrs() - 1; //baris yang digunakan untuk pengurang

            while ((get(barisKurang, j) != 1) && (barisKurang > 1)) { //stop kalau ketemu yang nilainya 1, atau barisKurang mentok
                barisKurang--; //cari barisKurang yang nilai M[bK][j] = 1 buat jadi pengurang
            }

            if (get(barisKurang, j) == 1) { //kalau M[barisKurang][j] nilainya 1
                baris = barisKurang - 1; //baris yang bakal dikurang
                while (baris >= 0) {
                    if (get(baris, j) != 0) { //cari baris yang bakal dikurang, yang masih belum nol
                        Tambah(baris, barisKurang, -(get(baris, j))); //M[baris][j] dijadiin nol
                    }
                    baris--;
                }
            }

            j--; //ganti kolom
        }
    }

}
