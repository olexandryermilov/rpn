package com.tasks7.rpn;

import java.util.*;

public class Application {

    public static char[] operators = {'+','-','/','*'};
    public static boolean isOperator(String s)
    {
        if(s.length()!=1)return false;
        for(int i=0;i<operators.length;i++){
            if(s.charAt(0)==operators[i])return true;
        }
        return false;
    }
    public static boolean isNumber(String s)
    {
        if(s.length()==0)return false;
        boolean hasDot=false;
        if(s.charAt(0)=='-'&&s.length()==1)return false;
        if(s.charAt(0)=='-'||(s.charAt(0)>='0'&&s.charAt(0)<='9')) {
            for(int i=1;i<s.length();i++) {
                if(s.charAt(i)=='.'&&s.charAt(i-1)!='-'&&!hasDot)
                {
                    hasDot=true;
                    continue;
                }
                if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                    return false;
                }
            }
        }
        else {
            return false;
        }
        return true;
    }

    public static double parse(String rpnString) {
        //System.out.println(rpnString);
        if(rpnString.length()<=2)throw new RPNParserException();
        String[] s = rpnString.split(" ");
        Stack<Double> st = new Stack<Double>();
        for(int i=0;i<s.length;i++)
        {
            if(s[i].equals("NaN"))
            {
                throw new ArithmeticException();
            }
            if(isNumber(s[i]))
            {
                st.push(Double.parseDouble(s[i]));
            }
            else
            {
                if(isOperator(s[i]))
                {
                    if(st.size()<2)throw new RPNParserException();
                    double a=st.pop();
                    double b=st.pop();
                    if(a==0.0&&s[i].charAt(0)=='/')throw new ArithmeticException();
                    switch (s[i].charAt(0))
                    {
                        case '+':st.push(a+b);break;
                        case '-':st.push(b-a);break;
                        case '*':st.push(a*b);break;
                        case '/':st.push(b/a);break;
                    }
                }
                else
                {
                    throw new RPNParserException();
                }
            }
        }
        if(st.size()!=1)throw new RPNParserException();
        double res=st.pop();
        return res;
    }

    public static void main(String[] args)
    {

    }

}

