package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static int [] p ; public static int [] w ;public static int[] sorted;
    public static int n, c, c1;
    public static int j = 0, zz = 0, z = 0;
    public static int[] x ;public static  int[] xx ;
    public static int u = 0;

    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите количество предметов");
        n = Integer.parseInt(reader.readLine());
        System.out.println("Введите вместимость");
        c = Integer.parseInt(reader.readLine());
        c1=c;
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
        w[n] = w[n - 1] + 10000;
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

        int aa[] = new int[n - j + 1]; int bb[] = new int[n - j + 1];
        aa[n-j] = c1; bb[n-j] = n;
        for(int i = 0; i < n - j; i++)
        {
            aa[i] = w[j + i];
            bb[i] = j + i;
            aa[n-j] = aa[n-j]+aa[i];
            bb[n-j] = bb[n-j]+bb[i];
        }

        bubble(aa, n - j, bb);


        int sum1 = 0; int h1 = 0;

        while ( (sum1 <= c))
        {
            sum1 = sum1 + aa[h1];
            h1 ++;

        }
        h1--;sum1 = sum1 - aa[h1];

        int sum2 = 0; int h2 = n - j - 1;
        while ((sum2 <= c) && (h2>=0))
        {
            sum2 = sum2 + aa[h2];
            h2--;

        }
        if (h2 >=0)
        {
            h2 ++; sum2 = sum2 - aa[h2];
        }


        h2 = n - j - 1 - h2;

        if ((h1 == h2) && (h1 != 0) && (h1 + h2 <= n - j))
        {
            for (int i = n - j - 1; i >= n - j - h2; i--)
            {
                x[bb[i]] = 1;
                z = z + p[bb[i]];
            }

            updates1();
        }
        else {
            while (sum < c) {
                m = m + 1;
                sum = sum + w[m];


            }
            while (k < m) {

                a = a + p[k];
                d = d + w[k];
                k = k + 1;
            }
            u = (int) (a + ((c - d) * p[m] / w[m]));


            if (zz >= z + u) {
                backtrack();
            } else {
                forward();
            }


        }
     }
     public static void forward ()
     {
         while (w[j] <= c)
         {
             c = c - w[j];
             z = z + p[j];
             x[j] = 1;
             j = j +1;

         }
         if (j <= n - 1)
         {
             x[j] = 0;

             j = j + 1;

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

             }

         }
         j = n -1;
         if (x[n-1] == 1)
         {
             c = c + w[n-1];
             z = z - p[n-1];
             x[n-1] = 0;


         }

         backtrack();
     }
     public static void backtrack ()
     {   int f = 0;
         int k = j;

         while ((f == 0) && (k >= 0))
         {

             k = k - 1;

             if (k == -1) break;
             if (x[k] == 1)
                 f = 1;

         }

         if (f == 0) return;
         c = c + w[k];
         z = z - p[k];
         x[k] = 0;
         j = k + 1;

         cub();


     }
    public static void bubble(int[] a, int n, int b[])
    {
        for (int i=n-1; i>=0; i--)
        {
            for (int j=0; j<i; j++)
            {
                if (a[j] > a[j+1])
                {
                    int tmp = a[j];
                    int tmp2 = b[j];
                    a[j] = a[j+1];
                    b[j] = b[j + 1];
                    a[j+1] = tmp;
                    b[j+1] = tmp2;
                }
            }
        }
    }
    public static void updates1()
    {

            zz = z;
            for (int k = 0; k < n; k++)
            {
                xx[k] = x[k];
                //System.out.print(xx[k] + " ");
            }



    }
}
