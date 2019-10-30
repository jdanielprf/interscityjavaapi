
# API JAVA para InterSCity

Esta biblioteca permite manipular recursos, capacidades e dados do InterSCity como objetos Java.

[Download](https://github.com/jdanielprf/interscityjavaapi/blob/master/lib/interscityjavaapi.jar)
 
## Importar a biblioteca
Importe a biblioteca  e depois instancie a classe ```InterSCityManagerFactory``` passando como argumento a URL da plataforma.

```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
```

## Gerenciando recursos

Para manipular os recursos é necessário obter uma instância da classe ```DataManager``` por meio da classe InterSCityManagerFactory. 
Para isso utilize o método ```capacities()``` .
Todos os recursos da plataforma são encapsulados em objetos do tipo ```Resource```.

#### Resource class

```java
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;

	private String uuid;
	private Double lat;
	private Double lon;
	private String description;
	private String capabilities[];
	private String status = "active";
	...
}
```

#### Obter um recurso pelo UUID
Informe o UUID (tipo String) do recurso.
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	Resource resource = rest.resources().getResource("61f97d1f-0531-4ad2-b30a-6c758d576895");
		
```


#### Obter um recurso pelo UUID e listar suas capacidades(apenas o nome).
Informe o UUID (tipo String) do recurso.
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	Resource resource = rest.resources().getResource("61f97d1f-0531-4ad2-b30a-6c758d576895");
	String[] capabilities = resource.getCapabilities();
		
```

#### Obter a lista de recursos ativos com uma determinada descrição.
Informe uma string contendo a descrição do recurso.

```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	Resource[] resources = rest.resources().findResourceActiveByDescription("meu-recurso");
```

#### Obter todos os recursos

```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	Resource[] resources = rest.resources().getAll();
```


#### Obter todos os recursos que contém atuadores
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	Resource[] resources = rest.resources().getAllResouceWithActuators();
```


#### Obter todos os recursos que contém sensores
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	Resource[] resources = rest.resources().getAllResouceWithSensors();
```

## Gerenciando capacidades 
Para manipular as capacidades é necessário obter uma instância da classe ```CapabilitiesManager``` por meio da classe ```InterSCityManagerFactory```. 
Para isso utilize o método ```capacities()``` .
Todos as capacidades da plataforma são encapsulados em objetos do tipo ```Capability```.

#### Capability class

```java
public class Capability {
	
	public static String CAPABILITY_TYPE_SENSOR="sensor";
	public static String CAPABILITY_TYPE_ACTUATOR="actuator";
	
	private String id;
	private String name;
	private String description;
	private String type;
	...
}
```


#### Obter a lista de todas as capacidades
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	Capability[] list = rest.capabilities().getAll();
```


#### Criar uma capacidade
Utilize os atributos estático para obter o valor das constantes do projeto **InterSCityJavaAPI**.
Informe o nome, descição e o tipo da capacidade.
Para setar o tipo da capacidade use as constantes: ```CapabilitiesManager.TYPE_SENSOR``` e ```CapabilitiesManager.TYPE_ACTUATOR```

```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	rest.capabilities().createCapability("daniel", "descricao daniel", CapabilitiesManager.TYPE_SENSOR);
	
```

#### Remover capacidade
Informe o nome da capacidade que deseja remover.
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	rest.capabilities().dateleCapability("temperatura");
		
```

#### Atualizar capacidade
Informe o nome da capacidade que deseja atualizar e depois os novos valores para a descrição e o tipo da capacidade.
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	rest.capabilities().updateCapability("rele", "rele da lampada da sala", CapabilitiesManager.TYPE_ACTUATOR);	
```




## Gerenciando dados
Para enviar e recuperar dados é necessário obter uma instância da classe ```DataManager``` por meio da classe ```InterSCityManagerFactory```. Para isso utilize o método ```data()``` .
Todos os dados da plataforma são encapsulados em objetos do tipo ```CapabilityValue```.

#### CapabilityValue class

```java
public class CapabilityValue {
	private String name;
	private Object value;
	private String timeStamp;
	...
}
```

#### Obter todos os dados de um recurso 

Informe o UUID do recurso e a descrição.

```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	List<CapabilityValue> listData = rest.data().getData("81410f03-6ae9-46ab-ae8c-6854f732485a", "data");
	
```

#### Enviar dados referente a um recurso 

Crie uma lista de **CapabilityValue**, não esqueça que todos os valores não podem ser nulos.

```java
	InterSCityManagerFactory ic = new InterSCityManagerFactory("...");
		
	List<CapabilityValue> list=new ArrayList<CapabilityValue>();
	for (int i = 0; i < 10; i++) {
			CapabilityValue value=new CapabilityValue();
			value.setName("ufma_combed_current");
			value.setValue(i);
			value.setTimeStamp(DateManager.convertDate(new Date()));
			list.add(value);
	}
		
	ic.data().sendDataResource("d8b34e32-30a4-4733-bfd2-02f0d258c256", list);
	
```



# Exemplo: monitoramento do uso de memória de um computador

Este exemplo consite em monitorar pelo InterSCIty o consumo de memória de um computador.

### Criar capacidade e recurso
Como o dado que deseja-se monitorar é o consumo de memória é necessário criar um capacidade referente ao uso de memória. Depois cria-se um recurso que representa o computador que estamos monitorando, também é colocada uma descrição e a localização do recurso(computador). 
Depois de obter a informação sobre a memoria disponivel em um computador devemos emcapsular o recurso em um objeto do tipo ```CapabilityValue

```java
		SingletonManager.init( "http://cidadesinteligentes.lsdi.ufma.br/eq1");
		SingletonManager.get().capabilities().createCapability("jd_memory", "memoria do computador",
				Capability.CAPABILITY_TYPE_SENSOR);
		Resource resource = new Resource();
		resource.setDescription("pc_jose_daniel");
		resource.setCapabilities(new String[] { "jd_memory" });
		resource.setLat(558251.0);
		resource.setLon(-44.308325);
		SingletonManager.get().resources().createResource(resource);

```


### Enviar dados

Primeiro é necessário buscar o recurso, normalmente isso é feito pelo UUID do recurso. Mas, neste caso, vamos localizar o recurso pela descrição. Como a descrição não é unica, o interscity devolve uma lista de recurso que possuem essa descrição.
Depois é necessário encapsular o dado em um objeto do tipo ```CapabilityValue```. Depois é só enviar o objeto!

```java

		SingletonManager.init( "http://cidadesinteligentes.lsdi.ufma.br/eq1");
		Resource resources[]=SingletonManager.get().resources().findResourceActive("description", "pc_jose_daniel");
		
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
				SingletonManager.get().data().sendDataResource(resources[0].getUuid(), value);
				Thread.sleep(1000);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

```
		
### Enviando dados para o InterScity por meio do ContextNet

```java

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


```