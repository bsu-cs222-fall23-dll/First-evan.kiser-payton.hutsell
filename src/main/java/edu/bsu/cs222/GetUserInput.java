package edu.bsu.cs222;

import java.util.Scanner;
public class GetUserInput {

    public String getUserInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What article would you like to look up on Wikipedia?");
        String userInput = scanner.nextLine();
        checkUserInput(userInput);
        return userInput;
    }
    public void checkUserInput(String userInput) {
        if(userInput.equals("")) {
            System.err.println("Error, Input is invalid!");
        }
    }
}