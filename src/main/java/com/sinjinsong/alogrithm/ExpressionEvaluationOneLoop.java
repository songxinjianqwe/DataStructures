package com.sinjinsong.alogrithm;

/**
 * @author sinjinsong
 * @date 2018/2/12
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExpressionEvaluationOneLoop {

	/**
	 * 表达式栈
	 */
	private Stack<Character> exprStack;
	/**
	 * 结果栈
	 */
	private Stack<Double> resultStack;

	private String infix;
	private Map<Character, Double> params;

	public ExpressionEvaluationOneLoop(String infix, Map<Character, Double> params) {
		exprStack = new Stack<>();
		resultStack = new Stack<>();
		this.infix = infix;
		this.params = params;
	}

	/**
	 * 主程序
	 */
	public void execute() {
		exprStack.push('#');
		char ch = 0;
		char op = 0;
		for (int i = 0; i < infix.length(); i++) {
			ch = infix.charAt(i);
			if (Character.isAlphabetic(ch)) {
				resultStack.push(params.get(ch));// 将操作数直接进结果栈
			} else if (ch == ')') {// 遇到右括号，不断出栈输出直至左括号出栈
				for (op = exprStack.pop(); op != '('; op = exprStack.pop()) {
					doOperator(op);// 对操作符而言，需要将结果栈出栈两次，再将结果进结果栈
				}
			} else {// 包含了左括号和运算符的情况
					// isp是栈中运算符的优先级；icp是表达式中运算符的优先级：不同在于左括号在isp中优先级最低，在icp中优先级最高
				for (op = exprStack.pop(); isp(op) > icp(ch); op = exprStack.pop()) {
					// 当栈顶运算符优先级高于当前运算符优先级时结束，否则就不断出栈输出
					// 栈底运算符优先级最低，栈顶运算符优先级最高
					doOperator(op);
				}
				exprStack.push(op);
				// 结束时将当前运算符入栈
				exprStack.push(ch);
			}
		}
		while (!exprStack.isEmpty()) {
			// 将栈中剩余未出栈的全部出栈（全是运算符）
			ch = exprStack.pop();
			if (ch == '#') {
				break;
			} else {
				doOperator(ch);
			}
		}
		System.out.println(resultStack.peek());
	}

	/**
	 * 对运算符进行操作
	 * 
	 * @param operator
	 */
	private void doOperator(char operator) {
		double left = 0, right = 0;
		if (!resultStack.isEmpty()) {
			right = resultStack.pop();
		} else {
			System.err.println("表达式错误，无法取出右操作数");
			resultStack.clear();
			return;
		}
		if (!resultStack.isEmpty()) {
			left = resultStack.pop();
		} else {
			System.err.println("表达式错误，无法取出左操作数");
			resultStack.clear();
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
		resultStack.push(result);
	}

	/**
	 * 判断优先级
	 * 
	 * @param ch
	 * @return
	 */
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

	public static void main(String[] args) {
		String infix = "(a+b)*(c-e^f)/d";
		Map<Character, Double> params = new HashMap<>();
		params.put('a', 3.0);
		params.put('b', 1.0);
		params.put('c', 5.0);
		params.put('d', 2.0);
		params.put('e', 3.0);
		params.put('f', 2.0);
		ExpressionEvaluationOneLoop program = new ExpressionEvaluationOneLoop(infix, params);
		program.execute();
	}
}
