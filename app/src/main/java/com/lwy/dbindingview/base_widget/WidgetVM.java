package com.lwy.dbindingview.base_widget;

import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.Toast;

import com.lwy.dbindingview.MyApplication;
import com.lwy.dbindingview.base.IView;
import com.lwy.dbindingview.command.ReplyCommand;
import com.lwy.dbindingview.command.functions.Action0;
import com.lwy.dbindingview.data.KeyValue;
import com.lwy.dbindingview.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lwy on 2018/4/23.
 */

public class WidgetVM {
    public static final String TAG = "WidgetVM";
//    private WeakReference<IView> mView;

    public final ObservableField<String> address = new ObservableField<>();

    public final ObservableArrayList<KeyValue> areaList = new ObservableArrayList<>();
    public final ObservableField<KeyValue> selectedArea = new ObservableField<>();

    public final ObservableArrayList<KeyValue> streetList = new ObservableArrayList<>();
    public final ObservableField<KeyValue> selectedStreet = new ObservableField<>();

    public JSONObject streetJson;

    public final ObservableField<KeyValue> sex = new ObservableField<>();

    public String cbSeparator = "@";

    public final ObservableField<String> hobby = new ObservableField<>();

    public final ObservableField<String> imageUri = new ObservableField<>();

    public ReplyCommand checkCommand = new ReplyCommand(new Action0() {
        @Override
        public void call() {
            Toast.makeText(MyApplication.getMyApplication(), "点击了查看", Toast.LENGTH_SHORT).show();
        }
    });


    public WidgetVM(IView view) {
//        mView = new WeakReference<IView>(view);
        init();
    }

    private void init() {
        try {
            imageUri.set("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=487963228,3094339171&fm=27&gp=0.jpg");

            String areaStr = JsonUtils.getJsonFromAss(MyApplication.getMyApplication(), "area.json");
            streetJson = new JSONObject(areaStr);
            Iterator<String> iter = streetJson.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                KeyValue keyValue = new KeyValue(areaList.size(), key);
                areaList.add(keyValue);
            }
            initObserver();
        } catch (JSONException e) {
            Log.d(TAG, e.toString());
        }

    }

    private void initObserver() {
        // to refresh second item below this
        selectedArea.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                try {
                    refreshStreetSpinner();
                } catch (JSONException e) {
                    Log.d(TAG, e.toString());
                }
            }
        });
    }

    private void refreshStreetSpinner() throws JSONException {
        JSONArray streetArray = streetJson.getJSONArray(selectedArea.get().value);
        streetList.clear();
        if (streetArray != null) {
            List<KeyValue> tempArrayList = new ArrayList<>();
            for (int i = 0; i < streetArray.length(); i++) {
                KeyValue keyValue = new KeyValue(streetList.size(), streetArray.getString(i));
                tempArrayList.add(keyValue);
            }
            streetList.addAll(tempArrayList);
        }
    }
}
