import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class UnTFIDFReducer extends Reducer<Text,Text,Text,Text>{
	private static float maximum = 0;
	public void reduce(Text key, Iterable<Text> values,
            Context context
            ) throws IOException, InterruptedException {

		
		ArrayList<String> ValueList = new ArrayList<String>(); 
		
		for (Text val : values) {
			ValueList.add(val.toString());
			float count = Float.parseFloat(val.toString().split("\t")[1]);
			if (count > maximum){
				maximum = count;
			}	
		}
		for(String val : ValueList){
			String[] valsplit = val.split("\t");
					
			float tf = Float.parseFloat(valsplit[1])/(float)maximum;
			
			context.write(key, new Text(valsplit[0] + "\t" + tf));
		}
	}
}
