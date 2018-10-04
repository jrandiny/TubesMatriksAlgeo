import java.util.Scanner;

public class Main {
    private static int promptUtama(){
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linear");
        System.out.println("2. Interpolasi Polinom");
        System.out.println("3. Keluar");

        Scanner scan = new Scanner(System.in);

        int menu = -99;
        while((menu != 1)&&(menu!=2)&&(menu!=3)){
            System.out.print("Opsi : ");
            menu = scan.nextInt();
        }

        return menu;
    }

    private static int promptOpsi(){
        System.out.println("PILIH METODE");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");

        Scanner scan = new Scanner(System.in);

        int opt = -99;
        while((opt != 1)&&(opt!=2)){
            System.out.print("Opsi : ");
            opt = scan.nextInt();
        }

        return opt;
    }

    private static int promptInput(){
        System.out.println("PILIH TIPE INPUT");
        System.out.println("1. File");
        System.out.println("2. Keyboard");

        Scanner scan = new Scanner(System.in);

        int input = -99;
        while((input != 1)&&(input!=2)){
            System.out.print("Opsi : ");
            input = scan.nextInt();
        }

        return input;
    }

    public static void main(String[] args) {
        Output.setShowLog(false);
        System.out.println("TUBES ALGEO");

        int opsi = promptUtama();

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

        int metode = promptOpsi();
        int input = promptInput();

        if(input==1){
            System.out.print("Nama file : ");

            Scanner scn = new Scanner(System.in);

            String namaFile = scn.nextLine();

            MIn.bacaFile(namaFile);
        }else{
            MIn.baca();
        }

        if(metode==1){
            MIn.gauss();
        }else{
            System.out.println("Aa");
            MIn.gauss();
        }


        slv.setM(MIn);

        slv.solveAll();

        slv.outputHasil();





    }
}
