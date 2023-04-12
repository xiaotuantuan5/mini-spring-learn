package com.minis.context;

import java.util.*;


public class ArgumentValues {
    private final List<ArgumentValue> argumentValueList = new ArrayList<>();
    public ArgumentValues() {
    }
    public void addArgumentValue(ArgumentValue argumentValue) {
        this.argumentValueList.add(argumentValue);
    }
    public ArgumentValue getIndexedArgumentValue(int index) {
        ArgumentValue argumentValue = this.argumentValueList.get(index);
        return argumentValue;
    }
    public int getArgumentCount() {
        return (this.argumentValueList.size());
    }
    public boolean isEmpty() {
        return (this.argumentValueList.isEmpty());
    }
}
//public class ArgumentValues {
//    private final Map<Integer, ArgumentValue> indexedArgumentValues = new HashMap<>(0);
//    private final List<ArgumentValue> genericArgumentValues = new LinkedList<>();
//    public ArgumentValues() {
//    }
//    private void addArgumentValue(Integer key, ArgumentValue newValue) {
//        this.indexedArgumentValues.put(key, newValue);
//    }
//    public boolean hasIndexedArgumentValue(int index) {
//        return this.indexedArgumentValues.containsKey(index);
//    }
//    public ArgumentValue getIndexedArgumentValue(int index) {
//        return this.indexedArgumentValues.get(index);
//    }
//    public void addGenericArgumentValue(Object value, String type) {
//        this.genericArgumentValues.add(new ArgumentValue(value, type));
//    }
//    private void addGenericArgumentValue(ArgumentValue newValue) {
//        if (newValue.getName() != null) {
//            for (Iterator<ArgumentValue> it =
//                 this.genericArgumentValues.iterator(); it.hasNext(); ) {
//                ArgumentValue currentValue = it.next();
//                if (newValue.getName().equals(currentValue.getName())) {
//                    it.remove();
//                }
//            }
//        }
//        this.genericArgumentValues.add(newValue);
//    }
//    public ArgumentValue getGenericArgumentValue(String requiredName) {
//        for (ArgumentValue valueHolder : this.genericArgumentValues) {
//            if (valueHolder.getName() != null && (requiredName == null || !valueHolder.getName().equals(requiredName))) {
//                continue;
//            }
//            return valueHolder;
//        }
//        return null;
//    }
//    public int getArgumentCount() {
//        return this.genericArgumentValues.size();
//    }
//    public boolean isEmpty() {
//        return this.genericArgumentValues.isEmpty();
//    }
//}