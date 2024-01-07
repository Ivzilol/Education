public class ValidPalindrome_125 {
    public static void main(String[] args) {
        ValidPalindrome_125 cl = new ValidPalindrome_125();
//        cl.isPalindrome("A man, a plan, a canal: Panama");
        cl.isPalindrome("0P");
    }


    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char currentSymbol = s.charAt(i);
            if (currentSymbol >= 'a' && currentSymbol <= 'z' || currentSymbol >= 'A' && currentSymbol <= 'Z'
                    || Character.isDigit(currentSymbol)) {
                char inLowercase = Character.toLowerCase(currentSymbol);
                sb.append(inLowercase);
            }

        }
        String newString = String.valueOf(sb);
        String reversString = String.valueOf(sb.reverse());
        return newString.equals(reversString);
    }
}
