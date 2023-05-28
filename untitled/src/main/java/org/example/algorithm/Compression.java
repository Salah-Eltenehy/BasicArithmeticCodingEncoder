package org.example.algorithm;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.example.ProbabilityRange;
import org.example.operations.*;

public class Compression  implements BasicArithmeticCodingEncoderInterface {
    private OperationsInterface operationsInterface;
    private static final int BUFFER_SIZE = 24;
    public Compression() {
        operationsInterface = new Operations();
    }
    @Override
    public void encode(String inputFilePath, String outputFilePath) throws IOException {
        byte[] fileBytes = operationsInterface.readFileAsBytes(inputFilePath);
        ByteBuffer byteBuffer = ByteBuffer.wrap(fileBytes);
        Map<Character, Integer> charFrequencies = operationsInterface.calculateCharFrequencies(fileBytes);
        Map<Character, ProbabilityRange> probabilities = operationsInterface.generateProbabilities(charFrequencies);
        while (byteBuffer.hasRemaining()) {
            int chunkSize = Math.min(byteBuffer.remaining(), BUFFER_SIZE);
            byte[] chunkBytes = new byte[chunkSize];
            byteBuffer.get(chunkBytes);
            String chunkMessage = new String(chunkBytes, StandardCharsets.UTF_8);
            double encodedValue = BasicArithmeticCodingEncoder.encode(chunkMessage, probabilities);
            operationsInterface.writeOutputFile(outputFilePath, charFrequencies, probabilities, encodedValue);
        }
        // String message = new String(fileBytes);
        // double encodedValue = BasicArithmeticCodingEncoder.encode(message, probabilities);
        // operationsInterface.writeOutputFile(outputFilePath, charFrequencies, probabilities, encodedValue);
    }

    @Override
    public String decompress(String filePath) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'decompress'");
    }
    
}
