import java.util.ArrayList;

public class Token {
    /**
     * Type :
     * 0 = variabel
     * 1 = angka
     * 2 = parametrik
     *
     * angka = angkanya jika tipe angka atau variabel, atau base angka jika tipe parametrik
     * unknownKe = untuk menandakan variabel unknown keberapa
     * daftarToken = untuk rekursi (tipe 2)
     *
     * Jadi misalkan
     * 1 2 3 5
     * 0 1 0 4
     *
     * X0 akan bertipe 2, dan nanti daftarToken akan diisi token untuk
     * - X1 yang bertipe 1 dengan nilai 4
     * - X2 yang bertipe 0
     */
    public int type;
    public double angka;
    public int unknownKe;
    public ArrayList<Token> daftarToken;

    /**
     * Empty constructor
     */
    public Token(){

    }

    /**
     * Constructor dengan tipe, angka, dan unknownKe
     * @param type tipe
     * @param angka angka
     * @param unknownKe unknownKe
     */
    public Token(int type, double angka, int unknownKe){
        this.type = type;
        this.angka = angka;
        this.unknownKe = unknownKe;
    }

    /**
     * Constructor dengan tipe dan angka
     * @param type tipe
     * @param angka angka
     */
    public Token(int type, double angka){
        this.type = type;
        this.angka = angka;
    }
}
