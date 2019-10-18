package com.demo;

import java.util.HashSet;
import java.util.Set;

public class Singleton {

    private Set<String> id;

    private Singleton(){
        id = new HashSet<String>();
    }
    private static class SingletonSetHolder{
        private final static Singleton instance = new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonSetHolder.instance;
    }
    public void add(String name){
        id.add(name);
    }
    public boolean contain(String name){
        return id.contains(name);
    }
}
