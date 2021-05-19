package com.jmb.batchapp.job.component.read;

/**
 * Interface that exposes a read operation.
 *
 * @param <T> The type the classes implementing will deal with.
 *
 * @author JuanMBruno
 */
public interface Reader<T> {
    T read();
}
