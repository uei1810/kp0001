package com.company;

//import com.sun.org.apache.xpath.internal.SourceTree;

//import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static int [] p ; public static int [] w ;public static int[] sorted;
    public static int n, c, c1, t = 1;
    public static int j = 0, zz = 0, z = 0, z1 = 0;
    public static int[] x ;public static  int[] xx ;
    public static int u = 0;
    public static ArrayList<Integer> aa = new ArrayList<Integer>();
    public static ArrayList<Integer> bb = new ArrayList<Integer>();
    public static ArrayList<Integer> pp = new ArrayList<Integer>();
    public static ArrayList<Integer> dd = new ArrayList<Integer>();

    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите количество предметов");
        n = Integer.parseInt(reader.readLine());
        System.out.println("Введите вместимость");
        c = Integer.parseInt(reader.readLine());
        c1 = c;
        p = new int[n+1];
        w = new int[n+1];
        x = new int[n];
        xx = new int[n];
        for (int i = 0; i < n; i++)
        {
            System.out.println("Введите вес " + (i + 1) + "го элемента");
            w[i] = Integer.parseInt(reader.readLine());
            aa.add(w[i]);
            bb.add(i);
            System.out.println("Введите профит " + (i + 1) + "го элемента");
            p[i] = Integer.parseInt(reader.readLine());
            pp.add(p[i]);
            dd.add(i);

        }
        w[n] = w[n - 1] + 10000;
        p[n] = 0;
        bubble(aa, bb, n);
        bubble(pp, dd, n);
        System.out.print("сортированные веса аа: ");
        for (int k = 0; k < n; k++)
        {
            System.out.print(aa.get(k) + " ");

        }
        System.out.println("размер списка aa = " + aa.size());
        System.out.print("сортированные номера весов bb: ");
        for (int k = 0; k < n; k++)
        {
            System.out.print(bb.get(k) + " ");
        }
        System.out.println("");
        System.out.print("сортированные выгоды pp: ");
        for (int k = 0; k < n; k++)
        {
            System.out.print(pp.get(k) + " ");

        }
        System.out.println("размер списка pp = " + pp.size());
        System.out.print("сортированные номера выгод pp: ");
        for (int k = 0; k < n; k++)
        {
            System.out.print(dd.get(k) + " ");
        }
        System.out.println("");
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


            while (sum <= c) {
                m = m + 1;
                sum = sum + w[m];


            }
             System.out.println("из cub(): можно впихнуть " + (m) + " элементов, начиная с j = " + j + "го элемента");
            while (k < m) {

                a = a + p[k];
                d = d + w[k];
                k = k + 1;
            }
            u = (int) (a + ((c - d) * p[m] / w[m]));
            System.out.println("верхняя граница, при m = " + m + ", u = " + u);


            if (zz >= z + u) {
                backtrack();
            } else {
                forward(); if(t==0) return;
            }


        //}
     }
     public static void forward ()
     {
         while (w[j] <= c)
         {
             c = c - w[j]; c1 = c;
             z = z + p[j]; z1 = z;
             x[j] = 1;
             j = j +1;

             for (int i = 0; i < aa.size(); i++)
             {
                 if (j - 1 == bb.get(i))
                 {
                     aa.remove(i);
                     bb.remove(i);
                 }

             }
             for (int i = 0; i < pp.size(); i++)
             {
                 if (j - 1 == dd.get(i))
                 {
                     pp.remove(i);
                     dd.remove(i);
                 }
             }


             for (int i = 0; i < aa.size(); i++)
             {
                 System.out.print(aa.get(i) + " ");
             }

             for (int i = 0; i < bb.size(); i++)
             {
                 System.out.print(bb.get(i) + " ");
             }

             for (int i = 0; i < pp.size(); i++)
             {
                 System.out.print(pp.get(i) + " ");
             }

             for (int i = 0; i < dd.size(); i++)
             {
                 System.out.print(dd.get(i));
             }

             check();
             if (t==0) return;
         }
         if (j <= n - 1)
         {

             x[j] = 0;
             j = j + 1;
             for (int i = 0; i < aa.size(); i++)
             {
                 if (j - 1 == bb.get(i))
                 {
                     aa.remove(i);
                     bb.remove(i);
                 }

             }
             for (int i = 0; i < pp.size(); i++)
             {
                 if (j - 1 == dd.get(i))
                 {
                     pp.remove(i);
                     dd.remove(i);
                 }
             }

             for (int i = 0; i < aa.size(); i++)
             {
                 System.out.print(aa.get(i) + " ");
             }

             for (int i = 0; i < bb.size(); i++)
             {
                 System.out.print(bb.get(i) + " ");
             }

             for (int i = 0; i < pp.size(); i++)
             {
                 System.out.print(pp.get(i) + " ");
             }

             for (int i = 0; i < dd.size(); i++)
             {
                 System.out.print(dd.get(i));
             }
             check();

         }
         if (j < n - 1) {
             System.out.println("запустился cub из forward");cub();}
         if (j == n - 1) {
             System.out.println("запустился forward из forward");forward();}
         if (j > n - 1) {
             System.out.println("запустился простой updates из forward");updates();}
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


         if (k > -1) {

             c = c1 + w[k];
             c1 = c;
             z = z1 - p[k];
             z1 = z;
             x[k] = 0;
             for (int i = k + 1; i < n; i++) {
                 x[i] = 0;
             }

             for (int i = k + 1; i < j; i++) {

                 binsearch(aa, bb, w[i], i);
                 binsearch(pp, dd, p[i], i);

             }


             for (int i = 0; i < aa.size(); i++) {
                 System.out.print(aa.get(i) + " ");
             }

             for (int i = 0; i < bb.size(); i++) {
                 System.out.print(bb.get(i) + " ");
             }

             for (int i = 0; i < pp.size(); i++) {
                 System.out.print(pp.get(i) + " ");
             }

             for (int i = 0; i < dd.size(); i++) {
                 System.out.print(dd.get(i));
             }

             j = k + 1;

             for (int i = 0; i < n; i++)
                 System.out.print("x[" + i + "] = " + x[i] + ", ");
         }
         if (f == 0) {
             System.out.println("зашли в if, f==0, и запускаем return из backtrack"); t = 0;
             return;
         }

         cub();


     }
    public static void bubble(ArrayList<Integer> a, ArrayList<Integer> b, int n)
    {
        for (int i=n-1; i>=0; i--)
        {
            for (int j=0; j<i; j++)
            {
                if (a.get(j) > a.get(j + 1))
                {
                    int tmp = a.get(j);
                    int tmp2 = b.get(j);
                    a.set(j, a.get(j + 1));
                    b.set(j, b.get(j + 1));
                    a.set(j+1,tmp);
                    b.set(j+1, tmp2);
                }
            }
        }
    }
    public static void updates1()
    {

        if (z > zz)
        {
            zz = z;
            for (int k = 0; k < n; k++)
            {
                xx[k] = x[k];

            }
        }

        for (int k = 0; k < n; k++)
        {
            System.out.print(xx[k]+" ");

        }


        backtrack();
    }
    public static void binsearch(ArrayList<Integer> a,ArrayList<Integer> b, int value, int index)
    {  int min = 0; int max = a.size(); int m;
        while (max - min > 0)
        {
            m = (max + min) / 2;
            if (a.get(m) > value)
                max = m;
            else
                min = m + 1;
        }
        a.add(max, value);
        b.add(max, index);

    }
    public static void check()
    {
        int sum1 = 0; int h1 = 0;
        while ((sum1 <= c) && (h1 < aa.size()))
        {
            sum1 = sum1 + aa.get(h1);
            h1++;
        }
        if (sum1 > c)
        {
            h1--; sum1 = sum1 - aa.get(h1);
        }

        int sum2 = 0; int h2 = aa.size();
        while ((sum2 <= c) && (h2 > 0))
        {
            h2--;
            sum2 = sum2 + aa.get(h2);
        }
        if (sum2 > c)
        {
            sum2 = sum2 - aa.get(h2); h2++;
        }
        h2 = aa.size() - h2;

        if (h1 == h2)
        {


            for (int i = 0; i < h1; i++)
            {
                x[dd.get(aa.size() - 1 -i)] = 1;
                z = z + pp.get(aa.size() - 1 - i);
                c = c - w[dd.get(aa.size() - 1 - i)];
                System.out.print("x[" + dd.get(aa.size() - 1 -i) + "] = 1 ");
            }
            //System.out.println("текущая вместимость c = " + c);
            //System.out.println("текущее значение z = " + z);

            updates1();
        }
    }
}
