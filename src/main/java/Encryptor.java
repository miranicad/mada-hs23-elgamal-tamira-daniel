import java.math.BigInteger;

public class Encryptor {
    public static void main(String[] args) {
        //Daten einlesen und vorbereiten
        String text = FileHandler.readTextFile("text.txt");
        BigInteger n = FileHandler.getN();
        BigInteger g = FileHandler.getG();
        BigInteger pk = FileHandler.readKey("pk.txt");

        //text in Acsii Array speichern
        int[] textInAscii = FileHandler.toAsciiCode(text);
        System.out.println(textInAscii);
    }
}
