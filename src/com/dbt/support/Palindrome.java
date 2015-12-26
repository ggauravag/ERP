package com.dbt.support;

import java.util.Scanner;

public class Palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		String input = sc.nextLine();
		char[] inputChars = input.toCharArray();
		boolean flag = false;
		int i = 0;
		
		if(inputChars.length == 2)
			i = 1;
		else if(inputChars.length == 1)
			i = 0;
		else
		{
			for(i = Math.round(inputChars.length/2.0f); i < inputChars.length && !flag ;)
			{
				if(inputChars.length > 2)
				{
					if(i == inputChars.length-1)
					{
						flag = true;
					}
					else
					{
						boolean cont = true;
						int j;
						for(j = 1; j <= inputChars.length - i - 1 && cont; j++)
						{
							if(inputChars[i - j] != inputChars[i + j])
							{
								cont = false;
							}
						}
						
						if(cont)
						{
							flag = true;
						}
						else
							i++;
					}
				}
			}
		}
		
		
		//System.out.println("Input Size : "+inputChars.length+", i : "+i);
		
		char[] answer = null;
		
		
		if(inputChars.length <= 2)
		{
			if(i == 1)
			{
				answer = new char[3];
				answer[0] = inputChars[0];
				answer[1] = inputChars[1];
				answer[2] = inputChars[0];
			}
			else if(i == 0)
			{
				answer = new char[2];
				answer[0] = inputChars[0];
				answer[1] = inputChars[0];
			}
		}
		else
		{
			int addLength = i;
			int finalLength = 2*addLength + 1;
			answer = new char[finalLength];
			int l;
			for(l = 0; l <= i; l++)
			{
				answer[l] = inputChars[l];
			}
			
			for(int m = i-1; m >= 0; m--,l++)
			{
				answer[l] = inputChars[m];
			}
				
		}
		
		System.out.println(String.valueOf(answer));
		
		
		sc.close();
	}

}
