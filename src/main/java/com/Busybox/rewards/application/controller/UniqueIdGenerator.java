package com.Busybox.rewards.application.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class UniqueIdGenerator {
	
	private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";

    public static List<String> generateUniqueIds() {
        List<String> uniqueIds = new ArrayList<>();
        List<String> allPossibleIds = generateAllPossibleIds();

        // Shuffle the list of all possible IDs to make the order random
        Collections.shuffle(allPossibleIds);

        uniqueIds.addAll(allPossibleIds);
        return uniqueIds;
    }

    private static List<String> generateAllPossibleIds() {
        List<String> allPossibleIds = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int x = 0; x < 10; x++) {
                        for (int y = 0; y < 10; y++) {
                            for (int z = 0; z < 10; z++) {
                                StringBuilder uniqueId = new StringBuilder();
                                uniqueId.append(LETTERS.charAt(i));
                                uniqueId.append(LETTERS.charAt(j));
                                uniqueId.append(LETTERS.charAt(k));
                                uniqueId.append(DIGITS.charAt(x));
                                uniqueId.append(DIGITS.charAt(y));
                                uniqueId.append(DIGITS.charAt(z));
                                allPossibleIds.add(uniqueId.toString());
                            }
                        }
                    }
                }
            }
        }

        return allPossibleIds;
    }
}
