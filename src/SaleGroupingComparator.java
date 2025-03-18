import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SaleGroupingComparator extends WritableComparator {

    public SaleGroupingComparator() {
        super(YMDTSalePair.class, true);
    }

    @Override
    public int compare(WritableComparable wc1, WritableComparable wc2) {
        YMDTSalePair pair = (YMDTSalePair) wc1;
        YMDTSalePair pair2 = (YMDTSalePair) wc2;
        return pair.getYearMonthDay().compareTo(pair2.getYearMonthDay());
    }
}

