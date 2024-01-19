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
        ListNode first = cl.new ListNode(2);
        first.next = cl.new ListNode(4);
        first.next.next = cl.new ListNode(3);
        ListNode second = cl.new ListNode(5);
        second.next = cl.new ListNode(6);
        second.next.next = cl.new ListNode(4);
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
        int sum = listToNumber(firstRevers) + listToNumber(secondRevers);
        String sumStr = Integer.toString(sum);
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        for (int i = sumStr.length() - 1; i >= 0; i--) {
            int currentNumber = Character.getNumericValue(sumStr.charAt(i));
            current.next = new ListNode(currentNumber);
            current = current.next;
        }
//        while (dummyHead != null) {
//            System.out.println(dummyHead.val + " ");
//            dummyHead = dummyHead.next;
//        }
        return dummyHead.next;
    }

    private int listToNumber(List<Integer> firstRevers) {
        int result = 0;
        for (int digit : firstRevers) {
            result = result * 10 + digit;
        }
        return result;
    }

    private List<Integer> reverseList(List<Integer> firstNumber) {
        List<Integer> reverse = new ArrayList<>();
        for (int i = firstNumber.size() - 1; i >= 0 ; i--) {
            reverse.add(firstNumber.get(i));
        }
        return reverse;
    }
}
