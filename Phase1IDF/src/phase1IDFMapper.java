import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;

public class phase1IDFMapper extends Mapper<LongWritable, Text, Text, Text>{
	
//	Path[] cachefiles = new Path[0];
	public int authCount = 1700;
	
//	@Override
//    public void setup(Context context) 
//    {
//		Configuration conf = context.getConfiguration();
//		
//		try 
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
//    	}
//		catch (IOException e) 
//	    {
//	        e.printStackTrace();
//	    }
//    }
	
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {	
		String[] parse = value.toString().split("\t");
		context.write(new Text(parse[0]), new Text(parse[1] + "\t" + authCount));
	}
}
