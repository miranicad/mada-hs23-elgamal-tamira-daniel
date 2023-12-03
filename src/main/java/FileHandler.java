import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
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
        return new BigInteger(String.valueOf(hexString),16);
    }




    public static void writeKey(BigInteger key, String fileName) throws IOException {
        try (BufferedWriter keyWriter = new BufferedWriter(new FileWriter("target/" + fileName+".txt"))) {
            keyWriter.write(String.valueOf(key));
        } catch (IOException ex) {
            throw new IOException("Write to " + fileName + "failed.", ex);

        }
    }

    static String readTextFile(String fileName) {
        String input;
        try (
            BufferedReader textReader = new BufferedReader(new FileReader("src/main/resources/" + fileName))) {
            input = textReader.readLine();
        } catch (IOException ex) {
            throw new RuntimeException("couldn't find " + fileName + " in /resources folder or file not in valid format");
        }
        if (!isAscii(input)) {
            throw new RuntimeException(fileName + " contains non-ASCII characters");
        }

        return input;
    }

    public static boolean isAscii(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) > 127) {
                return false;
            }
        }
        return true;
    }

    public static BigInteger readKey(String fileName) {
        String key = readTextFile(fileName);
        return new BigInteger(key);
    }

    static int[] toAsciiCode(String text) {
        int[] asciiCodes = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            asciiCodes[i] = (int) text.charAt(i);
        }
        return asciiCodes;
    }

    static ElGamalCipherText[] readEncryptedFromFile(String fileName) {
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

    static void writeDecryptedToFile(String fileName, BigInteger[] decryptedValues) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (BigInteger decryptedValue : decryptedValues) {
                // Convert the decrypted value to ASCII character and write to the file
                fileWriter.write((char) decryptedValue.intValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void writeEncryptedToFile(String fileName, ElGamalCipherText[] encryptedValues) {
        try (FileWriter fileWriter = new FileWriter("target/"+fileName)) {
            for (ElGamalCipherText encryptedValue : encryptedValues) {
                // Schreibe die verschlüsselten Werte in der Form (y1, y2);
                fileWriter.write("(" + encryptedValue.getA() + "," + encryptedValue.getB() + ");");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static BigInteger getN() {
        return readHexToBigInteger("hex.txt");
    }

    public static BigInteger getG() {
        return BigInteger.valueOf(2);
    }
}
