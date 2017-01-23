package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int [] p ; static int [] w ;
    static int n, c;
    static int j = 0, zz = 0, z = 0;
    static int[] x ; static  int[] xx ;
    static int u = 0;

    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите количество предметов");
        n = Integer.parseInt(reader.readLine());
        System.out.println("Введите вместимость");
        c = Integer.parseInt(reader.readLine());
        p = new int[n+1];
        w = new int[n+1];
        x = new int[n];
        xx = new int[n];
        for (int i = 0; i < n; i++)
        {
            System.out.println("Введите вес " + (i + 1) + "го элемента");
            w[i] = Integer.parseInt(reader.readLine());
            System.out.println("Введите профит " + (i + 1) + "го элемента");
            p[i] = Integer.parseInt(reader.readLine());

        }
        w[n] = w[n - 1] + 100;
        p[n] = 0;





        cub();
        System.out.print("Оптимальное решение: ");
        for (int k = 0; k < n; k++)
        {

            System.out.print(xx[k] + " ");
        }
        System.out.println("");
        System.out.println("Максимум целевой функции = " + zz);

    }
    public static void cub ()
    {   int a = 0;
        int sum = 0;
        int m = j - 1;
        int k = j;
        int d = 0;
        //System.out.println("это текущая вместимость = " + c);
        while ( sum < c )
        {
            m = m + 1;
            sum = sum + w[m ];


        }
        while ( k < m )
        {

            a = a + p[k];
            d = d + w[k];
            k = k + 1;
        }
            u = (int)(a + ((c - d)*p[m]/w[m]));
            //System.out.println(" это верхняя граница " + u);
            if (zz >= z+u)
               {   backtrack();}
            else
                { forward();}



     }
     public static void forward ()
     {
         while (w[j] <= c)
         {
             c = c - w[j];
             z = z + p[j];
             x[j] = 1;
             j = j +1;
             //System.out.println("Это j = " + j + " иr x = " + x[j-1]);
         }
         if (j <= n - 1)
         {
             x[j] = 0;
             //System.out.println("это j = " + j + " и х = " + x[j]);
             j = j + 1;
             //System.out.println("это последняя j = " + j);
         }
         if (j < n - 1) {cub();}
         if (j == n - 1) {forward();}
         if (j > n - 1) {updates();}
     }
     public static void updates()
     {
         if (z > zz)
         {
             zz = z;
             for (int k = 0; k < n; k++)
             {
                 xx[k] = x[k];
                 //System.out.print(xx[k] + " ");
             }

         }
         j = n -1;
         if (x[n-1] == 1)
         {
             c = c + w[n-1];
             z = z - p[n-1];
             x[n-1] = 0;
             //System.out.println(" это x[6] = " + x[n-1]);

         }
         //System.out.println(" это backtrack из updates ");
         backtrack();
     }
     public static void backtrack ()
     {   int f = 0;
         int k = j;
         //System.out.println( "изначальное k " + k);
         while ((f == 0) && (k >= 0))
         {

             k = k - 1;

             if (k == -1) break;
             if (x[k] == 1)
                 f = 1;
             //System.out.println( "новое f " + f);
         }

         if (f == 0) return;
         c = c + w[k];
         z = z - p[k];
         x[k] = 0;
         j = k + 1;
         cub();


     }
}
