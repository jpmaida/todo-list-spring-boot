package com.laboratory.infra;

public class IdGenerator {
    public static long generate(){
        return ((Double)Math.floor(Math.random()*(100-1+1)+1)).longValue();
    }
}
