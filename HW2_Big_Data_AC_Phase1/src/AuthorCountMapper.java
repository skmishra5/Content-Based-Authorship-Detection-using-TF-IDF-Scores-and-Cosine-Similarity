import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class AuthorCountMapper extends Mapper<LongWritable, Text, Text, Text>{
	private Text word = new Text();
	private Text word1 = new Text();
	private String author = new String("");
	String buf = "";
	private final static IntWritable one = new IntWritable(1);
	
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		
		String line = "";
		line  = value.toString();
		String[] token;
		
		if(line.contains("<===>")) {			
				token = (value.toString()).split("<===>");
				author = token[0].toString().toLowerCase().replaceAll("[^a-zA-Z]"," ");
				buf = "one";
				word1.set(buf);
				word.set(author);
				context.write(new Text(buf), new Text(author));	   				
		}			
	}
}
	
