import java.math.BigInteger;

public class Elgamal {

    public static void main(String[] args) {
        BigInteger hexNumber = FileReader.readHexToBigInteger("hex.txt");
        System.out.println(hexNumber);
    }
}
