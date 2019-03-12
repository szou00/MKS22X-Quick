import java.util.Random;

public class Quick {

  /*return the value that is the kth smallest value of the array.
  */
  public static int quickselect(int []data, int k){
    int p = partition(data,0,data.length);
    while (k!=p) {
      p = partition(data,0,data.length);
    }
    return k;
  }

  public static int partition ( int [] data, int start, int end){
    Random rand = new Random();
    int s = start;
    int x = rand.nextInt(end+1-start) + start;
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

  public static void main(String[] args) {
    int[] data = {17,61,67,47,93,12,20,4,44,68};
    System.out.println(quickselect(data,4));
  }
}
