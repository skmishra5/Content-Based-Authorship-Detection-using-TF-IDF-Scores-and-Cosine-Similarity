import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class CosineCalReducer extends Reducer<Text, Text,Text, Text>{
	double dProduct = 0.0d;
	double nA = 0.0d;
	double nB = 0.0d;
	double VectorA = 0.0d;
	double VectorB = 0.0d;
	double cosineValue = 0.0d;
	//private DoubleWritable DW = new DoubleWritable();
	private Text DW = new Text();
	
	public void reduce(Text key, Iterable<Text> values,
            Context context
            ) throws IOException, InterruptedException {
		
		for (Text val : values) {
			String[] parse = val.toString().split("\t");
			VectorA = Double.parseDouble(parse[1]);
			VectorB = Double.parseDouble(parse[2]);
			dProduct +=  VectorA * VectorB;
			nA += Math.pow(VectorA, 2);
			nB += Math.pow(VectorB, 2);
		}	
		cosineValue = dProduct / (Math.sqrt(nA) * Math.sqrt(nB));
		String buf = new Double(cosineValue).toString();
		DW.set(buf);
		context.write(key, new Text(buf));
	}
}
