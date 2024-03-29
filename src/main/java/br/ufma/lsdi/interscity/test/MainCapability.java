package br.ufma.lsdi.interscity.test;

import br.ufma.lsdi.interscity.beans.Capability;
import br.ufma.lsdi.interscity.manager.CapabilitiesManager;
import br.ufma.lsdi.interscity.manager.InterSCityManagerFactory;

public class MainCapability {
	public static String URL = "http://cidadesinteligentes.lsdi.ufma.br/eq1";

	public static void main(String[] args) {
		InterSCityManagerFactory re = new InterSCityManagerFactory(URL);
		re.capabilities().createCapability("teste", "Teste", Capability.CAPABILITY_TYPE_SENSOR);
		
	}
	
	public void teste() {
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
