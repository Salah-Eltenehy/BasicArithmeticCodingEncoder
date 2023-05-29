package org.example.algorithm;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.example.ProbabilityRange;
import org.example.operations.*;

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
        while (byteBuffer.hasRemaining()) {
            int s = Math.min(buffer_size, byteBuffer.capacity());
            byte[] msg = new byte[s];
            System.out.println(byteBuffer.capacity());
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


















