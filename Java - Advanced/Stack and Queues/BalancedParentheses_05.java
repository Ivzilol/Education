package Exercises_01;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BalancedParentheses_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        ArrayDeque<Character> openBracketsStack = new ArrayDeque<>();
        boolean areBalanced = false;
        for (int index = 0; index < input.length(); index++) {
            char currentBrackets = input.charAt(index);
            if (currentBrackets == '{' || currentBrackets == '(' || currentBrackets == '[') {
                openBracketsStack.push(currentBrackets);
            } else if (currentBrackets == '}' || currentBrackets == ')' || currentBrackets == ']') {
                if (openBracketsStack.isEmpty()){
                    areBalanced = false;
                    break;
                }
                char lastOpen = openBracketsStack.pop();
                if (currentBrackets == '}' && lastOpen == '{') {
                    areBalanced = true;
                } else if (currentBrackets == ']' && lastOpen == '[') {
                    areBalanced = true;
                } else if (currentBrackets == ')' && lastOpen == '(') {
                    areBalanced = true;
                } else {
                    areBalanced = false;
                    break;
                }
            }
        }
        if (areBalanced) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
