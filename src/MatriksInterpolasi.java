import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MatriksInterpolasi extends Matriks {

    /**
     * Membaca input interpolasi dari file
     * @param namaFile Nama file interpolasi
     */
    @Override
    public void bacaFile(String namaFile){
        File file = new File(namaFile);

        int titik = 0;
        ArrayList<Double[]> temp = new ArrayList<>();

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
            System.out.println("Error : "+e.getMessage());
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
     * Membaca input interpolasi dari terminal
     */
    @Override
    public void baca() {
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
}
