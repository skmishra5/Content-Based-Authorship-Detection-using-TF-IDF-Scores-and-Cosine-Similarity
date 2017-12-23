import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class Phase1niReducer extends Reducer<Text, Text,Text,IntWritable>{
	//private IntWritable res = new IntWritable();
	
	
	String f = "";
	
	public void reduce(Text key, Iterable<Text> values,
	                Context context
	                ) throws IOException, InterruptedException {
	int sum = 0;
	Set<String> s = new TreeSet<String>();
	for (Text val : values) {
		s.add(val.toString());
		sum += 1 ;
	}
	//f = s.toString() + sum;
	//res.set(sum);
	context.write(key, new IntWritable(s.size()));
	}
}
