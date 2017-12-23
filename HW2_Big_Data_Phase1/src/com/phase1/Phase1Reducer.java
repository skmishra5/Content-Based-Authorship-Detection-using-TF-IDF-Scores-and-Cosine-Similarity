package com.phase1;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;


public class Phase1Reducer extends Reducer<Text,IntWritable,Text,IntWritable>{
	private IntWritable res = new IntWritable();

	public void reduce(Text key, Iterable<IntWritable> values,
	                Context context
	                ) throws IOException, InterruptedException {
	int sum = 0;
	for (IntWritable val : values) {
	 sum += val.get();
	}
	res.set(sum);
	context.write(key, res);
	}
}
