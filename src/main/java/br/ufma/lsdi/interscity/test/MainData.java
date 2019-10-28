package br.ufma.lsdi.interscity.test;

import java.util.Iterator;
import java.util.List;

import br.ufma.lsdi.interscity.beans.CapabilityValue;
import br.ufma.lsdi.interscity.beans.Resource;
import br.ufma.lsdi.interscity.mamager.InterSCityManagerFactory;

public class MainData {
	public static void main(String[] args) {
		InterSCityManagerFactory ic = new InterSCityManagerFactory(MainCapability.URL);
		Resource[] list = ic.resources().findResourceActiveByDescription("Academic_Block-Building_Total_Mains-0");
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
		}
		List<CapabilityValue> listData = ic.data().getData("81410f03-6ae9-46ab-ae8c-6854f732485a", "data");
		System.out.println("=================");
		for (Iterator<CapabilityValue> iterator = listData.iterator(); iterator.hasNext();) {
			CapabilityValue capabilityValue = (CapabilityValue) iterator.next();
		//	System.out.println(capabilityValue);
		}
		System.out.println("===============");
		System.out.println("===============");
		System.out.println("===============");
		System.out.println("===============");
		System.out.println("===============");
		System.out.println("===============");
		ic.data().sendDataResource("81410f03-6ae9-46ab-ae8c-6854f732485a", listData);

		
	}
}
