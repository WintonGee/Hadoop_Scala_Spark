import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

// key should be year,month,day
// the value should be a custom object-> time:sales
public class YMDTSalePair implements Writable, WritableComparable<YMDTSalePair> {

    private final Text yearMonthDay = new Text();
    private final Text timeSales = new Text();

    public YMDTSalePair() {
    }

    public YMDTSalePair(String yearMonth, String timeSales) {
        this.yearMonthDay.set(yearMonth);
        this.timeSales.set(timeSales);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        yearMonthDay.write(out);
        timeSales.write(out);
    }


    @Override
    public void readFields(DataInput in) throws IOException {
        yearMonthDay.readFields(in);
        timeSales.readFields(in);
    }

    @Override
    public int compareTo(YMDTSalePair pair) {
        if (yearMonthDay.compareTo(pair.getYearMonthDay()) == 0) {
            return timeSales.compareTo(pair.timeSales);
        }
        return yearMonthDay.compareTo(pair.getYearMonthDay());
    }

    public Text getYearMonthDay() {
        return yearMonthDay;
    }

    public Text getTimeSales() {
        return timeSales;
    }

}

