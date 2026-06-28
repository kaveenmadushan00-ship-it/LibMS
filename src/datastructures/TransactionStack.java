// datastructures/TransactionStack.java
package datastructures;

import java.util.Stack;

public class TransactionStack {
    private Stack<String> stack = new Stack<>();

    public void push(String record) { stack.push(record); }

    public String pop() { return stack.isEmpty() ? null : stack.pop(); }

    public void displayAll() {
        if (stack.isEmpty()) { System.out.println("No transactions yet."); return; }
        System.out.println("\n── Transaction History (most recent first) ──");
        // Iterate in reverse order so newest shows first
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println((stack.size() - i) + ". " + stack.get(i));
        }
    }
}