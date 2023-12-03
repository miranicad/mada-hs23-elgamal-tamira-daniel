import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Decryptor {
    public static void main(String[] args) {
        //Daten einlesen und vorbereiten
        BigInteger n = FileHandler.getN();
        BigInteger g = FileHandler.getG();
        BigInteger sk = FileHandler.readKey("sk.txt");

        // Verschlüsselte Werte aus "chiffre.txt" lesen
        ElGamalCipherText[] encryptedValues = readEncryptedFromFile("src/main/resources/chiffre.txt");


        // Loop zur ElGamal-Entschlüsselung der verschlüsselten Werte
        BigInteger[] decryptedAscii = new BigInteger[encryptedValues.length];
        for (int i = 0; i < encryptedValues.length; i++) {
            decryptedAscii[i] = decryptElGamal(encryptedValues[i], sk, n);
        }


        // Die verschlüsselten Werte in die Datei schreiben
        writeDecryptedToFile("target/text-d.txt", decryptedAscii);


    }

    private static void writeDecryptedToFile(String fileName, BigInteger[] decryptedValues) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (BigInteger decryptedValue : decryptedValues) {
                // Convert the decrypted value to ASCII character and write to the file
                fileWriter.write((char) decryptedValue.intValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ElGamalCipherText[] readEncryptedFromFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            String[] pairs = line.split(";");

            ElGamalCipherText[] encryptedValues = new ElGamalCipherText[pairs.length];
            for (int i = 0; i < pairs.length; i++) {
                // Parse the values from the format (y1, y2);
                String[] parts = pairs[i].replaceAll("\\(", "").replaceAll("\\)", "").trim().split(",");
                BigInteger a = new BigInteger(parts[0]);
                BigInteger b = new BigInteger(parts[1]);
                encryptedValues[i] = new ElGamalCipherText(a, b);
            }

            return encryptedValues;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    private static BigInteger decryptElGamal(ElGamalCipherText cipherText, BigInteger privateKey, BigInteger p) {
        BigInteger a = cipherText.getA();
        BigInteger b = cipherText.getB();
        BigInteger aInverse = a.modPow(privateKey.negate(), p);
        return b.multiply(aInverse).mod(p);
    }
}
