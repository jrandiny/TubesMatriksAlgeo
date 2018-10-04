public class Pecahan {
    private int pembilang;
    private int penyebut;

    public Pecahan(){
        pembilang = 0;
        penyebut = 0;
    }

    public Pecahan(double input){
        setDouble(input);
    }

    public double getDouble(){
        return ((double)pembilang) / ((double) penyebut);
    }

    public int getPembilang(){
        return pembilang;
    }

    public int getPenyebut(){
        return penyebut;
    }

    public void setPembilang(int pembilang){
        this.pembilang = pembilang;
    }

    public void setPenyebut(int penyebut){
        this.penyebut = penyebut;
    }

    public static Pecahan kali(Pecahan p1,Pecahan p2){
        Pecahan pout = new Pecahan();

        pout.setPembilang(p1.getPembilang() * p2.getPembilang());
        pout.setPenyebut(p1.getPenyebut() * p2.getPenyebut());

        return pout;
    }

    public static Pecahan tambah(Pecahan p1,Pecahan p2){
        Pecahan pout = new Pecahan();

        pout.setPembilang((p1.getPembilang()*p2.getPenyebut() )+ (p2.getPembilang() * p1.getPenyebut()));
        pout.setPenyebut(p1.getPenyebut() * p2.getPenyebut());

        return pout;
    }

    public static Pecahan kurang(Pecahan p1,Pecahan p2){
        Pecahan pout = new Pecahan();

        pout.setPembilang((p1.getPembilang()*p2.getPenyebut() )- (p2.getPembilang() * p1.getPenyebut()));
        pout.setPenyebut(p1.getPenyebut() * p2.getPenyebut());

        return pout;
    }


    public void setDouble(double input){
        String s = String.valueOf(input);
        int digitsDec = s.length() - 1 - s.indexOf('.');

        penyebut = 1;
        for(int i = 0; i < digitsDec; i++){
            input *= 10;
            penyebut *= 10;
        }
        pembilang = (int) Math.round(input);
    }

}
