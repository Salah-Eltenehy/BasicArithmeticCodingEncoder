package org.example;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Compression implements BasicArithmeticCodingEncoderInterface {
    private static int buffer_size = 20;
    private OperationsInterface operationsInterface;
    public Compression() {
        operationsInterface = new Operations();
    }
    @Override
    public void encode(String inputFilePath, String outputFilePath) throws IOException {
        byte[] fileBytes = operationsInterface.readFileAsBytes(inputFilePath);
        Map<Character, Integer> charFrequencies = operationsInterface.calculateCharFrequencies(fileBytes);
        Map<Character, ProbabilityRange> probabilities = operationsInterface.generateProbabilities(charFrequencies);
        ByteBuffer byteBuffer = ByteBuffer.wrap(fileBytes);
        operationsInterface.writeOutputFileHeader(outputFilePath, charFrequencies, probabilities);
        int amountRead = 0;
        while (byteBuffer.hasRemaining()) {
            int s = Math.min(buffer_size, byteBuffer.capacity() - amountRead);
            amountRead += s;
            byte[] msg = new byte[s];
            byteBuffer.get(msg);
            String message = new String(msg, StandardCharsets.UTF_8);
            
            double encodedValue = BasicArithmeticCodingEncoder.encode(message, probabilities);    
            operationsInterface.writeOutputFile(outputFilePath, encodedValue);
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


















