package com.learntest;

/**
 * @author lei
 * @date 01/31/2021 4:32 PM
 */
public class Incrementor {
    public int increment(ValueGenerator valueGenerator){
        return valueGenerator.getValue() + 1;
    }
}
