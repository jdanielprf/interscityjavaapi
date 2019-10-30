package br.ufma.lsdi.interscity.test;

import java.util.ArrayList;
import java.util.Date;

import br.ufma.lsdi.interscity.beans.Capability;
import br.ufma.lsdi.interscity.beans.CapabilityValue;
import br.ufma.lsdi.interscity.beans.Resource;
import br.ufma.lsdi.interscity.manager.ContextNetSingleton;
import br.ufma.lsdi.interscity.manager.DateUtil;
import br.ufma.lsdi.interscity.manager.SingletonManager;

public class MainPCResource {
	public static void create() {
		
				 
		SingletonManager.init( "http://192.168.0.26:8000");
		SingletonManager.get().capabilities().createCapability("jd_memory", "memoria do computador",
				Capability.CAPABILITY_TYPE_SENSOR);
		Resource resource = new Resource();
		resource.setDescription("pc_jose_daniel");
		resource.setCapabilities(new String[] { "jd_memory" });
		resource.setLat(558251.0);
		resource.setLon(-44.308325);
		SingletonManager.get().resources().createResource(resource);
	}

	public static void send() {
		SingletonManager.init( "http://192.168.0.26:8000");
		Resource resources[]=SingletonManager.get().resources().findResourceActive("description", "pc_jose_daniel");
		
		while(resources.length>0) {
			try {
				int mb = 1024*1024;
				
			//	com.sun.management.OperatingSystemMXBean operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
	            //RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
	            //operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	            com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean)
	            java.lang.management.ManagementFactory.getOperatingSystemMXBean();
	            long physicalMemorySize = os.getTotalPhysicalMemorySize();
	            long physicalfreeMemorySize = os.getFreePhysicalMemorySize();

				long men = (physicalMemorySize-physicalfreeMemorySize)/mb;
				CapabilityValue value = new CapabilityValue();
				value.setName("jd_memory");
				value.setValue(men);
				value.setTimeStamp(DateUtil.convertDate(new Date()));
				SingletonManager.get().data().sendDataResource(resources[0].getUuid(), value);
				Thread.sleep(1000);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public static void sendContextNet() {
		SingletonManager.init( "http://192.168.0.26:8000");
		Resource resources[]=SingletonManager.get().resources().findResourceActive("description", "pc_jose_daniel");
		
		// config context net
		ContextNetSingleton.init("192.168.0.26", 5500);
		ContextNetSingleton.get().connect();
		while(resources.length>0) {
			try {
				int mb = 1024*1024;
				com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean)
	            java.lang.management.ManagementFactory.getOperatingSystemMXBean();
	            long physicalMemorySize = os.getTotalPhysicalMemorySize();
	            long physicalfreeMemorySize = os.getFreePhysicalMemorySize();

				long men = (physicalMemorySize-physicalfreeMemorySize)/mb;
				CapabilityValue value = new CapabilityValue();
				value.setName("jd_memory");
				value.setValue(men);
				
				value.setTimeStamp(DateUtil.convertDate(new Date()));
				ArrayList<CapabilityValue> list=new ArrayList<CapabilityValue>();
				list.add(value);
				ContextNetSingleton.get().sendCapabilityValues(resources[0].getUuid(), list);
		
				Thread.sleep(1000);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		// create();
		sendContextNet();
	}
}
