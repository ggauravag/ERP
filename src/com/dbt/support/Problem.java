package com.dbt.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem {
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int j = 0; j < T; j++)
		{
			int M = sc.nextInt();
			int N = sc.nextInt();

			while(N > 0)
			{
				int a = (int) (Math.log(N)/Math.log(2));
				N = N - (int)Math.pow(2, a);
				if(N > 0)
				{
					System.out.print("("+M+"<<"+a+") + ");
				}
				else
				{
					System.out.print("("+M+"<<"+a+")");
				}
			}
			System.out.println();
		}	
		sc.close();
	}

}
