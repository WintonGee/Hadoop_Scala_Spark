import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SaleMapper extends Mapper<LongWritable, Text, YMDTSalePair, Text> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        // - So what I need to do is change up for token indexes because they are switched up
        // - This is the input: 1,2023/04/21,23:51:47,2,2
        String[] tokens = line.trim().split(",");
        String yearMonthDay = tokens[1];
        String timeSales = tokens[2] + " " + tokens[0];
        context.write(new YMDTSalePair(yearMonthDay, timeSales), new Text(timeSales));
    }

}
