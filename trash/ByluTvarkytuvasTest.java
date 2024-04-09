package trash;
public class ByluTvarkytuvasTest {

    public static void main(String args[]) {
        String testDirektorija= "C:\\Users\\kroda\\OneDrive\\Java\\porj"; 
        testDirektorija= "C:\\Users\\HP\\OneDrive\\Java\\porj";
        ByluTvarkytuvas tvarkytuvas = new ByluTvarkytuvas(testDirektorija);
        tvarkytuvas.println("israsytiBylas");
        
        String direktorija = "direktorija";
        String direktorija2 = "direktorija2";
        tvarkytuvas.sukurtiByla(direktorija);
        //tvarkytuvas.israsytiBylas();
        tvarkytuvas.println("israsytiBylas"); //tvarkytuvas.println();
        tvarkytuvas.sukurtiByla(direktorija2);
        //tvarkytuvas.israsytiBylas();
        tvarkytuvas.println("israsytiBylas");
        tvarkytuvas.istrintiByla(direktorija);
        tvarkytuvas.istrintiByla(direktorija2);
        //tvarkytuvas.israsytiBylas();
        tvarkytuvas.println("israsytiBylas");//tvarkytuvas.println();
        System.out.println(ByluTvarkytuvas.getObjektuKiekis());

        //new File(testDirektorija).delete();
    }
    
}