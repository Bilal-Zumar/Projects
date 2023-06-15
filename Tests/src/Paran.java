import sun.awt.image.ImageWatched;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Paran {

    public static boolean findCorrectSequence(String sequence)
    {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('}','{');
        map.put(')','(');
        map.put(']','[');

        List stack = new LinkedList<Character>();

        for (int i = 0; i < sequence.length(); i++) {
            if(sequence.charAt(i) == '{' || sequence.charAt(i) == '(' || sequence.charAt(i) == '[')
            {
                stack.add(sequence.charAt(i));
            }
            else {
                if (stack.size() >0 && map.get(sequence.charAt(i)) == stack.get(stack.size()-1))
                {
                    stack.remove(stack.size()-1);
                }
                else {
                    return false;
                }
            }
        }
        if(stack.size()==0)
           return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(findCorrectSequence("]"));
    }
}
