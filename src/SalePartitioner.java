import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

// < key, value >
public class SalePartitioner extends Partitioner<YMDTSalePair, Text> {

    @Override
    public int getPartition(YMDTSalePair pair, Text timeSales, int numberOfPartitions) {
        return Math.abs(pair.getYearMonthDay().hashCode() % numberOfPartitions);
    }
}
