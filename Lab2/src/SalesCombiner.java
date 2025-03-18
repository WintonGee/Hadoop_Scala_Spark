import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SalesCombiner
        extends Reducer<Text, SalesCountPair, Text, SalesCountPair> {
    @Override
    public void reduce(Text key, Iterable<SalesCountPair> values, Context context)
            throws IOException, InterruptedException {
        int sum = 0;
        int count = 0;
        for (SalesCountPair el : values) {
            sum += el.getSum();
            count += el.getCount();
        }
        context.write(key, new SalesCountPair(sum, count));
    }
}
