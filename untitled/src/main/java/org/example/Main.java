package org.example;

import java.io.IOException;
import java.util.*;

import org.example.algorithm.*;
public class Main {

    public static void main(String[] args) throws IOException {
        String inputFilePath = "input.txt";
        String outputFilePath = "output.txt";

        BasicArithmeticCodingEncoderInterface compression = new Compression();

        compression.encode(inputFilePath, outputFilePath);
        
        BasicArithmeticCodingEncoderInterface deCompression = new Decompression();
        
        System.out.println(deCompression.decompress(outputFilePath));


        // String message = "ABCABAAC";
        // Map<Character, ProbabilityRange> probabilities = new HashMap<>(); 
        // probabilities.put('A', new ProbabilityRange(0.0, 0.4));
        // probabilities.put('B', new ProbabilityRange(0.4, 0.6));
        // probabilities.put('C', new ProbabilityRange(0.6, 1.0));
        
        // double encodedValue = BasicArithmeticCodingEncoder.encode(message, probabilities);
        // System.out.println(encodedValue);


        // String decodedMessage = BasicArithmeticCodingEncoder.decode(encodedValue, message.length(), probabilities);

        // System.out.println(decodedMessage);
    }
}
