import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Output {
    // KAMUS GLOBAL
    private static FileWriter fw;
    private static PrintWriter pw;
    private static String temp;

    private static boolean showLog;

    public static boolean isShowLog() {
        return showLog;
    }

    /**
     * mengeset showlog. Showlog sendiri mengatur apakah suatu pernyataan akan ditampilkan/ditulis atau tidak
     */
    public static void setShowLog(boolean showLog) {
        Output.showLog = showLog;
    }

    /**
     * Menampilkan pernyataan-pernyataan yang tersembunyi, apabila showlog bernilai TRUE
     */
    public static void log(String var) {
        if (showLog) {
            System.out.print(var);
        }
    }

    /**
     * Log, tapi di-enter
     */
    public static void logln(String var) {
        if (showLog) {
            System.out.println(var);
        }
    }

    /**
     * Log, tapi hanya enter
     */
    public static void logln() {
        if (showLog) {
            System.out.println();
        }
    }

    /**
     * Membuat file external baru dan menyiapkan program untuk menulis ke file external
     * @param namaFile nama file untuk output
     */
    public static void startTulis(String namaFile){

        try {
            fw = new FileWriter(namaFile);
            pw = new PrintWriter(fw);
        } catch (IOException e) {
            System.out.println("Error : "+e.getMessage());
        }
    }

    /**
     * menuliskan string yang disimpan di temp ke file external
     */
    public static void tulisFile(){
        if(pw!=null){
            pw.print(temp);
        }
    }

    /**
     * Mengeprint string ke layar, dan menyimpannya ke temp, untuk disiapkan untuk ditulis ke file external
     * @param var string yang mau ditampilkan
     */
    public static void print(String var) {
        System.out.print(var);
        if (temp == null)
            temp = var;
        else
            temp = temp + var;
    }

    /**
     * prosedur print, tapi di-enter
     * @param var string yang mau ditampilkan
     */
    public static void println(String var) {
        System.out.println(var);
        if (temp == null)
            temp = var +"\n";
        else
            temp = temp + var + "\n";
    }

    /**
     * prosedur println seperti di atas, tapi tanpa parameter
     */
    public static void println() {
        System.out.println();
        if (temp == null)
            temp = "\n";
        else
            temp = temp + "\n";
    }

    /**
     * selesai menulis ke file external dengan cara men-close printer
     */
    public static void selesaiTulis() {
        if(pw!=null){
            pw.close();
            temp = "";
        }
    }
}
