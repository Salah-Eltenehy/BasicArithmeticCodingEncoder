package org.example.algorithm;

import java.util.*;

import org.example.ProbabilityRange;
public class BasicArithmeticCodingEncoder {
    public static double encode(String message, Map<Character, ProbabilityRange> probabilities) {
        // Initialize the range variables
        double low = 0.0;
        double high = 1.0;

        // Encode each character in the message
        for (char c : message.toCharArray()) {
            // Determine the range for the current character
            ProbabilityRange range = probabilities.get(c);
            double symbol_low = range.low;
            double symbol_high = range.high;

            // Update the range variables
            double range_size = high - low;
            high = low + range_size * symbol_high;
            low = low + range_size * symbol_low;
        }

        // Output the final value in the range
        return (low + high) / 2.0;
    }
    public static String decode(double encodedValue, int messageLength, Map<Character, ProbabilityRange> probabilities) {
        StringBuilder decodedMessageBuilder = new StringBuilder();
        double low = 0.0;
        double high = 1.0;
    
        // Decode each character in the message
        for (int i = 0; i < messageLength; i++) {
            double rangeSize = high - low;
            double offset = encodedValue - low;
            char c = ' ';
    
            // Find the character whose probability range contains the current value
            ProbabilityRange chosenRange = null;
            for (Map.Entry<Character, ProbabilityRange> entry : probabilities.entrySet()) {
                ProbabilityRange range = entry.getValue();
                double symbol_low = range.low;
                double symbol_high = range.high;
    
                if (offset >= (symbol_low * rangeSize) && offset < (symbol_high * rangeSize)) {
                    c = entry.getKey();
                    chosenRange = range;
                    break;
                }
            }
    
            // Update the range variables and append the decoded character
            if (chosenRange != null) {
                double symbol_low = chosenRange.low;
                double symbol_high = chosenRange.high;
                high = low + rangeSize * symbol_high;
                low = low + rangeSize * symbol_low;
            }
            decodedMessageBuilder.append(c);
        }
    
        return decodedMessageBuilder.toString();
    }
    
    
}
