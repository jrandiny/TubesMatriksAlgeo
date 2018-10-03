import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Output {
    private static FileWriter fw;
    private static PrintWriter pw;

    public static boolean showLog;

    public static boolean isShowLog() {
        return showLog;
    }

    public static void setShowLog(boolean showLog) {
        Output.showLog = showLog;
    }

    public static void log(String var) {
        if (showLog) {
            System.out.print(var);
        }
    }

    public static void logln(String var) {
        if (showLog) {
            System.out.println(var);
        }
    }

    public static void startTulis(){

        try {
            fw = new FileWriter("output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw = new PrintWriter(fw);

    }

    public static void print(String var) {
        System.out.print(var);
        pw.print(var);
    }

    public static void println(String var) {
        System.out.println(var);
        pw.println(var);
    }

    public static void tulisMatriks(Matriks M) {



            for (int i = 0; i < M.getBrs(); i++) {
                for (int j = 0; j < M.getCol(); j++) {
                    pw.print(M.get(i,j));
                    pw.print(" ");
                }
                pw.println();
            }
            pw.println();

    }

    public static void selesaiTulis() {

        pw.close();
    }
}
