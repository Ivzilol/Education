public class LinkedListCycle_141 {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        LinkedListCycle_141 cl =  new LinkedListCycle_141();
        ListNode head = cl.new ListNode(3);
        head.next = cl.new ListNode(2);
        head.next.next = cl.new ListNode(0);
        head.next.next.next = cl.new ListNode(-4);
        head.next.next.next.next = head.next;
        cl.hasCycle(head);
    }

    public boolean hasCycle(ListNode head) {
        boolean hasCycle = false;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                hasCycle = true;
                break;
            }
        }
        return hasCycle;
    }
}
