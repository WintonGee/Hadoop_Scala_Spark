package example;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SecondarySortMapper extends Mapper<LongWritable, Text, YMTemperaturePair, IntWritable> {

    // TODO 2017, 1, 1, 6:23:11, 43

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        // 2005, 08, 23, 70
        String[] tokens = line.split(",");
        String yearMonth = tokens[0].trim() + "-" + tokens[1].trim();
        int temperature = Integer.parseInt(tokens[3].trim());
        context.write(new YMTemperaturePair(yearMonth, temperature), new IntWritable(temperature));
    }

}
