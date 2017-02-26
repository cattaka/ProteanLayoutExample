package net.cattaka.android.proteanlayoutexample.repo;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.cattaka.android.proteanlayoutexample.data.AggregatedEntry;
import net.cattaka.android.proteanlayoutexample.data.CatEntries;
import net.cattaka.android.proteanlayoutexample.data.CatEntry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by cattaka on 17/02/23.
 */

public class Repository {
    private static Repository mRepository;
    private List<CatEntry> mCatEntries;

    Repository() {
    }

    public static synchronized Repository getInstance(Context context) {
        // このサンプルアプリの本題ではないので雑な実装
        if (mRepository == null) {
            mRepository = new Repository();
            mRepository.mCatEntries = Collections.unmodifiableList(loadCatEntries(context));
        }
        return mRepository;
    }

    @NonNull
    private static List<CatEntry> loadCatEntries(Context context) {
        try {
            AssetManager assetManager = context.getAssets();
            ObjectMapper objectMapper = new ObjectMapper();
            CatEntries catEntries = objectMapper.readValue(assetManager.open("data/data.json"), CatEntries.class);
            return catEntries.getItems();
        } catch (IOException e) {
            throw new RuntimeException("Program error", e);
        }
    }

    public List<CatEntry> findByColor(String color) {
        List<CatEntry> results = new ArrayList<>();
        for (CatEntry item : mCatEntries) {
            if ((color == null && item.getColor() == null)
                    || (color != null && color.equals(item.getColor()))) {
                results.add(item);
            }
        }
        return results;
    }

    public List<CatEntry> findByHair(String hair) {
        List<CatEntry> results = new ArrayList<>();
        for (CatEntry item : mCatEntries) {
            if ((hair == null && item.getHair() == null)
                    || (hair != null && hair.equals(item.getHair()))) {
                results.add(item);
            }
        }
        return results;
    }

    public static List<CatEntry> removeByName(List<CatEntry> inOut, String name) {
        for (Iterator<CatEntry> itr = inOut.iterator(); itr.hasNext(); ) {
            CatEntry item = itr.next();
            if ((name == null && item.getName() == null)
                    || (name != null && name.equals(item.getName()))) {
                itr.remove();
            }
        }
        return inOut;
    }

    public List<CatEntry> getCatEntries() {
        return mCatEntries;
    }

    public List<AggregatedEntry> aggregateByColor() {
        Map<String, Integer> values = new TreeMap<>();
        for (CatEntry item : mCatEntries) {
            String key = item.getColor();
            Integer value = values.get(item.getColor());
            if (value == null) {
                value = 0;
            }
            values.put(key, value + 1);
        }
        List<AggregatedEntry> results = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : values.entrySet()) {
            results.add(new AggregatedEntry(entry.getKey(), entry.getValue()));
        }
        return results;
    }
}
