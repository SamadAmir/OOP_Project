package com.company;
import java.util.StringTokenizer;
public class CostOperation {

    public int getExpense(String budgetBookData) {
        StringTokenizer st = new StringTokenizer(budgetBookData, " ");
        int[] a = {0, 0};
        int i = 0;
        while(st.hasMoreTokens())
        {
            a[i] = Integer.parseInt(st.nextToken());
            i++;
        }

        return a[1];
  }

    public int getBudget(String budgetBookData) {
        StringTokenizer st = new StringTokenizer(budgetBookData, " ");
        int[] a = {0, 0};
        int i = 0;
        while(st.hasMoreTokens())
        {
            a[i] = Integer.parseInt(st.nextToken());
            i++;
        }

        return a[0];
    }
    public boolean budgetCheck(int budget, int expense, int cost) {
        if (budget < (expense + cost))
            return true;
        else {
            return false;
        }
    }
    }

