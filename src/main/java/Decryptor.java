import java.math.BigInteger;

public class Decryptor {
    public static void main(String[] args) {
        //Daten einlesen und vorbereiten
        BigInteger n = FileHandler.getN();
        BigInteger g = FileHandler.getG();
        BigInteger sk = FileHandler.readKey("sk.txt");

        // Verschlüsselte Werte aus "chiffre.txt" einlesen
        ElGamalCipherText[] encryptedValues = FileHandler.readEncryptedFromFile("src/main/resources/chiffre.txt");

        // Loop zur ElGamal-Entschlüsselung der verschlüsselten Werte
        BigInteger[] decryptedAscii = new BigInteger[encryptedValues.length];
        for (int i = 0; i < encryptedValues.length; i++) {
            decryptedAscii[i] = decryptElGamal(encryptedValues[i], sk, n);
        }

        // Die verschlüsselten Werte "text-d.txt ausgeben"
        FileHandler.writeDecryptedToFile("target/text-d.txt", decryptedAscii);
    }

    private static BigInteger decryptElGamal(ElGamalCipherText cipherText, BigInteger privateKey, BigInteger p) {
        BigInteger a = cipherText.getA();
        BigInteger b = cipherText.getB();
        BigInteger aInverse = a.modPow(privateKey.negate(), p);
        return b.multiply(aInverse).mod(p);
    }
}
