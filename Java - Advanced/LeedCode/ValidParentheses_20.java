import java.util.ArrayDeque;

public class ValidParentheses_20 {
    public static void main(String[] args) {
        ValidParentheses_20 cl = new ValidParentheses_20();
        cl.isValid("()");
        cl.isValid("()[]{}");
        cl.isValid("(]");
        cl.isValid("([]){");
    }
    public boolean isValid(String s) {
        boolean areBalanced = false;
        ArrayDeque<Character> openBracketsStack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char currentBrackets = s.charAt(i);
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
        if (!openBracketsStack.isEmpty()) {
            areBalanced = false;
        }
        System.out.println(areBalanced);
        return areBalanced;
    }
}
