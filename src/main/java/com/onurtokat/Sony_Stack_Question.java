package com.onurtokat;

import java.util.Stack;
import java.util.regex.Pattern;

public class Sony_Stack_Question {

    /*
   *solution method returns peek value of the stack
   * @param S string
   * @return integer value of latest element of the stack
    */
    public static int solution(String S) {

        //stack will collect elements
        Stack<String> stack = new Stack<>();

        //Split for method string parameter
        String[] split = S.split("\\s+");

        for (String s : split) {

            //if string contains only the number
            if (!s.matches("[a-zA-Z]+") && (!s.matches(Pattern.quote("+")) && !s.matches(Pattern.quote("-")))) {
                stack.push(s);
            } else if (s.contains("DUP")) {
                stack.push(stack.get(stack.size()-1));
            } else if (s.contains("POP")) {
                stack.pop();
            } else if (s.matches(Pattern.quote("+"))) {
                int tmpInt=0;
                if (stack.size() >= 2) {
                    tmpInt = Integer.valueOf(stack.get(stack.size() - 1)) + Integer.valueOf(stack.get(stack.size
                            () - 2));
                    stack.pop();
                    stack.pop();
                    stack.push(Integer.toString(tmpInt));
                }else{
                    return -1;
                }
            }else if (s.matches(Pattern.quote("-"))) {
                int tmpInt=0;
                if (stack.size() >= 2) {
                    tmpInt = Integer.valueOf(stack.get(stack.size() - 1)) - Integer.valueOf(stack.get(stack.size
                            () - 2));
                    stack.pop();
                    stack.pop();
                    stack.push(Integer.toString(tmpInt));
                }else{
                    return -1;
                }
            }
        }
        if(!stack.isEmpty()){
            return Integer.valueOf(stack.peek());
        }else {
            return 0;
        }
    }

    public static void main(String[] args) {
        //System.out.println(solution("13 DUP 4 POP 5 DUP + DUP + -"));
        System.out.println(solution("5 6 + -"));
        //System.out.println(solution("3 DUP 5 - -"));

    }
}
