package org.example.algorithm;
import java.io.IOException;
public interface BasicArithmeticCodingEncoderInterface {
    void encode(String inputFilePath, String outputFilePath) throws IOException ;
    String decompress(String filePath) throws IOException;
}
