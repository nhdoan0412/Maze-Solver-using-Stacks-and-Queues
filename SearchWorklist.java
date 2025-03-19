/*
 * Class to implement SearchWorklist as a Stack and a Queue.
 * You can use any built-in Java collections for this class.
 */

 import java.util.ArrayList;

 class StackWorklist implements SearchWorklist {
	 ArrayList<Square> stack;
 
	 public StackWorklist() {
		 this.stack = new ArrayList<>();
	 }
	 
	 public void add(Square c) {
		 stack.add(c);
	 }
 
	 public Square remove() {
		 if (!isEmpty()) {
			 return stack.remove(stack.size() - 1);
		 }
		 return null;
	 }
 
	 public boolean isEmpty() {
		 return stack.isEmpty();
	 }
 }
 
 class QueueWorklist implements SearchWorklist {
	 ArrayList<Square> queue;
 
	 public QueueWorklist() {
		 this.queue = new ArrayList<>();
	 }
 
	 public void add(Square c) {
		 queue.add(c);
	 }
 
	 public Square remove() {
		 if (!isEmpty()) {
			 return queue.remove(0);
		 }
		 return null;
	 }
 
	 public boolean isEmpty() {
		 return queue.isEmpty();
	 }
 }
 
 public interface SearchWorklist {
	 void add(Square c);
	 Square remove();
	 boolean isEmpty();
 }