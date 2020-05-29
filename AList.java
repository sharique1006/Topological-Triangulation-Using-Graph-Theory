public class AList<T> {

    T[] arr;
    int currSize;

    public AList() {
        arr = (T[]) new Object[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = null;
        }
        currSize = 0;
    }
    public int size() {
        return currSize;
    }

    public void add(T value) {
        currSize++;
        if(arr[arr.length-1] != null) {
            T[] arr2 = (T[]) new Object[10000 + currSize];
            for(int i = 0; i < arr.length; i++) {
                arr2[i] = arr[i];
            }
            int temp = arr.length;
            arr = (T[]) new Object[10000 + currSize];
            arr = arr2;
            arr[temp] = value;
        }
        else {
            int count = 0;
            while (arr[count] != null) {
                count++;
            }
            arr[count] = value;
        }
    }

    public T get(int index) {
        return arr[index]; 
    }

    public void set (int index, T value) {
        arr[index] = value;
    }
}