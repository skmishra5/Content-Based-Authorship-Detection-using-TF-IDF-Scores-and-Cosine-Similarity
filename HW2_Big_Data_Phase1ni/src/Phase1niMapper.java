import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class Phase1niMapper extends Mapper<LongWritable, Text, Text, Text>{
	private Text word = new Text();
	//private String author = new String("");
	String buf = "";
	private final static IntWritable one = new IntWritable(1);
	
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String author = new String("");
		String line = "";
		String sentence = "";
		line  = value.toString();
		String[] token;
		
		if(line.contains("<===>")) {			
				token = (value.toString()).split("<===>");
				author = token[0].toString().toLowerCase().replaceAll("[^a-zA-Z]"," ");  				
				sentence=token[1].toString().toLowerCase().replaceAll("[^a-zA-Z]"," ");	   				
				StringTokenizer itr = new StringTokenizer(sentence);
				
				while (itr.hasMoreElements())
				{
					String tkn = (String)itr.nextElement();
					tkn = tkn.replaceAll("[^a-zA-Z]","");
					
					if(tkn.trim().length() != 0) {
						
						context.write(new Text(tkn), new Text(author));
					}			
				}		
		}
	}
}
