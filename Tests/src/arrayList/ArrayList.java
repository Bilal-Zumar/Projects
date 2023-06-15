package arrayList;

public class ArrayList implements List{

    private Integer [] arr;
    private int next = 0;

    public ArrayList()
    {
        this(10);
    }

    public ArrayList(int size)
    {
        arr = new Integer[size];
    }

    @Override
    public void add(int index, Integer element) {
        if (index < 0 || index > next)
            System.out.println("Exception: invalid index - ignoring add");
        if (next == arr.length)
            resize();
        for (int i=next; i>index; i--)
            arr[i] = arr[i-1];
        arr[index] = element;
        next++;
    }

    @Override
    public boolean add(Integer element) {
        add(next, element);
        return true;
    }

    @Override
    public Integer get(int index) {
        if (index < 0 || index >= next)
        {
            System.out.println("Exception: invalid index in get");
            return -999;
        }
        return arr[index];
    }

    @Override
    public Integer remove(int index) {
        if (index < 0 || index >= next)
            System.out.println("Exception: invalid index in remove");
        Integer removedElement = arr[index];
        for (int i=index; i<next-1; i++)
            arr[i] = arr[i+1];
        arr[--next] = null;
        return removedElement;
    }

    @Override
    public Integer set(int index, Integer element) {
        if (index < 0 || index >= next)
            System.out.println("Exception: invalid index in set");
        Integer oldElement = arr[index];
        arr[index] = element;
        return oldElement;
    }

    @Override
    public int size() {
        return next;
    }

    @Override
    public int indexOf(Integer element) {
        for (int i=0; i<next; i++)
        {
            if (arr[i].equals(element))
                return i;
        }
        return -1;
    }

    private void resize()
    {
        Integer [] temp = new Integer[arr.length*2];
        for (int i=0; i<next; i++)
            temp[i] = arr[i];
        arr = temp;
    }

    public String toString()
    {
        String str = "";
        for (int k=0; k<next; k++)
        {
            str += String.format("%5s,", arr[k]);
            if ((k+1) %10 == 0)
                str += String.format("\n");
        }
        return str;
    }
}
