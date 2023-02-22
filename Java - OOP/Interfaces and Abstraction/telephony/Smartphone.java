package Exercises_04.telephony;

import java.util.List;

public class Smartphone implements Browsable, Callable {

    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String call() {

        StringBuilder sb = new StringBuilder();
        for (String currentNumber : this.numbers) {
            boolean isValid = true;
            char[] phoneCharacters = currentNumber.toCharArray();
            for (char phoneCharacter : phoneCharacters) {
                if (!Character.isDigit(phoneCharacter)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                sb.append("Calling... ").append(currentNumber);
                sb.append(System.lineSeparator());
            } else {
                sb.append("Invalid number!");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    @Override
    public String browse() {

        StringBuilder sb = new StringBuilder();
        for (String currentBrowsers : this.urls) {
            boolean isValid = true;
            char[] browserCharacters = currentBrowsers.toCharArray();
            for (char browserCharacter : browserCharacters) {
                if (Character.isDigit(browserCharacter)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                sb.append("Browsing: ").append(currentBrowsers).append("!");
                sb.append(System.lineSeparator());
            } else {
                sb.append("Invalid URL!");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
