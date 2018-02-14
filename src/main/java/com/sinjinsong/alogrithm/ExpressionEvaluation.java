package com.sinjinsong.alogrithm;

/**
 * @author sinjinsong
 * @date 2018/2/12
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExpressionEvaluation {
    private static int icp(char ch) {
        switch (ch) {
            case '#':
                return 0;
            case '(':
                return 6;
            case '*':
            case '/':
            case '^':
                return 6;
            case '+':
            case '-':
                return 2;
            case ')':
                return 1;
        }
        return 0;
    }

    private static int isp(char ch) {
        switch (ch) {
            case '#':
                return 0;
            case '(':
                return 1;
            case '*':
            case '/':
            case '^':
                return 5;
            case '+':
            case '-':
                return 3;
            case ')':
                return 6;
        }
        return 0;
    }

    public static String toPostfix(String infix) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        stack.push('#');
        char ch = 0;
        char op = 0;
        for (int i = 0; i < infix.length(); i++) {
            ch = infix.charAt(i);
            if (Character.isAlphabetic(ch)) {
                sb.append(ch);//操作数直接输出
            } else if (ch == ')') {//遇到右括号，不断出栈输出直至左括号出栈
                for (op = stack.pop(); op != '('; op = stack.pop()) {
                    sb.append(op);
                }
            } else {//包含了左括号和运算符
                //isp是栈中运算符的优先级；icp是表达式中运算符的优先级：不同在于左括号在isp中优先级最低，在icp中优先级最高
                for (op = stack.pop(); isp(op) > icp(ch); op = stack.pop()) {
                    //当栈顶运算符优先级高于当前运算符优先级时结束，否则就不断出栈输出
                    sb.append(op);
                }
                //结束时将当前运算符入栈
                stack.push(op);
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            //将栈中剩余未出栈的全部出栈
            sb.append(stack.pop());
        }
        System.out.println(sb.toString());
        return sb.toString();
    }


}

/**
 * 用于计算后缀式
 *
 * @author songx
 */
class Calculator {
    private Stack<Double> stack;

    public Calculator() {
        stack = new Stack<>();
    }

    public void run(String expr, Map<Character, Double> params) {
        char ch = 0;
        for (int i = 0; i < expr.length(); i++) {
            ch = expr.charAt(i);
            switch (ch) {
                //处理运算符
                case '+':
                case '-':
                case '*':
                case '/':
                case '^':
                    doOperator(ch);
                    break;
                case '#'://结束
                    break;
                //处理操作数
                default:
                    addOperand(params.get(ch));
                    break;
            }
        }
        System.out.println("Result:" + stack.peek());
    }

    private void addOperand(double operand) {
        stack.push(operand);
    }

    private void doOperator(char operator) {
        double left = 0, right = 0;
        if (!stack.isEmpty()) {
            right = stack.pop();
        } else {
            System.err.println("表达式错误，无法取出右操作数");
            stack.clear();
            return;
        }
        if (!stack.isEmpty()) {
            left = stack.pop();
        } else {
            System.err.println("表达式错误，无法取出左操作数");
            stack.clear();
            return;
        }

        double result = 0;
        switch (operator) {
            case '+':
                result = left + right;
                break;
            case '-':
                result = left - right;
                break;
            case '*':
                result = left * right;
                break;
            case '/':
                if (Math.abs(right) < 1e-6) {
                    System.err.println("除数为零!");
                } else {
                    result = left / right;
                }
                break;
            case '^':
                result = Math.pow(left, right);
                break;
        }
        stack.push(result);
    }

    public static void main(String[] args) {
        String infix = "(a+b)*(c-e^f)/d";
        Calculator calc = new Calculator();
        Map<Character, Double> params = new HashMap<>();
        params.put('a', 3.0);
        params.put('b', 1.0);
        params.put('c', 5.0);
        params.put('d', 2.0);
        params.put('e', 3.0);
        params.put('f', 2.0);
        calc.run(ExpressionEvaluation.toPostfix(infix), params);
    }
}
