package org.example.algorithm;

import java.io.IOException;
import java.util.Map;

import org.example.ProbabilityRange;
import org.example.operations.*;

public class Compression implements BasicArithmeticCodingEncoderInterface {
    private OperationsInterface operationsInterface;
    public Compression() {
        operationsInterface = new Operations();
    }
    @Override
    public void encode(String inputFilePath, String outputFilePath) throws IOException {
        byte[] fileBytes = operationsInterface.readFileAsBytes(inputFilePath);
        Map<Character, Integer> charFrequencies = operationsInterface.calculateCharFrequencies(fileBytes);
        Map<Character, ProbabilityRange> probabilities = operationsInterface.generateProbabilities(charFrequencies);
        String message = new String(fileBytes);
        double encodedValue = BasicArithmeticCodingEncoder.encode(message, probabilities);
        operationsInterface.writeOutputFile(outputFilePath, charFrequencies, probabilities, encodedValue);
    }

    @Override
    public String decompress(String filePath) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'decompress'");
    }
    
}
