

public class Palindrome {


    public Deque<Character> wordToDeque(String word) {
        Deque<Character> newWord = new LinkedListDeque<>();
        int len = word.length();
        for(int i = 0; i < len; i++) {
            newWord.addLast(word.charAt(i));
        }
        return newWord;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordOfDeque = this.wordToDeque(word);
        return isPalindromeHelper(wordOfDeque);
    }

    private boolean isPalindromeHelper(Deque<Character> wordOfDeque) {
        int size = wordOfDeque.size();
        if(size == 0 || size == 1){
            return true;
        }
        Character l1 = wordOfDeque.removeFirst();
        Character l2 = wordOfDeque.removeLast();
        if(l1 != l2){return false;}
        return isPalindromeHelper(wordOfDeque);
    }


    public boolean isPalindrome(String word, CharacterComparator cc){
        if(word == null || word.length() <= 1){
            return true;
        }
        int len = word.length();
        for(int i=0; i < len / 2; i++){
            if(! cc.equalChars(word.charAt(i),word.charAt(len-1-i))){
                return false;
            }
        }
        return true;

    }
}
