import java.util.*;
import java.io.*;

public class HeapSort {

    public static void main(String[] args) throws IOException {
    	Scanner sc = new Scanner(new File(args[0]));
    	ArrayList<String> data = new ArrayList<String>();
    	while (sc.hasNext()) data.add(sc.next());

      Heap<String> heap = new Heap<String>(data.size());   //heap sort is a very simple sorting algorithm since it rellies on the ordered nature of the heap
      for (String obj : data){       //first we add all the elements to the heap
        heap.insert(obj);
      }

      while(!heap.isEmpty()) {     //then we remove them till the heap is empty. The output method can change though, this one is very simple
        System.out.println(heap.removeMin());
      }

      //System.out.println(heap.toString());

    }


}
//
// takes a filename from the command line
// and outputs that file sorted, one word per line
//
