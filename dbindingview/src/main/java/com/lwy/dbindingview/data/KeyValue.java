package com.lwy.dbindingview.data;

import java.io.Serializable;

/**
 * Created by lwy on 2017/11/15.
 */

public final class KeyValue implements Serializable {
    static final long serialVersionUID = 1L;

    public final Integer key;
    public final String value;

    public KeyValue(int key, String value) {
        this.key = key;
        this.value = value;
    }

//    public KeyValue(String value) {
//        this.value = value;
//    }
//
//    public KeyValue(Integer key) {
//        this.key = key;
//    }

//    public KeyValue() {
//
//    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof KeyValue))
            return false;
        KeyValue kv2 = (KeyValue) obj;
        if (this.key.equals(kv2.key) && this.value.equals(kv2.value))
            return true;
        else
            return false;
    }
}
