package com.company.ant;

public abstract class ant implements run{
    protected String name;
    protected int Size;
//
    public int getSize() {
        return Size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        Size = size;
    }
}
