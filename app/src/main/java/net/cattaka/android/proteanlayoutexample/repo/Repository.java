package net.cattaka.android.proteanlayoutexample.repo;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.cattaka.android.proteanlayoutexample.data.CatEntries;
import net.cattaka.android.proteanlayoutexample.data.CatEntry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cattaka on 17/02/23.
 */

public class Repository {
    private Context mContext;

    public Repository(@NonNull Context context) {
        mContext = context;
    }

    @NonNull
    public List<CatEntry> loadCatEntries() {
        try {
            AssetManager assetManager = mContext.getAssets();
            ObjectMapper objectMapper = new ObjectMapper();
            CatEntries catEntries = objectMapper.readValue(assetManager.open("data/data.json"), CatEntries.class);
            return catEntries.getItems();
        } catch (IOException e) {
            throw new RuntimeException("Program error", e);
        }
    }

    public List<CatEntry> findByColor(String color) {
        List<CatEntry> results = new ArrayList<>();
        List<CatEntry> items = loadCatEntries();
        for (CatEntry item : items) {
            if ((color == null && item.getColor() == null)
                    || (color != null && color.equals(item.getColor()))) {
                results.add(item);
            }
        }
        return results;
    }

    public List<CatEntry> findByHair(String hair) {
        List<CatEntry> results = new ArrayList<>();
        List<CatEntry> items = loadCatEntries();
        for (CatEntry item : items) {
            if ((hair == null && item.getHair() == null)
                    || (hair != null && hair.equals(item.getHair()))) {
                results.add(item);
            }
        }
        return results;
    }
}
