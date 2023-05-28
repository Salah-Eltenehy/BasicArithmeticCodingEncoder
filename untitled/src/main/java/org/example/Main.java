package org.example;

import java.io.IOException;
import java.util.*;

import org.example.algorithm.*;
public class Main {

    public static void main(String[] args) throws IOException {
        String inputFilePath = "F:/Codes/Java/test/untitled/src/main/java/org/example/input.txt";
        String outputFilePath = "F:/Codes/Java/test/untitled/src/main/java/org/example/output.txt";
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
/*
 F:
A: 132
B: 72
C: 60

P:
A: 0.0 - 0.5
B: 0.5 - 0.7727272727272727
C: 0.7727272727272727 - 1.0

D:0.5496395899044969
 */
