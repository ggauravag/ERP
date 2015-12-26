package com.dbt.support;

import java.util.Scanner;

class StackUnderflowException extends Exception {
	public String getMessage() {
		return "Stack Underflow Occurred";
	}
}

class StackOverFlowException extends Exception {
	public String getMessage() {
		return "Stack Overflow Occurred";
	}
}

class Stack // To create Stack ADT for this program only
{
	private char[] stack;
	private int top;
	private int capacity;

	public Stack() {
		capacity = 200001;
		stack = new char[capacity];			// To create a stack with maximum capacity of 200001
		top = -1;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public void ensureCapacity(int capacity) {   //To Change capacity dynamically
		this.capacity = capacity;
		char[] temp = stack.clone();
		stack = new char[capacity];
		for(int i = 0; i < temp.length; i++)
		{
			stack[i] = temp[i];
		}
	}

	public boolean isEmpty() {
		if (top == -1)
			return true;
		else
			return false;
	}

	public char peek() throws StackUnderflowException {
		if (top == -1)
			throw new StackUnderflowException();
		else
			return stack[top];
	}

	public char pop() throws StackUnderflowException {
		if (top == -1) 
		{
			throw new StackUnderflowException();
		} 
		else 
		{
			char toReturn = stack[top];
			top--;
			return toReturn;
		}
	}

	public void push(char inp) throws StackOverFlowException {
		if (top == capacity - 1) 
		{
			throw new StackOverFlowException();
		}
		top++;
		stack[top] = inp;
	}
}

public class Cipher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input;
		Scanner sc = new Scanner(System.in);
		
		input = sc.nextLine();
		Stack stackInput = new Stack();   //To store input into a stack
		Stack stackOutput = new Stack();   // To store output into a stack
		
		
		char[] inputArray = input.toCharArray();
		for(int i = 0; i < inputArray.length; i++)    // Storing the character input array into input Stack
		{
			try
			{
				stackInput.push(inputArray[i]);
			}
			catch(StackOverFlowException ex)
			{
				ex.printStackTrace();
			}
		}
		
		while(!stackInput.isEmpty())   // Inserting each input character from stack into output stack if both the top of the stack does not match else pop them both
		{
			try
			{
				if(stackOutput.isEmpty())   //If output stack is empty then push input directly to the output stack
				{
					stackOutput.push(stackInput.pop());
				}
				else
				{
					char inputTop = stackInput.peek();
					char outputTop = stackOutput.peek();
					
					if(inputTop == outputTop)  //if both the TOP is same then pop both because they are the identical characters inserted at some arbitrary place
					{
						stackInput.pop();
						stackOutput.pop();
					}
					else
					{
						stackOutput.push(stackInput.pop());
					}
				}
			}
			catch(StackOverFlowException ex)
			{
				ex.printStackTrace();
			}
			catch(StackUnderflowException ex)
			{
				ex.printStackTrace();
			}
		}
		
		while(!stackOutput.isEmpty())     //Now just to print the output to the console.
		{	
			try 
			{
				System.out.print(stackOutput.pop());
			}
			catch (StackUnderflowException ex)
			{
				ex.printStackTrace();
			}
		}
		
	}
}
