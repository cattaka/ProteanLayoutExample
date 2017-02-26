package net.cattaka.android.proteanlayoutexample.data;

/**
 * Created by cattaka on 17/02/26.
 */

public class AggregatedEntry {
    private String key;
    private int count;

    public AggregatedEntry() {
    }

    public AggregatedEntry(String key, int count) {
        this.key = key;
        this.count = count;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
