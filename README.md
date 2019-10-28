
### API JAVA para InterSCity

Esta biblioteca permite manipular recursos, capacidades e dados como objetos Java.

### Importar a biblioteca
Importe a biblioteca. 
Faça a instanciação por meio da classe InterSCityManagerFactory

```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
```

### Gerenciando recursos

Para manipular os recursos é necessario obter uma instância da classe DataManager por meio da classe InterSCityManagerFactory. Para isso é necessário usar o método **capacities()** .


##Obter um recurso pelo UUID
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	rest.resources().getResource("61f97d1f-0531-4ad2-b30a-6c758d576895");
```

##Obter um recurso por descrição com status ativo
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	rest.resources().findResourceActiveByDescription("mob-mail");
```

##Obter todos os recursos
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	rest.resources().getAll();
```


##Obter todos os recursos que contem atuadores
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	rest.resources().getAllResouceWithActuators();
```


##Obter todos os recursos que contem sensores
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	 rest.resources().getAllResouceWithSensors();
```

### Gerenciando capacidades 
Para manipular os recursos é necessario obter uma instância da classe DataManager por maio da classe InterSCityManagerFactory. Para isso é necessário usar o método **capacities()** .

## lista de capacidades
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	Capability[] list = rest.capabilities().getAll();
```


## criar uma nova capacidade
Utilize os atributos estatiscos para obter o valor das constantes do InterSCity.

Para setar o tipo da capacidade use as constantes: CapabilitiesManager.TYPE_SENSOR e CapabilitiesManager.TYPE_ACTUATOR
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	rest.capabilities().createCapability("daniel", "descricao daniel", CapabilitiesManager.TYPE_SENSOR);
	
```

## remover capacidade
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	rest.capabilities().dateleCapability("temperatura");
		
```

## update capacidade
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	rest.capabilities().updateCapability("rele", "rele da lampada da sala", CapabilitiesManager.TYPE_ACTUATOR);	
```




### Gerenciando dados

## Obter todos os dados de um recurso 
```java
	InterSCityManagerFactory rest=new InterSCityManagerFactory("...");
	List<CapabilityValue> listData = rest.data().getData("81410f03-6ae9-46ab-ae8c-6854f732485a", "data");
	
```

## Enviar dados referente a um recurso 

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







