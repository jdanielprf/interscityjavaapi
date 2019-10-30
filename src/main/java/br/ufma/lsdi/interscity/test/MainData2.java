package br.ufma.lsdi.interscity.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufma.lsdi.interscity.beans.CapabilityValue;
import br.ufma.lsdi.interscity.manager.DateUtil;
import br.ufma.lsdi.interscity.manager.InterSCityManagerFactory;

public class MainData2 {
	public static void main(String[] args) {
		InterSCityManagerFactory ic = new InterSCityManagerFactory(MainCapability.URL);
		
		List<CapabilityValue> list=new ArrayList<CapabilityValue>();
		for (int i = 0; i < 10; i++) {
			CapabilityValue value=new CapabilityValue();
			value.setName("ufma_combed_current");
			value.setValue(i);
			value.setTimeStamp(DateUtil.convertDate(new Date()));
			list.add(value);
		}
		
		ic.data().sendDataResource("d8b34e32-30a4-4733-bfd2-02f0d258c256", list);
		
	}
}
