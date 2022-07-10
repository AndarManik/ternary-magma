package SortedInsert;

import java.util.ArrayList;

public class ValueSortedStack<T> {
    int size;
    ArrayList<ValueGeneric> data = new ArrayList<>();
    public ValueSortedStack(int size){
        this.size = size;
    }
    public void add(double input, T generic) {
        ValueGeneric vg = new ValueGeneric(input, generic);
        if(data.size() < size || input < data.get(data.size() - 1).value)
            insert(vg);
    }
    void insert(ValueGeneric input) {
        for (int i = 0; i < data.size(); i++)
            if(input.value < data.get(i).value) {
                data.add(i, input);
                if(data.size() > size)
                    data.remove(data.size() - 1);
                return;
            }
        data.add(input);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = data.size() - 1; i >= 0; i--) {
            ValueGeneric datum = data.get(i);
            sb.append(datum).append("\n");
        }
        return sb.toString();
    }
    
    class ValueGeneric {
        double value;
        T generic;
        public ValueGeneric(double error, T generic) {
            this.value = error;
            this.generic = generic;
        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append(value).append("\n");
            sb.append(generic);
            return sb.toString();
        }
    }

}
