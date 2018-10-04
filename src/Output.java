import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Output {
    private static FileWriter fw;
    private static PrintWriter pw;
    private static String temp;

    private static boolean showLog;

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

    public static void logln() {
        if (showLog) {
            System.out.println();
        }
    }

    public static void startTulis(){

        try {
            fw = new FileWriter("output.txt");
            pw = new PrintWriter(fw);
        } catch (IOException e) {
            System.out.println("Error : "+e.getMessage());
        }
    }

    public static void tulisFile(){
        if(pw!=null){
            pw.print(temp);
        }
    }

    public static void print(String var) {
        System.out.print(var);
        if (temp == null)
            temp = var;
        else
            temp = temp + var;
    }

    public static void println(String var) {
        System.out.println(var);
        if (temp == null)
            temp = var;
        else
            temp = temp + "\n" + var;
    }

    public static void println() {
        System.out.println();
        if (temp == null)
            temp = "\n";
        else
            temp = temp + "\n";
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
        if(pw!=null){
            pw.close();
        }
    }
}
