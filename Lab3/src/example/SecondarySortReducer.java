package example;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SecondarySortReducer extends Reducer<YMTemperaturePair, IntWritable, Text, Text> {

    @Override
    protected void reduce(YMTemperaturePair key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
        String result = "";
        for (IntWritable value : values) {
            result += (value.toString() + ",");
        }
        result = result.substring(0, result.length() - 1);
        context.write(key.getYearMonth(), new Text(result));
    }
}

// Creates result of the sort:   2001-11	40,46,47,48


