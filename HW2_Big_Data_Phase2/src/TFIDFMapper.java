import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;

public class TFIDFMapper extends Mapper<LongWritable, Text, Text, Text>{
	
//	Path[] cachefiles = new Path[0];
//	Path[] cachefiles1 = new Path[1];
//	public static int authCount;
//	public static int ni;
//	int i = 0;
//	ArrayList<Integer> list = new ArrayList<Integer>();
//	@SuppressWarnings("deprecation")
//	@Override
//	  public void setup(Context context) 
//	  {
//	      Configuration conf = context.getConfiguration();
//	      Configuration conf1 = context.getConfiguration();
//	   
//	      try 
//	      {
//	    	  cachefiles = DistributedCache.getLocalCacheFiles(conf);
//	    	  BufferedReader reader = new BufferedReader(new FileReader(cachefiles[0].toString())); 
//	    
//	    	  String line;
//	    	  String[] token = new String[3];
//	    	  while ((line = reader.readLine())!= null) 
//	    	  {
//	    		  token = line.split("\t");
//	    	  }
//	    	  token[1].trim();
//	    	  authCount = Integer.parseInt(token[1]);
//	    	  
//	    	  cachefiles1 = DistributedCache.getLocalCacheFiles(conf1);
//	    	  BufferedReader reader1 = new BufferedReader(new FileReader(cachefiles1[1].toString())); 
//	    	  
//	    	  String line1;
//	    	  String[] token1 = new String[3];
//	    	  
//	    	  while ((line1 = reader1.readLine())!= null) 
//	    	  {
//	    		  token1 = line1.split("\t");
//	    		  token1[1].trim();
//	    		  ni = Integer.parseInt(token1[1]);
//	    		  list.add(ni);
//	    	  }
//	    	  //token1[1].trim();
//	    	  
//	    	  
//	      }
//	   
//	      catch (IOException e) 
//	      {
//	    	  e.printStackTrace();
//	      }
//
//	   } 
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String[] parse = value.toString().split("\t");
		//int Ni = list.get(i);
		//context.write(new Text(parse[1]), new Text(parse[0] + "\t " + parse[2] + "\t" + authCount + "\t" + Ni));
		context.write(new Text(parse[1]), new Text(parse[0] + "\t " + parse[2]));
		//i++;
		//if(i == list.size())
			//i = 0;
	}
	
	
}
