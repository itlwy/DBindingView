package com.lwy.dbindingview.app.base_widget;

import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.Toast;

import com.lwy.dbindingview.app.MyApplication;
import com.lwy.dbindingview.app.base.IView;
import com.lwy.dbindingview.app.utils.JsonUtils;
import com.lwy.dbindingview.command.ReplyCommand;
import com.lwy.dbindingview.command.functions.Action0;
import com.lwy.dbindingview.data.KeyValue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
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
    public final KeyValue sex_male;
    public final KeyValue sex_female;
    private final WeakReference<IView> mView;

    public JSONObject streetJson;

    public final ObservableField<KeyValue> sex = new ObservableField<>();

    public final ObservableArrayList<KeyValue> sexList = new ObservableArrayList<>();

//    public String cbSeparator = "@";

    public final ObservableArrayList<KeyValue> hobbys = new ObservableArrayList<>();
    public final ObservableArrayList<KeyValue> hobby = new ObservableArrayList<>();

    public final ObservableField<String> imageUri = new ObservableField<>();

    public ReplyCommand checkCommand = new ReplyCommand(new Action0() {
        @Override
        public void call() {

            Toast.makeText(MyApplication.getMyApplication(), "点击了查看", Toast.LENGTH_SHORT).show();
        }
    });


    public WidgetVM(IView view) {
        mView = new WeakReference<IView>(view);
        init();
        sex_male = new KeyValue(1, "男");
        sex_female = new KeyValue(0, "女");
        sexList.add(sex_male);
        sexList.add(sex_female);
        sex.set(sex_female);

        KeyValue hb1 = new KeyValue(1, "看书");
        KeyValue hb2 = new KeyValue(2, "看电影");
        KeyValue hb3 = new KeyValue(3, "敲代码");
        hobbys.add(hb1);
        hobbys.add(hb2);
        hobbys.add(hb3);
    }

    private void init() {
        try {
            imageUri.set("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=487963228,3094339171&fm=27&gp=0.jpg");

            String areaStr = JsonUtils.getJsonFromAss(MyApplication.getMyApplication(), "area.json");
            streetJson = new JSONObject(areaStr);
            Iterator<String> iter = streetJson.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                KeyValue keyValue = new KeyValue(areaList.size() + 1, key);
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
                KeyValue keyValue = new KeyValue(selectedArea.get().key * 100 + streetList.size(), streetArray.getString(i));
                tempArrayList.add(keyValue);
            }
            streetList.addAll(tempArrayList);
        }
    }
}
