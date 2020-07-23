package chapter21;

public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;

    /**
     * 使用synchronized
     * @return
     */
    public synchronized static int nextSerialNumber() {
        // Not thread-safe
        return serialNumber++;
    }
}
