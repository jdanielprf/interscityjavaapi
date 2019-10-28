
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




