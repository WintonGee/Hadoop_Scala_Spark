import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SalesReducer extends Reducer<Text, SalesCountPair, Text, IntWritable> {

    @Override
    public void reduce(Text date, Iterable<SalesCountPair> temperatures, Context context)
            throws IOException, InterruptedException {
        int sum = 0;
        for (SalesCountPair el : temperatures) {
            sum += el.getSum();
        }
        // Date, Number of sales
        context.write(date, new IntWritable(sum));
    }
}
