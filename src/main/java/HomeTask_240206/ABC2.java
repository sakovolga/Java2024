package HomeTask_240206;

import java.util.ArrayList;
import java.util.List;

public class ABC2 {
    private List<String> list;

    public ABC2() {
        this.list = new ArrayList<>();
    }

    public List<String> getList() {
        return list;
    }

    public void addToList(String str) {
        list.add(str);
    }

    public void modifyList() {
        list.replaceAll(el -> el + "!");
    }
}
