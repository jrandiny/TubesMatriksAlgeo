import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("TUBES ALGEO");
        Scanner scan = new Scanner(System.in);

        int opt = scan.nextInt();

        Matriks M1 = new Matriks(3,3);
        M1.baca();
        M1.print();

        System.out.println(M1.nolBanyak());

        M1.print();
    }
}
