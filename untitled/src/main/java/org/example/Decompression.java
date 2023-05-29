package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.*;

public class Decompression  implements BasicArithmeticCodingEncoderInterface{

    

    @Override
    public String decompress(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        Map<Character, ProbabilityRange> probabilities = new HashMap<>();
        double encodedValue = 0.0;
        int messageLength = 0;

        boolean readingProbabilities = false;
        boolean dest = true;
        String res = "";
        for (String line : lines) {
            if (line.startsWith("F:")) {
                continue;
            } else if (line.startsWith("P:")) {
                readingProbabilities = true;
                continue;
            } else if (line.startsWith("D:")) {
                readingProbabilities = false;
                dest = false;
                encodedValue = Double.parseDouble(line.substring("D:\n".length()).trim());
                res += BasicArithmeticCodingEncoder.decode(encodedValue, messageLength, probabilities);
                //break;
            }
            if (dest){    
                String[] parts = line.split(":");
                if (parts.length < 2) {
                    continue;
                }

                if (!readingProbabilities) {
                    int frequency = Integer.parseInt(parts[1].trim());
                    messageLength += frequency;
                } else {
                    char c = parts[0].charAt(0);
                    String[] rangeParts = parts[1].split("-");
                    double low = Double.parseDouble(rangeParts[0].trim());
                    double high = Double.parseDouble(rangeParts[1].trim());
                    probabilities.put(c, new ProbabilityRange(low, high));
                }
            }
        }

        return res;
    }

    @Override
    public void encode(String inputFilePath, String outputFilePath) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'encode'");
    }
    
}
