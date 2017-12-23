import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class AuthorCountReducer extends Reducer<Text,Text, Text, Text>{
	
	private Text word = new Text();
	String buf ="AuthorCount:";
	String buf1 = "";
	Set<String> s = new TreeSet<String>();

	public void reduce(Text key, Iterable<Text> values,
	                Context context
	                ) throws IOException, InterruptedException {
	for (Text val : values) {
		s.add(val.toString());
	}
	buf1 = Integer.toString(s.size());
	word.set(buf);
	context.write(word, new Text(buf1));
	}
}
