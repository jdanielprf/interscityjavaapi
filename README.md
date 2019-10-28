
# API JAVA para InterSCity

Esta biblioteca permite manipular recursos, capacidades e dados como objetos Java.

[Download](https://github.com/jdanielprf/interscityjavaapi/blob/master/lib/interscityjavaapi.jar)
 
## Importar a biblioteca
Importe a biblioteca. 
Instancie a classe **InterSCityManagerFactory** passando como argumento a url da plataforma.

```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
```

## Gerenciando recursos

Para manipular os recursos é necessário obter uma instância da classe DataManager por meio da classe InterSCityManagerFactory. 
Para isso utilize o método **capacities()** .

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
Informe o UUID do recurso.
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	rest.resources().getResource("61f97d1f-0531-4ad2-b30a-6c758d576895");
```

#### Obter um recurso por descrição com status ativo
Informe uma string contendo a descrição do recurso.

```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	rest.resources().findResourceActiveByDescription("mob-mail");
```

#### Obter todos os recursos

```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	rest.resources().getAll();
```


#### Obter todos os recursos que contém atuadores
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	rest.resources().getAllResouceWithActuators();
```


#### Obter todos os recursos que contém sensores
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	 rest.resources().getAllResouceWithSensors();
```

## Gerenciando capacidades 
Para manipular os recursos é necessário obter uma instância da classe DataManager por maio da classe InterSCityManagerFactory. 
Para isso utilize o método **capacities()** .

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
Utilize os atributos estático para obter o valor das constantes do InterSCity.
Informe o nome, descição e o tipo da capacidade.
Para setar o tipo da capacidade use as constantes: CapabilitiesManager.TYPE_SENSOR e CapabilitiesManager.TYPE_ACTUATOR
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
Informe o nome do recurso que deseja atualizar e depois os novos valoes para a descrição e o tipo da capacidade.
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	rest.capabilities().updateCapability("rele", "rele da lampada da sala", CapabilitiesManager.TYPE_ACTUATOR);	
```




## Gerenciando dados

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




