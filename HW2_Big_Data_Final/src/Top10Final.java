import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Top10Final {
	public static void main(String[] args) throws Exception{
		BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		ArrayList list = new ArrayList();
		 String line;
     	  while ((line = reader.readLine())!= null) 
     	  {
     	      list.add(line);
     	  }
     	  int temp = list.size()-11;
     	  for(int i = list.size() -1; i > temp; i--)
     	  {
     		  System.out.println(list.get(i).toString());
     	  }
	}
}
