import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ByluTvarkytuvas {

    private static int objektuKiekis = 0;
    private final int id;
    private String bazineDirektorija;
    private int kataloguKiekis;
    private List<String> katalogai; // funckionalumas dar neimplementuotas.

    public void println() {
        System.out.println(this.bazineDirektorija);
        System.out.println(this.kataloguKiekis);
        System.out.println(this.katalogai);
        israsytiBylas();
    }

    public void println(String laukas) {
        if(laukas == "bazineDirektorija")
            System.out.println(this.bazineDirektorija);
        if(laukas == "kataloguKiekis")
            System.out.println(this.kataloguKiekis);
        if(laukas == "katalogai")
            System.out.println(this.katalogai);
        if(laukas == "israsytiBylas")
            israsytiBylas();
    }

    public void sukurtiByla(String bylosPavadinimas) {
        File byla = new File(bazineDirektorija, bylosPavadinimas);
        if (!byla.exists()) {
            boolean sukurta = byla.mkdirs();
            if (sukurta) {
                System.out.println("Sukurta: " + bylosPavadinimas);
                this.kataloguKiekis++;
            } else {
                System.out.println("Nepavyko sukurti: " + bylosPavadinimas);
            }
        } else {
            System.out.println("Byla jau sukurta: " + bylosPavadinimas);
        }
    }

    public void istrintiByla(String bylosPavadinimas) {
        File byla = new File(bazineDirektorija, bylosPavadinimas);
        if (byla.exists()) {
            boolean istrinta = trinimas(byla);
            if (istrinta) {
                System.out.println("Byla istrinta: " + bylosPavadinimas);
                this.kataloguKiekis--;
            } else {
                System.out.println("Nevapyko istrinti: " + bylosPavadinimas);
            }
        } else {
            System.out.println("Byla neegzistuoja: " + bylosPavadinimas);
        }
    }

    private boolean trinimas(File failas) {
        File turinys[] = failas.listFiles();
        if (turinys != null) {
            for (File f : turinys) {
                trinimas(f);
            }
        }
        return failas.delete();
    }

    public void israsytiBylas() {
        File bazineDir = new File(bazineDirektorija);
        String bylos[] = bazineDir.list();  
        if (bylos != null && bylos.length > 0) {
            System.out.println("Failai direktorijoje" + bazineDirektorija + ":");
            for (String byla : bylos) {
                System.out.println(byla);
            }
        } else {
            System.out.println("Nerasta failu" + bazineDirektorija);
        }
    }

    public void vaizduotiKataloguStruktura() {
    }

    ByluTvarkytuvas() {
        this.id = ByluTvarkytuvas.objektuKiekis;
        ByluTvarkytuvas.objektuKiekis++;
        this.kataloguKiekis = 0;
        this.bazineDirektorija = "C:";
        this.katalogai = new ArrayList<>();
    }

    ByluTvarkytuvas(String direkorija) {
        this.id = ByluTvarkytuvas.objektuKiekis;
        ByluTvarkytuvas.objektuKiekis++;
        this.katalogai = new ArrayList<>();
        this.kataloguKiekis = 0;
        this.bazineDirektorija = direkorija;
    }

    public String getBazineDirektorija() {
        return bazineDirektorija;
    }

    public void setBazineDirektorija(String bazineDirektorija) {
        this.bazineDirektorija = bazineDirektorija;
    }

    public int getKataloguKiekis() {
        return kataloguKiekis;
    }

    public void setKataloguKiekis(int kataloguKiekis) {
        this.kataloguKiekis = kataloguKiekis;
    }

    public List<String> getKatalogai() {
        return katalogai;
    }

    public void setKatalogai(List<String> katalogai) {
        this.katalogai = katalogai;
    }

    public int getId() {
        return id;
    }

    public static int getObjektuKiekis()
    {
        return objektuKiekis;
    }


}



//(current, name) -> new File(current, name).isDirectory()