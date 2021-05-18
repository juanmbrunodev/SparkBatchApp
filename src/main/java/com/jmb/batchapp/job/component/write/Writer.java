package com.jmb.batchapp.job.component.write;

public interface Writer<T> {
    public void write(T input);
}
