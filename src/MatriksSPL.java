import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MatriksSPL extends Matriks {

    /**
     * Membaca input file SPL
     * @param namaFile Nama file matriks SPL
     */
    @Override
    public void bacaFile(String namaFile) {
        int row = 0;
        ArrayList<ArrayList<Double>> temp = new ArrayList<>();

        try {
            File file = new File(namaFile);

            Scanner scn = new Scanner(file);

            while(scn.hasNextLine()){
                String line = scn.nextLine();

                temp.add(new ArrayList<>());

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
     * Membaca SPL dari terminal
     */
    @Override
    public void baca() {
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
}
