import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SaleReducer extends Reducer<YMDTSalePair, Text, Text, Text> {

    @Override
    protected void reduce(YMDTSalePair key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
        String result = "";
        for (Text value : values) {
            result += (value + ",");
        }
        result = result.substring(0, result.length() - 1);
        context.write(key.getYearMonthDay(), new Text(result));
    }
}

