import java.util.ArrayList;

public class Token {
    /**
     * Type :
     * 0 = variabel
     * 1 = angka
     * 2 = parametrik
     */
    public int type;
    public double angka;
    public int unknownKe;
    public ArrayList<Token> daftarToken;

    public Token(){

    }

    public Token(int type, double angka, int unknownKe){
        this.type = type;
        this.angka = angka;
        this.unknownKe = unknownKe;
    }

    public Token(int type, double angka){
        this.type = type;
        this.angka = angka;
    }
}
