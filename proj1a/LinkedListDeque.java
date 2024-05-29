public class LinkedListDeque <T>{
    private int size;

    private class Node{
        private T item;
        private Node next;
        private Node prev;

        public Node(T item, Node next, Node prev){
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        public Node(Node next, Node prev){
            this.next = next;
            this.prev = prev;
        }
    }

    private Node sentinel;

    public LinkedListDeque(){
        sentinel = new Node(null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    //add an item of type T to the front of the deque
    //no involve looping or recursion, a single operation takes constant time
    public void addFirst(T item){
        Node newNode = new Node(item, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;//must change the prev info of the first node
        size += 1;//otherwise, would lose the info if change the next node of sentinel firstly
    }

    //add an item of type T to the back of the deque
    //no involve looping or recursion, a single operation takes constant time
    public void addLast(T item){
        Node newNode = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    //returns true if deque is empty, false otherwise
    public boolean isEmpty(){
        return size == 0;
    }

    //returns the number of items in the deque
    public int size(){
        return size;
    }

    //prints the items in the deque from first to last
    //separated by a space
    public void printDeque(){
        Node current = sentinel.next;
        while(current != sentinel){
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

    //removes and returns the item at the front of the deque,
    //if no such item exists, returns null
    //no involve looping or recursion, a single operation takes constant time
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        Node first = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return first.item;
    }

    //removes and returns the item at the back of the deque,
    //if no such item exists, returns null
    //no involve looping or recursion, a single operation takes constant time
    public T removeLast(){
        if(size == 0){
            return null;
        }
        Node last = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return last.item;
    }

    //get the item at the given index, if no such
    //items exists, returns null. no alter the deque
    public T get(int index){
        if(index < 0 || index >= size){
            return null;
        }else{
            Node current = sentinel.next;
            for(int i=0; i < index; i++){
                current =  current.next;
            }
            return current.item;
        }
    }

    public T getRecursive(int index){
        if(index < 0 || index >= size){
            return null;
        }else{
            return getRecursiveHelper(sentinel.next, index);
        }

    }

    private T getRecursiveHelper(Node N, int index){
        if(index == 0){
            return N.item;
        }else{
            return getRecursiveHelper(N.next, index-1);
        }
    }
}
