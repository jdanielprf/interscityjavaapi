package br.ufma.lsdi.interscity.test;

import br.ufma.lsdi.interscity.beans.Capability;
import br.ufma.lsdi.interscity.mamager.CapabilitiesManager;
import br.ufma.lsdi.interscity.mamager.InterSCityManagerFactory;

public class MainCapability {
	public static String URL = "http://cidadesinteligentes.lsdi.ufma.br/eq1";

	public static void main(String[] args) {
		InterSCityManagerFactory re = new InterSCityManagerFactory(URL);
		
		Capability[] list = re.capabilities().getAll();
		
		for (int i = 0; i < list.length; i++) {
			Capability capability = list[i];
			System.out.println(capability);
		}
		re.capabilities().createCapability("daniel", "descricao daniel", CapabilitiesManager.TYPE_SENSOR);
	
		re.capabilities().updateCapability("daniel", "outra descricao", CapabilitiesManager.TYPE_ACTUATOR);
		
		re.capabilities().dateleCapability("daniel");
		
		//re.capability().getCapability("daniel");
		
	}
}
