package com.company.ant;

public abstract class ant implements IBehaviour {
    private int spawnTime;
    private String name;
    private int Size;

    @Override
    public int getSize() {
        return Size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSpawnTime() {
        return spawnTime;
    }

    @Override
    public void setSize(int size) {
        Size = size;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSpawnTime(int spawnTime) {
        this.spawnTime = spawnTime;
    }
}
