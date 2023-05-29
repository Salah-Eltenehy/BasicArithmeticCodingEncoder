package org.example.operations;

import java.util.Map;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.example.ProbabilityRange;

public class Operations implements OperationsInterface {

    @Override
    public byte[] readFileAsBytes(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }

    @Override
    public Map<Character, Integer> calculateCharFrequencies(byte[] fileBytes) {
        Map<Character, Integer> charFrequencies = new HashMap<>();
        for (byte b : fileBytes) {
            char c = (char) b;
            charFrequencies.put(c, charFrequencies.getOrDefault(c, 0) + 1);
        }
        return charFrequencies;
    }

    @Override
    public Map<Character, ProbabilityRange> generateProbabilities(Map<Character, Integer> charFrequencies) {
        Map<Character, ProbabilityRange> probabilities = new HashMap<>();
        double low = 0.0;
        int totalChars = charFrequencies.values().stream().mapToInt(Integer::intValue).sum();

        for (Map.Entry<Character, Integer> entry : charFrequencies.entrySet()) {
            double probability = (double) entry.getValue() / totalChars;
            probabilities.put(entry.getKey(), new ProbabilityRange(low, low + probability));
            low += probability;
        }

        return probabilities;
    }
    
    @Override
    public void writeOutputFile(String filePath, double encodedValue) throws IOException {
                try (FileWriter writer = new FileWriter(filePath, true)) {
                    writer.write("\nD:" + encodedValue);
                }
    }

    @Override
    public void writeOutputFileHeader(String filePath, Map<Character, Integer> charFrequencies,
            Map<Character, ProbabilityRange> probabilities) throws IOException {
                try (FileWriter writer = new FileWriter(filePath)) {
                    writer.write("F:\n");
                    for (Map.Entry<Character, Integer> entry : charFrequencies.entrySet()) {
                        writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
                    }
                    writer.write("\nP:\n");
                    for (Map.Entry<Character, ProbabilityRange> entry : probabilities.entrySet()) {
                        writer.write(entry.getKey() + ": " + entry.getValue().low + " - " + entry.getValue().high + "\n");
                    }

                }
    }
    
}