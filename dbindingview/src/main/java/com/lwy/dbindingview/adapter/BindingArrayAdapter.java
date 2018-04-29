package com.lwy.dbindingview.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.lwy.dbindingview.data.KeyValue;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lwy on 2017/11/20.
 */

public class BindingArrayAdapter extends ArrayAdapter<String> {

    private int resoureId;
    private List<KeyValue> objects;
    private Context context;


    public BindingArrayAdapter(Context context, int resourceId, List<KeyValue> objects) {
        super(context, resourceId, initValueArray(objects));
        this.objects = new ArrayList<>();
        for (KeyValue object : objects) {
            this.objects.add(object);
        }
        this.context = context;

    }

    public List<KeyValue> getObjects() {
        return objects;
    }

    @Override
    public void clear() {
        this.objects.clear();
        super.clear();
    }

    public void addAll(List<KeyValue> objects) {
        this.objects.addAll(objects);
        super.addAll(initValueArray(objects));
    }

    private static List<String> initValueArray(List<KeyValue> objects) {
        List<String> showItems = new ArrayList<>();
        for (KeyValue item : objects) {
            showItems.add(item.value);
        }
        return showItems;
    }

}
