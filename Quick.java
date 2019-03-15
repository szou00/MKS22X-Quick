import java.util.Random;
import java.util.Arrays;

public class Quick {

  /*return the value that is the kth smallest value of the array.
 */
 public static int quickselect(int []data, int k){
   int p = partition(data,0,data.length-1);
   // System.out.println("k: " + k + " p: " + p);
   while (k!=p) {
     if (p < k) {
       p = partition(data,p+1,data.length-1);
       // System.out.println(p);
     }
     else {
       if (p > k) {
         p = partition(data,0,p-1);
         // System.out.println("Second loop: " + p);
       }
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
     if (lo < hi) { //the only way something would happen
       int pivot = partition(data,lo,hi);
       quicksort(data,pivot+1,hi);
       quicksort(data,lo,pivot-1);
     }
   }



 public static int partition ( int [] data, int start, int end){
   //keeping track of the original start
   int s = start;
   // System.out.println(end+1-start);

   //choosing the median of the lowest, highest, and middle elements
   int min;int max;int med;
   min = start;
   if (data[(start+end)/2] >= data[min]) {
     med = (start+end)/2;
   }
   else {
     min = (start+end)/2;
     med = start;
   }
   if (data[end] >= data[min]) {
     if (data[end] >= data[med]) {
       max = end;
     }
     else {
       max = (start+end)/2;
       med = end;
     }
   }
   else {
     min = end;
     med = start;
     max = start+end/2;
   }
   // System.out.println(min + " " +  med + " " + max);
   int x = med;
   //swapping target to the front
   int target = data[x];
   data[x] = data[start];
   data[start] = target;

   //swapping in general
   if (start!= end) {
     start+=1;
   }
   while (start != end) {
     int equal = -1;
     if (data[start] == target) {
       Random r = new Random();
       equal = r.nextInt(2);
     }
     if (data[start] > target || equal == 0) {
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


 public static void main(String[]args){
  System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
  int[]MAX_LIST = {1000000000,500,10};
  for(int MAX : MAX_LIST){
    for(int size = 31250; size < 2000001; size*=2){
      long qtime=0;
      long btime=0;
      //average of 5 sorts.
      for(int trial = 0 ; trial <=5; trial++){
        int []data1 = new int[size];
        int []data2 = new int[size];
        for(int i = 0; i < data1.length; i++){
          data1[i] = (int)(Math.random()*MAX);
          data2[i] = data1[i];
        }
        long t1,t2;
        t1 = System.currentTimeMillis();
        Quick.quicksort(data2);
        t2 = System.currentTimeMillis();
        qtime += t2 - t1;
        t1 = System.currentTimeMillis();
        Arrays.sort(data1);
        t2 = System.currentTimeMillis();
        btime+= t2 - t1;
        if(!Arrays.equals(data1,data2)){
          System.out.println("FAIL TO SORT!");
          System.exit(0);
        }
      }
      System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
    }
    System.out.println();
  }
}


}
