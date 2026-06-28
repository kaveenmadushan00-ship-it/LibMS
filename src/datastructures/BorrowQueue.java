// datastructures/BorrowQueue.java
package datastructures;

import java.util.LinkedList;
import java.util.Queue;

public class BorrowQueue {
    private Queue<String> queue = new LinkedList<>();

    public void enqueue(String entry) { queue.offer(entry); }

    public String dequeue() { return queue.poll(); }

    public void displayQueue() {
        if (queue.isEmpty()) { System.out.println("Waitlist is empty."); return; }
        System.out.println("\n── Borrow Waitlist (Queue) ──");
        int i = 1;
        for (String entry : queue) {
            System.out.println(i++ + ". " + entry);
        }
    }
}