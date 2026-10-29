package com.recipebook.util;

import java.util.Scanner;

public class ConsoleInput {
    private final Scanner scanner;

    public ConsoleInput(Scanner scanner) {
        if (scanner == null) throw new IllegalArgumentException("Scanner is required.");
        this.scanner = scanner;
    }

    public String readNonBlank(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine();
            if (InputValidator.isNonBlank(value)) return value.trim();
            System.out.println("Input cannot be blank. Please try again.");
        }
    }

    public String readPassword(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine();
            if (value.length() >= 4) return value;
            System.out.println("Password must contain at least 4 characters.");
        }
    }

    public int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            try {
                int number = Integer.parseInt(value);
                if (InputValidator.isMenuChoiceValid(number, min, max)) return number;
                System.out.printf("Enter a number between %d and %d.%n", min, max);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    public boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String answer = scanner.nextLine().trim().toLowerCase();
            if (answer.equals("y") || answer.equals("yes")) return true;
            if (answer.equals("n") || answer.equals("no")) return false;
            System.out.println("Please enter y or n.");
        }
    }
}
