package edu.bsu.cs222;

import edu.bsu.cs222.Exceptions.NoInputException;

import java.util.Scanner;

public class GetUserInput {

    public String getUserInput() throws NoInputException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhat article would you like to look up on Wikipedia?");
        String userInput = scanner.nextLine();
        if(userInput.trim().equals("")) {
            throw new NoInputException();
        }
        return userInput;

    }

}