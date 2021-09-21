/**
 * Given a string of numbers,
 * numbers=123
 * find how many unique expressions exists.
 *
 * e.g. solution example
 * 1+2+3 = 6  //this is an expression and is unique
 * 1+2-3 = 0 // this is unique too
 * 1-2+3 = 2 // tmb unique
 * 1-2-3 = -6 // si es unique
 *
 * correct answer:
 * there are 4 unique solutions
 */

public class InterviewQuestion002 {

    public int uniqueExpressions() {
        int[] numbers = {1,2,3};
        //How many signs do we need + or -
        int totalSigns = numbers.length;
        //Permutate over the total given signs
        permutation("+-+-");

        // <= to ensure we dont go out of bounds
        for (int i = 0; i <= numbers.length; i++) {
            System.out.println("test");
        }
        return -1;
    }

    // CODE BELOW WAS NOT WRITTEN BY ME
    // THANKS A LOT TO https://stackoverflow.com/questions/4240080/generating-all-permutations-of-a-given-string
    public static void permutation(String str) {
        permutation("", str);
    }

    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) System.out.println(prefix);
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }

    public static void main(String[] args){
        InterviewQuestion002 ques002 = new InterviewQuestion002();
        ques002.uniqueExpressions();
    }
}
