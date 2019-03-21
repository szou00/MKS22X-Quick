import java.util.Random;
import java.util.Arrays;

public class Quick {

  public static void insertionsort(int[] data, int lo,int hi) {
    if (lo >= hi) {
      return; //base case
    }
    int current = 0;
    for (int i = 0; i < data.length; i++) { //goes thru the list
      current = data[i];
      //compound boolean
      int x = i - 1;
      while (x > -1 && data[x] > current) { //finds the index that value should be
        data[x + 1] = data[x];
        data[x] = current;
        x -= 1;
      }
    }
  }

  /*return the value that is the kth smallest value of the array.
 */
 public static int quickselect(int []data, int k){
   int p = partition(data,0,data.length-1); //partitions it once
   while (k!=p) { //while it's not the index that's wanted
     if (p < k) { //if it's lower than the index wanted
       p = partition(data,p+1,data.length-1); //look through the values above current index
     }
     if (p > k) { //if it's lower than index that's wanted
       p = partition(data,0,p-1); //look through the values below current index
     }
   }
   return data[p];
 }

   /*Modify the array to be in increasing order.
   */
   public static void quicksort(int[] data) {
     quicksort(data,0,data.length-1);
   }

   //helper method
   public static void quicksort(int[] data, int lo, int hi) {
     if (lo < hi) { //the only way something would happen
       int pivot = partition(data,lo,hi); //partitions it once
       quicksort(data,pivot+1,hi); //calls itself to partition the two different parts of the array
       quicksort(data,lo,pivot-1); //below the index and above the index
     }
   }



 public static int partition ( int [] data, int start, int end){
   //keeping track of the original start
   int s = start;

   //choosing the median of the lowest, highest, and middle elements
   int med = 0;
   //the middle value is the median
   if (data[(start+end)/2] <= data[start] && data[(start+end)/2] >= data[end] || data[(start+end)/2] >= data[start] && data[(start+end)/2] <= data[end]) {
     med = (start+end)/2;
   }
   //the end value is the median
   else if (data[end] >= data[start] && data[end] <= data[(start+end)/2] || data[end] <= data[start] && data[end] >= data[(start+end)/2]) {
     med = end;
   }
   //the first value is the median
   else {
     med = start;
   }

   //swapping target to the front
   int target = data[med]; //target stores the median
   data[med] = data[start]; //the index of median gets the value that's at the beginning
   data[start] = target; //the beginning gets the target value

   //swapping in general
   if (start!= end) {
     start+=1;
   }
   while (start != end) { //while start is not equal to end
     int equal = -1; //initializes this
     if (data[start] == target) { //if it's equal
       Random r = new Random();
       equal = r.nextInt(2); //randomly chooses to go to the front or back
     }
     if (data[start] > target || equal == 0) { //if it's greater than target or is equal to 0
       int temp = data[end]; //swaps to the end
       data[end] = data[start];
       data[start] = temp;
       end-=1;
     }
     else {
       start+=1; //otherwise start is incremented
     }
   }

   //swapping pivot back
   if (data[start] > target) { //if the current value is greater than target
     data[s] = data[start-1]; //swap target back with the value in front of the current value
     data[start-1] = target;
     start -= 1;
   }
   else {
     data[s] = data[start]; //otherwise just swaps it with the current value
     data[start] = target;
   }
   return start;
 }

//testing from k's website
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
