package org.example;
import java.io.IOException;
import java.util.*;
public interface OperationsInterface {
    byte[] readFileAsBytes(String filePath)  throws IOException;
    Map<Character, Integer> calculateCharFrequencies(byte[] fileBytes);
    Map<Character, ProbabilityRange> generateProbabilities(Map<Character, Integer> charFrequencies);
    void writeOutputFile(String filePath, double encodedValue) throws IOException;
    void writeOutputFileHeader(String filePath, Map<Character, Integer> charFrequencies, Map<Character, ProbabilityRange> probabilities) throws IOException;
}