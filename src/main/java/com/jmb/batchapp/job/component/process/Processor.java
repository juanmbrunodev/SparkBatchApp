package com.jmb.batchapp.job.component.process;

public interface Processor<T> {
    T process(T input);
}
