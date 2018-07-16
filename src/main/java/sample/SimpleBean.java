package sample;

import java.util.ArrayList;
import java.util.List;

public class SimpleBean {
    private int x = 77;
    private String name = "Tilo";
    private List<Inner> inner = new ArrayList<>();

    public SimpleBean() {
        inner.add(new Inner());
        inner.add(new Inner());

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Inner> getInner() {
        return inner;
    }

    public void setInner(List<Inner> inner) {
        this.inner = inner;
    }

    public class Inner{
        private int innerx = 12;

        public int getInnerx() {
            return innerx;
        }

        public void setInnerx(int innerx) {
            this.innerx = innerx;
        }
    }


}


