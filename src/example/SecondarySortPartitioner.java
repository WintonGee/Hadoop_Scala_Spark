package example;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

// < key, value >
public class SecondarySortPartitioner extends Partitioner<YMTemperaturePair, IntWritable> {

    @Override
    public int getPartition(YMTemperaturePair pair, IntWritable temperature, int numberOfPartitions) {
        return Math.abs(pair.getYearMonth().hashCode() % numberOfPartitions);
    }

}
