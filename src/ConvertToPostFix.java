import java.util.Scanner;
import java.util.Stack;

public class ConvertToPostFix {

  /**
   * Problem: Convert an infix expression to postfix expression and evaluate it.
   * Step 1: Read the infix expression in string form from left to right.
   * Step 2: If the current character is an operand, add it to the postfix expression.
   * Step 3: If the current character is an operator, push it onto the stack. If the stack is empty, push the operator onto the stack.
   * Step 4: If the current character is an open parenthesis, push it onto the stack.
   * Step 5: If the current character is a closing parenthesis, pop all the operators from the stack until an open
   * parenthesis is found. Add each operator to the postfix expression.
   * Step 6: Repeat steps 2-5 until the infix expression is completely read.
   * Step 7: Pop all the operators from the stack add them to the postfix expression.
   * Step 8: The postfix expression is the final output.
   **/

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the formula: ");
//    String formula = sc.nextLine();
    String formula = "(1+3*3)/2+(4+5^3)/3";
    System.out.println("Postfix expression: " + infixToPostfix(formula));
    System.out.println("Result: " + evaluatePostfix(infixToPostfix(formula)));

  }

  private static String evaluatePostfix(String s) {
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        stack.push(c - '0');
      } else {
        int a = stack.pop();
        int b = stack.pop();
        switch (c) {
          case '+':
            stack.push(b + a);
            break;
          case '-':
            stack.push(b - a);
            break;
          case '*':
            stack.push(b * a);
            break;
          case '/':
            stack.push(b / a);
            break;
          case '^':
            stack.push((int) Math.pow(b, a));
            break;
        }
      }
    }
    return String.valueOf(stack.pop());
  }

  private static String infixToPostfix(String formula) {
    Stack<Character> stack = new Stack<>();
    StringBuilder postfix = new StringBuilder();
    for (int i = 0; i < formula.length(); i++) {
      char c = formula.charAt(i);
      if (Character.isLetterOrDigit(c)) {
        postfix.append(c);
      } else if (c == '(') {
        stack.push(c);
      } else if (c == ')') {
        while (!stack.isEmpty() && stack.peek() != '(') {
          postfix.append(stack.pop());
        }
        stack.pop();
      } else {
        while (!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek())) {
          postfix.append(stack.pop());
        }
        stack.push(c);
      }
    }
    while (!stack.isEmpty()) {
      postfix.append(stack.pop());
    }
    return postfix.toString();
  }

  private static int getPrecedence(char c) {
    switch (c) {
      case '+':
      case '-':
        return 1;
      case '*':
      case '/':
        return 2;
      case '^':
        return 3;
    }
    return -1;
  }

}