package br.ufma.lsdi.interscity.test;

public class MainMemory {
public static void main(String[] args) throws InterruptedException {
	int gb = 1024*1024;
	//com.sun.management.OperatingSystemMXBean operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
    //RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
    //operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean)
    java.lang.management.ManagementFactory.getOperatingSystemMXBean();
    long physicalMemorySize = os.getTotalPhysicalMemorySize();
    long physicalfreeMemorySize = os.getFreePhysicalMemorySize();
    System.out.println(physicalMemorySize/gb);
    System.out.println((physicalMemorySize-physicalfreeMemorySize)/gb);
    Thread.sleep(1000);
}
}
