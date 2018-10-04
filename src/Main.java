import java.util.Scanner;

public class Main {
    /**
     * Memilih menu penyelesaian
     */
    private static int promptUtama(Scanner scan){
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linear");
        System.out.println("2. Interpolasi Polinom");
        System.out.println("3. Keluar");

        int menu = -99;
        while((menu != 1)&&(menu!=2)&&(menu!=3)){
            System.out.print("Opsi : ");
            menu = scan.nextInt();
        }

        return menu;
    }

    /**
     * Memilih menu metode
     */
    private static int promptOpsi(Scanner scan){
        System.out.println("PILIH METODE");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");

        int opt = -99;
        while((opt != 1)&&(opt!=2)){
            System.out.print("Opsi : ");
            opt = scan.nextInt();
        }

        return opt;
    }

    private static int promptCetak(Scanner scan){
        System.out.println("Apakah mau disimpan ke file (0/1)?");

        int opt = -99;
        while((opt != 0)&&(opt!=1)){
            System.out.print("Opsi : ");
            opt = scan.nextInt();
        }

        return opt;
    }
    /**
     * Memilih menu input
     */
    private static int promptInput(Scanner scan){
        System.out.println("PILIH TIPE INPUT");
        System.out.println("1. File");
        System.out.println("2. Keyboard");

        int input = -99;
        while((input != 1)&&(input!=2)){
            System.out.print("Opsi : ");
            input = scan.nextInt();
        }

        return input;
    }

    /**
     * Main program, menyatukan semuanya
     */
    public static void main(String[] args) {
        Output.setShowLog(false);
        System.out.println("TUBES ALGEO");
        Scanner scn = new Scanner(System.in);
        double temp;

        int opsi = promptUtama(scn);

        Matriks MIn;
        Solver slv;

        MIn = new MatriksSPL();
        slv = new SPL();

        if(opsi==3){
            System.exit(0);
        }else if(opsi==2){
            MIn = new MatriksInterpolasi();
            slv = new Interpolasi();

        }else{
            MIn = new MatriksSPL();
            slv = new SPL();
        }

        int metode = promptOpsi(scn);
        int input = promptInput(scn);

        if(input==1){
            System.out.print("Nama file : ");

            scn = new Scanner(System.in);

            String namaFile = scn.nextLine();

            MIn.bacaFile(namaFile);
        }else{
            MIn.baca();
        }

        if(metode==1){
            MIn.gauss();
        }else{
            MIn.gaussJordan();
        }

        Output.println("Matriks hasil");
        MIn.printF();

        Output.println("Hasil persamaan");

        slv.setM(MIn);

        slv.solveAll();

        slv.outputHasil();

        Output.println();

        if(opsi==2){
            System.out.print("Masukkan x yang mau dihitung = ");
            temp = scn.nextDouble();
            Interpolasi slvInter = (Interpolasi) slv;
            slvInter.cobaHasil(temp);
        }

        int cetak = promptCetak(scn);

        if(cetak ==1){
            System.out.print("Nama file : ");

            scn = new Scanner(System.in);

            String namaFile = scn.nextLine();

            Output.startTulis(namaFile);

            Output.tulisFile();

            Output.selesaiTulis();
        }

    }
}
