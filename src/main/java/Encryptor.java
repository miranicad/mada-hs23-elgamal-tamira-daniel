import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

public class Encryptor {
    public static void main(String[] args) {
        //Daten einlesen und vorbereiten
        String text = FileHandler.readTextFile("text.txt");
        BigInteger n = FileHandler.getN();
        BigInteger g = FileHandler.getG();
        BigInteger pk = FileHandler.readKey("pk.txt");

        //text in ASCII Array speichern
        int[] textInAscii = FileHandler.toAsciiCode(text);
        System.out.println(textInAscii);

        //Loop zur Verschlüsselung der Ascii Werte
        ElGamalCipherText[] encryptedAscii = new ElGamalCipherText[text.length()];
        for (int i = 0; i < textInAscii.length; i++) {
            encryptedAscii[i] = encryptElGamal(BigInteger.valueOf(textInAscii[i]), g, n, pk);
        }

        // Die verschlüsselten Werte in die Datei schreiben
        writeEncryptedToFile("chiffre.txt", encryptedAscii);
    }

    private static ElGamalCipherText encryptElGamal(BigInteger message, BigInteger g, BigInteger p,
                                                    BigInteger publicKey) {
        SecureRandom random = new SecureRandom();
        BigInteger k = new BigInteger(p.bitLength() - 2, random).add(BigInteger.valueOf(2));

        BigInteger a = g.modPow(k, p);
        BigInteger b = publicKey.modPow(k, p).multiply(message).mod(p);

        return new ElGamalCipherText(a, b);
    }

    private static void writeEncryptedToFile(String fileName, ElGamalCipherText[] encryptedValues) {
        try (FileWriter fileWriter = new FileWriter("target/"+fileName)) {
            for (ElGamalCipherText encryptedValue : encryptedValues) {
                // Schreibe die verschlüsselten Werte in der Form (y1, y2);
                fileWriter.write("(" + encryptedValue.getA() + "," + encryptedValue.getB() + ");");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


