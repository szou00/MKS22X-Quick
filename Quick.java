import java.util.Random;
import java.util.Arrays;

public class Quick {

  /*return the value that is the kth smallest value of the array.
 */
 public static int quickselect(int []data, int k){
   int p = partition(data,0,data.length-1);
   while (k!=p) {
     if (p < k) {
       p = partition(data,p+1,data.length-1);
     }
     if (p > k) {
       p = partition(data,0,p-1);
     }
   }
   return data[p];
 }

   /*Modify the array to be in increasing order.
   */
   public static void quicksort(int[] data) {
     quicksort(data,0,data.length-1);
   }

   public static void quicksort(int[] data, int lo, int hi) {
     if (lo >= hi) {
       //base case
       //nothing happens
     }
     int pivot = partition(data,lo,hi);
     quicksort(data,pivot+1,hi);
     quicksort(data,lo,pivot-1);
   }



 public static int partition ( int [] data, int start, int end){
   // Random rand = new Random();
   int s = start;
   // System.out.println(end+1-start);

   //choosing the median of the lowest, highest, and middle elements
   int min;int max;int med;
   min = start;
   if (data[start+end/2] >= data[min]) {
     med = start+end/2;
   }
   else {
     min = start+end/2;
     med = start;
   }
   if (data[end] >= data[min]) {
     if (data[end] >= data[med]) {
       max = end;
     }
     else {
       max = start+end/2;
       med = end;
     }
   }
   else {
     min = end;
     med = start;
     max = start+end/2;
   }

   int x = med;
   // System.out.println(x);
   int target = data[x];
   data[x] = data[start];
   data[start] = target;

   if (start!= end) {
     start+=1;
   }
   while (start != end) {
     if (data[start] >= target) {
       int temp = data[end];
       data[end] = data[start];
       data[start] = temp;
       end-=1;
     }
     else {
       start+=1;
     }
   }
   if (data[start] > target) {
     data[s] = data[start-1];
     data[start-1] = target;
     start -= 1;
   }
   else {
     data[s] = data[start];
     data[start] = target;
   }
   return start;
 }


 public static void main(String args[]) {
   int[]ary = { 2, 10, 15, 23, 0,  5};
   // for (int i = 0; i < ary.length; i++) {
   //   System.out.println(quickselect(ary,i));
   // }
   // quicksort(ary);
   partition(ary,0,5);
   System.out.println(Array.toString(ary));
 }


}
