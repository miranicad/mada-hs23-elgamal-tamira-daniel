import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

public class Elgamal {

    public static void main(String[] args) throws IOException {
        //n aus hex datei einlesen
        BigInteger n = FileHandler.readHexToBigInteger("hex.txt");
        System.out.println(n);

        //g definieren
        BigInteger g = BigInteger.valueOf(2);

        //Privater Schlüssel erzeugen
        SecureRandom random = new SecureRandom();
        BigInteger sk = new BigInteger(n.bitLength()-2,random).add(BigInteger.valueOf(2));

        //Öffentlicher Schlüssel erzeugen
        BigInteger pk = g.modPow(sk, n);

        //output der Schlüssel zu Ordner target
        FileHandler.writeKey(sk,"sk");
        FileHandler.writeKey(pk,"pk");
    }
}
