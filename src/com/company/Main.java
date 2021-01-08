package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        double[] c1={1,2,3};
        double[] c2= {-1,-2,-2};
        double[] c4= {5,0,0, -1,34,1,-2};

        polinom p1 = new polinom (c1);
        polinom p2 = new polinom(c2);
        polinom p4 = new polinom (c4);
        System.out.println(p1.toString());
        System.out.println(p2.toString());

        System.out.println(p1.  y(2));

    }

}
