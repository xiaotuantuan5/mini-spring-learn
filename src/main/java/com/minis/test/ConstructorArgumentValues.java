package com.minis.test;

import com.minis.beans.factory.config.ConstructorArgumentValue;

import java.util.ArrayList;
import java.util.List;

public class ConstructorArgumentValues {
    private final List<ConstructorArgumentValue> ConstructorArgumentValueList = new ArrayList<>();
    public ConstructorArgumentValues() {
    }
    public void addConstructorArgumentValue(ConstructorArgumentValue ConstructorArgumentValue) {
        this.ConstructorArgumentValueList.add(ConstructorArgumentValue);
    }
    public ConstructorArgumentValue getIndexedConstructorArgumentValue(int index) {
        ConstructorArgumentValue ConstructorArgumentValue = this.ConstructorArgumentValueList.get(index);
        return ConstructorArgumentValue;
    }
    public int getArgumentCount() {
        return (this.ConstructorArgumentValueList.size());
    }
    public boolean isEmpty() {
        return (this.ConstructorArgumentValueList.isEmpty());
    }
}