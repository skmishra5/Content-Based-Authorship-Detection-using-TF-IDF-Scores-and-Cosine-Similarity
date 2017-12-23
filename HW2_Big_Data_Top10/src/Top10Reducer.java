import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class Top10Reducer extends Reducer<Text,Text,Text,Text>{
	
//	final TreeMap<Double, String> tree = new TreeMap<Double, String>(new Comparator<Double>() {
//
//		@Override
//		public int compare(Double o1, Double o2) {
//			// TODO Auto-generated method stub
//			return o2.compareTo(o1);
//		}
//	});
	ArrayList list = new ArrayList();
	Text word = new Text();
	public void reduce(Text key, Iterable<Text> values, 
            Context context
            ) throws IOException, InterruptedException {
		
//			String[] parse = values.toString().split("\t");
////			if(tree.containsKey(Double.parseDouble(values.toString())))
//					tree.put(Double.parseDouble(values.toString()), key.toString());	
////			else
////				tree.put(Double.parseDouble(values.toString()), key.toString());
//			Iterator ittwo = tree.entrySet().iterator();
//			while (ittwo.hasNext()) {
//				Map.Entry pairs = (Map.Entry)ittwo.next();
//				
//			}
		for(Text val : values){
			word.set(val.toString());
		}
		
//		for(int i = 0; i < 10; i++)
//		{
			context.write(key, new Text(word));
		//}	
		
	}
}
