import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class UnknownAuthorMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private Text word = new Text();
	private String author = new String("");
	String buf = "";
	private final static IntWritable one = new IntWritable(1);
	
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		
		String line = "";
		String sentence = "";
		line  = value.toString();
		String[] token;
		
		if(line.contains("<===>")) {			
				token = (value.toString()).split("<===>");  				
				sentence=token[1].toString().toLowerCase().replaceAll("[^a-zA-Z]"," ");	   				
				StringTokenizer itr = new StringTokenizer(sentence);
				
				while (itr.hasMoreTokens())
				{
					String tkn = itr.nextToken().replaceAll("[^a-zA-Z]","");
					
					if(tkn.length() != 0) {
						buf = "Unknown" + "\t" + tkn;
						word.set(buf);
						context.write(word, one);
					}			
				}		
		}
	}
}
