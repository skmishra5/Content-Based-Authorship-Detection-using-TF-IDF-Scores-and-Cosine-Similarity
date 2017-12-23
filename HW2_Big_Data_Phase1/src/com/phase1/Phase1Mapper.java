package com.phase1;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class Phase1Mapper extends Mapper<LongWritable, Text, Text, IntWritable>{
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
				author = token[0].toString().toLowerCase().replaceAll("[^a-zA-Z]"," ");  				
				sentence=token[1].toString().toLowerCase().replaceAll("[^a-zA-Z]"," ");	   				
				StringTokenizer itr = new StringTokenizer(sentence);
				
				while (itr.hasMoreTokens())
				{
					String tkn = itr.nextToken().replaceAll("[^a-zA-Z]","");
					
					if(tkn.length() != 0) {
						buf = author + "\t" + tkn;
						word.set(buf);
						context.write(word, one);
					}			
				}		
		}
		else{
			line =  line.toLowerCase().replaceAll("[^a-zA-Z]"," ");
			StringTokenizer itr = new StringTokenizer(line);
			
			while (itr.hasMoreTokens())
			{
				String tkn = itr.nextToken().replaceAll("[^a-zA-Z]","");
				
				if(tkn.length() != 0) {
					buf = author + "\t" + tkn;
					//Buffer = substring;
					word.set(buf);
					context.write(word, one);
				}			
			}		
		}

	}	    

}
