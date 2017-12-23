import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class phase1IDFReducer extends Reducer<Text,Text,Text,DoubleWritable>{

	DoubleWritable res = new DoubleWritable();
	
	public void reduce(Text key, Iterable<Text> values,
            Context context
            ) throws IOException, InterruptedException {

		ArrayList<String> ValueList = new ArrayList<String>(); 
		
		for (Text val : values) {
			ValueList.add(val.toString());	
		}
		
		for(String val : ValueList){
			String[] valsplit = val.split("\t");
			double ni = Double.parseDouble(valsplit[0]);
			double N =  Double.parseDouble(valsplit[1])/ni;			
			double idf = Math.log10(N) / Math.log10(2);
			res.set(idf);
			context.write(key, res);
		}
	}
}
