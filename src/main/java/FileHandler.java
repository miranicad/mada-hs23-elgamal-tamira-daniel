import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class FileHandler {

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

    public static void writeKey(BigInteger key, String fileName) throws IOException {
        try (BufferedWriter keyWriter = new BufferedWriter(new FileWriter("target/" + fileName+".txt"))) {
            keyWriter.write(String.valueOf(key));
        } catch (IOException ex) {
            throw new IOException("Write to " + fileName + "failed.", ex);

        }
    }
}
