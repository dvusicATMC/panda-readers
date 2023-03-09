package com.atomic.commons.sender;

/**
 *
 * Interface that represents a simple sender. Implement a class for
 * a technology (example Kafka), define the key and value class and
 * the whole sending logic. It is used so other classes don't have
 * to worry about the correct way of sending data somewhere.
 *
 * @param <K> key
 * @param <V> value
 *
 * @author dvusic
 */
public interface Sender<K, V> {

    /**
     * Method that sends the value with the corresponding key
     *
     * @param recordKey key
     * @param recordValue value
     * @throws Exception
     */
    public void send(K recordKey, V recordValue) throws Exception;

    /**
     * Returns the max size that can be sent
     *
     * @return max size of value that can the sender send
     */
    public int getMaxRequestSize();

    public void close();
}
