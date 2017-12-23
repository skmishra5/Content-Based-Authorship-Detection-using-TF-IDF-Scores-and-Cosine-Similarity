import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class TFIDFReducer extends Reducer<Text,Text,Text,Text>{
	private double maximum = 0.0d;
	
	
	public void reduce(Text key, Iterable<Text> values,
            Context context
            ) throws IOException, InterruptedException {

		ArrayList<String> ValueList = new ArrayList<String>(); 
		
		for (Text val : values) {
			ValueList.add(val.toString());
			double count = Double.parseDouble(val.toString().split("\t")[1]);
			if (count > maximum){
				maximum = count;
			}	
		}
		for(String val : ValueList){
			String[] valsplit = val.split("\t");
			//double ni = Double.parseDouble(valsplit[3]);
			//double N =  Double.parseDouble(valsplit[2])/ni;
					
			//double idf = Math.log10(N) / Math.log10(2);
			double tf = (Double.parseDouble(valsplit[1])/(double)maximum);
			//double tfidf = tf * idf;
			
			//context.write(key, new Text(valsplit[0] + "\t" + tf +"\t" +idf + "\t" + tfidf));
			context.write(key, new Text(valsplit[0] + "\t" + tf));
		}
	}
}
