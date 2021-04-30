package com.jmb.batchapp.job;

import java.util.Optional;

public abstract class Job<T> {

    abstract protected Optional<T> preProcess();
    abstract protected Optional<T> process(Optional<T> preProcessOutput);
    abstract protected void postProcess(Optional<T> processOutput);

    public void execute() {
        Optional<T> preProcessOutput = preProcess();
        Optional<T> processOutput = process(preProcessOutput);
        postProcess(processOutput);
    }
}
