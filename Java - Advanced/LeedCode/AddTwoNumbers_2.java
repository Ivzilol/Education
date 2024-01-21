import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class AddTwoNumbers_2 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        AddTwoNumbers_2 cl = new AddTwoNumbers_2();
        ListNode first = cl.new ListNode(9);
        ListNode second = cl.new ListNode(1);
        second.next = cl.new ListNode(9);
        second.next.next = cl.new ListNode(9);
        second.next.next.next = cl.new ListNode(9);
        second.next.next.next.next = cl.new ListNode(9);
        second.next.next.next.next.next = cl.new ListNode(9);
        second.next.next.next.next.next.next= cl.new ListNode(9);
        second.next.next.next.next.next.next.next = cl.new ListNode(9);
        second.next.next.next.next.next.next.next.next = cl.new ListNode(9);
        second.next.next.next.next.next.next.next.next.next = cl.new ListNode(9);
        cl.addTwoNumbers(first, second);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> firstNumber = new ArrayList<>();
        List<Integer> secondNumber = new ArrayList<>();
        while (l1 != null) {
            firstNumber.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            secondNumber.add(l2.val);
            l2 = l2.next;
        }
        List<Integer> firstRevers = reverseList(firstNumber);
        List<Integer> secondRevers = reverseList(secondNumber);
        BigInteger sum = listToNumber(firstRevers).add(listToNumber(secondRevers));
        String sumStr = sum.toString();
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        for (int i = sumStr.length() - 1; i >= 0; i--) {
            int currentNumber = Character.getNumericValue(sumStr.charAt(i));
            current.next = new ListNode(currentNumber);
            current = current.next;
        }
        return dummyHead.next;
    }

    private BigInteger listToNumber(List<Integer> firstRevers) {
        BigInteger result = BigInteger.valueOf(0);
        for (int digit : firstRevers) {
            result = result.multiply(BigInteger.TEN).add(BigInteger.valueOf(digit));
        }
        return result;
    }

    private List<Integer> reverseList(List<Integer> firstNumber) {
        List<Integer> reverse = new ArrayList<>();
        for (int i = firstNumber.size() - 1; i >= 0 ; i--) {
            int number = firstNumber.get(i);
            reverse.add(number);
        }
        return reverse;
    }
}
