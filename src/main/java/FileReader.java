import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;

public class FileReader {

    static BigInteger readHexToBigInteger(String fileName) {
        StringBuilder hexString = new StringBuilder();
        String line;
        try (
            BufferedReader reader = new BufferedReader(new java.io.FileReader("src/main/resources/" + fileName))) {

            while ((line = reader.readLine()) != null) {
                // Remove any spaces or newline characters from the line
                line = line.replaceAll("\\s", "");
                hexString.append(line);
            }

        } catch (IOException ex) {
            throw new RuntimeException("couldn't find " + fileName + " in /resources folder or file not in valid format");
        }

        BigInteger number = BigInteger.ZERO;

        return new BigInteger(String.valueOf(hexString),16);
    }
}
