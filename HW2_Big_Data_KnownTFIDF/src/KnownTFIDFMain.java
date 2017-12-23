import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class KnownTFIDFMain {
	public static void main(String[] args) throws Exception{
		if (args.length != 3) {
		      System.out.printf("Usage: <jar file> <Author count file > <ni> <input dir> <output dir>\n");
		      System.exit(-1);
		    }
		
		Configuration conf =new Configuration();
		
		Job job=Job.getInstance(conf);
		job.setJarByClass(KnownTFIDFMain.class);
		
		job.setMapperClass(KnownTFIDFMapper.class);
		job.setReducerClass(KnownTFIDFReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		//DistributedCache.addCacheFile(new Path(args[0]).toUri(), job.getConfiguration());
		//DistributedCache.addCacheFile(new Path(args[1]).toUri(), job.getConfiguration());
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileInputFormat.addInputPath(job, new Path(args[1]));
		FileOutputFormat.setOutputPath(job, new Path(args[2]));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
