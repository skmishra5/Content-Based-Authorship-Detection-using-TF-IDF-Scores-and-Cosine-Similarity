import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class KnownTFIDFReducer extends Reducer<Text,Text,Text,Text>{
	
	public void reduce(Text key, Iterable<Text> values,
            Context context
            ) throws IOException, InterruptedException {
		
		ArrayList<String> ValueList = new ArrayList<String>();
		double idf = 0.0d;
		for (Text val : values) {
			String[] valsplit = val.toString().split("\t");
			if(!(valsplit[1].equals("$")))
			{
				String authorTF = valsplit[0] + "\t" + valsplit[1];
				ValueList.add(authorTF);
			}
			else
			{
				idf = Double.parseDouble(valsplit[0]);
			}
		}
		
		for(String val : ValueList)
		{
			String[] valsplit1 = val.toString().split("\t");
			double tfidf = idf * (Double.parseDouble(valsplit1[1]));
			context.write(key, new Text(valsplit1[0] + "\t" + idf + "\t" + tfidf));
		}	
	}
}
