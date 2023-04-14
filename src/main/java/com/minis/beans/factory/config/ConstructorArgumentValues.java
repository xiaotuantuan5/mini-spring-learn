package com.minis.beans.factory.config;

import java.util.*;


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
//public class ConstructorArgumentValues {
//    private final Map<Integer, ConstructorArgumentValue> indexedConstructorArgumentValues = new HashMap<>(0);
//    private final List<ConstructorArgumentValue> genericConstructorArgumentValues = new LinkedList<>();
//    public ConstructorArgumentValues() {
//    }
//    private void addConstructorArgumentValue(Integer key, ConstructorArgumentValue newValue) {
//        this.indexedConstructorArgumentValues.put(key, newValue);
//    }
//    public boolean hasIndexedConstructorArgumentValue(int index) {
//        return this.indexedConstructorArgumentValues.containsKey(index);
//    }
//    public ConstructorArgumentValue getIndexedConstructorArgumentValue(int index) {
//        return this.indexedConstructorArgumentValues.get(index);
//    }
//    public void addGenericConstructorArgumentValue(Object value, String type) {
//        this.genericConstructorArgumentValues.add(new ConstructorArgumentValue(value, type));
//    }
//    private void addGenericConstructorArgumentValue(ConstructorArgumentValue newValue) {
//        if (newValue.getName() != null) {
//            for (Iterator<ConstructorArgumentValue> it =
//                 this.genericConstructorArgumentValues.iterator(); it.hasNext(); ) {
//                ConstructorArgumentValue currentValue = it.next();
//                if (newValue.getName().equals(currentValue.getName())) {
//                    it.remove();
//                }
//            }
//        }
//        this.genericConstructorArgumentValues.add(newValue);
//    }
//    public ConstructorArgumentValue getGenericConstructorArgumentValue(String requiredName) {
//        for (ConstructorArgumentValue valueHolder : this.genericConstructorArgumentValues) {
//            if (valueHolder.getName() != null && (requiredName == null || !valueHolder.getName().equals(requiredName))) {
//                continue;
//            }
//            return valueHolder;
//        }
//        return null;
//    }
//    public int getArgumentCount() {
//        return this.genericConstructorArgumentValues.size();
//    }
//    public boolean isEmpty() {
//        return this.genericConstructorArgumentValues.isEmpty();
//    }
//}