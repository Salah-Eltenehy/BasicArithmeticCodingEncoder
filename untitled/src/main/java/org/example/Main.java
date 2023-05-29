package org.example;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String inputFilePath = "input.txt";
        String outputFilePath = "output.txt";

        BasicArithmeticCodingEncoderInterface compression = new Compression();

        compression.encode(inputFilePath, outputFilePath);
        
        BasicArithmeticCodingEncoderInterface deCompression = new Decompression();
        System.out.println(deCompression.decompress(outputFilePath));
    }
}
