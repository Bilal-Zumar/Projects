package arrayList;


public interface List
{

    void add(int index, Integer element);
    boolean add(Integer element);
    Integer get(int index);
    Integer remove(int index);
    Integer set(int index, Integer element);
    int size();
    int indexOf(Integer element);

}
