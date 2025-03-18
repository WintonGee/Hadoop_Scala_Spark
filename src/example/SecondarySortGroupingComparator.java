package example;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SecondarySortGroupingComparator extends WritableComparator {

    public SecondarySortGroupingComparator() {
        super(YMTemperaturePair.class, true);
    }

    @Override
    public int compare(WritableComparable wc1, WritableComparable wc2) {
        YMTemperaturePair pair = (YMTemperaturePair) wc1;
        YMTemperaturePair pair2 = (YMTemperaturePair) wc2;
        return pair.getYearMonth().compareTo(pair2.getYearMonth());
    }
}

