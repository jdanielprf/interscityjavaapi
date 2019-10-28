package br.ufma.lsdi.interscity.test;

import br.ufma.lsdi.interscity.beans.Resource;
import br.ufma.lsdi.interscity.mamager.InterSCityManagerFactory;

public class Main {
	InterSCityManagerFactory rest=new InterSCityManagerFactory(MainCapability.URL);
	
	public void removerTudo() {
		Resource[] list = rest.resources().getAllResouceWithSensors();
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i].getUuid());
			//list[i].setStatus("incative");
			list[i].setCapabilities(new String[] {"temperature"});
			list[i].setDescription("removido");
			list[i].setLat(0.0);
			list[i].setLon(0.0);
			
			rest.resources().updateResource(list[i]);
		}
		
	}
	@SuppressWarnings("unused")
	public void teste() {
		
		System.out.println("=================");
		Resource resource = rest.resources().getResource("61f97d1f-0531-4ad2-b30a-6c758d576895");
		String[] capabilities = resource.getCapabilities();
		
		Resource[] resources = rest.resources().getAll();
		
		System.out.println();
		System.out.println("=================");
	//	Resource[] resources = rest.resources().findResourceActiveByDescription("meu-recurso");
		System.out.println();
		System.out.println("=================");
//		
//		Resource r=new Resource();
//		//r.setUuid("");
//		r.setStatus("active");
//		r.setDescription("teste123");
//		r.setCapabilities(new String[]{
//				"ufma_combed_build",
//				"ufma_combed_power",
//				"ufma_combed_circuit",
//		        "ufma_combed_energy",
//		       	"ufma_combed_current"});
//		r.setLat(1.0);
//		r.setLon(2.0);
//		String uuid=rest.create(r).getUuid();
//		System.out.println(uuid);
//		System.out.println("=================");
//		
	//	List<CapabilityEnergy> dados = new ReadData().readFolder("F:\\dataset\\iiitd\\Academic Block\\AHU\\0");
	//	rest.sendDataResource(r.getUuid(), dados);
	}
	
	public static void main(String[] args) {
		}
}