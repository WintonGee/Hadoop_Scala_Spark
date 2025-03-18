import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SalesMapper extends Mapper<LongWritable, Text, Text, SalesCountPair> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String valueAsString = value.toString().trim();
        String[] tokens = valueAsString.split(",");
        if (tokens.length < 2) {
            return;
        }
        // This is the expected input: 1,2023/04/21,23:51:47,2,2
        context.write(new Text(tokens[1]), new SalesCountPair(1,1));
    }

}
