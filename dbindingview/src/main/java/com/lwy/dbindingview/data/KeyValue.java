package com.lwy.dbindingview.data;

import java.io.Serializable;

/**
 * Created by mac on 2017/11/15.
 */

public class KeyValue implements Serializable {
    static final long serialVersionUID = 1L;

    public Integer key = -1;
    public String value = "";

    public KeyValue(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public KeyValue(String value) {
        this.value = value;
    }

    public KeyValue(Integer key) {
        this.key = key;
    }

    public KeyValue() {

    }
}
