
public class ArrayDeque<T> {

    private int size;
    private static final int DEFAULT_CAPACITY = 8;
    private T[] elementData;
    private int length;

    private int nextFirst;
    private int nextLast;

    private ArrayDeque(){
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        length = DEFAULT_CAPACITY;
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    private int minusOne(int index){
        if(index == 0){
            return length-1;
        }
        return index - 1;
    }
    private int plusOne(int index, int length){
        index %= length;
        if(index == length-1){
            return 0;
        }
        return index + 1;
    }

    private void resizeToL(){
        T[] newData = (T[]) new Object[length*2];
        int ptr1 = plusOne(nextFirst, length);
        int ptr2 = 3;
        while(ptr1 != nextLast){
            newData[ptr2] = elementData[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 ++;
        }
        nextFirst = 2;
        nextLast = ptr2;
        elementData = newData;
        length *= 2;
    }
    private void resizeToS(){
        T[] newDate = (T[]) new Object[length / 2];
        int ptr1 = plusOne(nextFirst, length);
        int ptr2 = 3;
        while(ptr1 != nextLast){
            newDate[ptr2] = elementData[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length);
        }
        nextFirst = 2;
        nextLast = ptr2;
        elementData = newDate;
        length /= 2;
    }

    public void addFirst(T item){
        if(size == length-2){
            resizeToL();
        }
        elementData[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size ++;
    }
    public void addLast(T item){
        if(size == length-2){
            resizeToL();
        }
        elementData[nextLast] = item;
        nextLast = plusOne(nextLast, length);
        size ++;
    }

    public T removeFirst(){
        if(length > 16 && length / size >= 4){
            resizeToS();
        }
        nextFirst = plusOne(nextFirst, length);
        size --;
        return elementData[nextFirst];
    }
    public T removeLast(){
        if(length > 16 && length / size >= 4){
            resizeToS();
        }
        nextLast= minusOne(nextLast);
        size --;
        return elementData[nextLast];
    }

    public T get(int index){
        if(index < 0 || index >= size){
            return null;
        }
        index = (index+1+nextFirst)%length;
        return elementData[index];

    }

    public void printDeque(){
        int ptr = plusOne(nextFirst, length);
        while(ptr != nextLast){
            System.out.print(elementData[ptr] + " ");
            ptr=plusOne(ptr, length);
        }
    }

}
