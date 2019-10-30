package br.ufma.lsdi.interscity.manager;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.ufma.lsdi.interscity.beans.CapabilityValue;
import br.ufma.lsdi.interscityl.beans.rest.ContextNetValues;
import lac.cnclib.net.NodeConnection;
import lac.cnclib.net.NodeConnectionListener;
import lac.cnclib.net.mrudp.MrUdpNodeConnection;
import lac.cnclib.sddl.message.ApplicationMessage;
import lac.cnclib.sddl.message.Message;
import lac.cnclib.sddl.serialization.Serialization;

public class ContextNet implements NodeConnectionListener{
	public static final String TAG_SIMULATOR_DATA = "TAG_SIMULATOR_DATA";
	public  static final  String TAG_SIMULATOR_DATA_ACK = "TAG_SIMULATOR_DATA_ACK";
	
	public static int DEFAULT_PORT=4000;
	private String gatewayIP;
	private int gatewayPort;
	private UUID uuid;
	private MrUdpNodeConnection connection;
	
	public ContextNet(String ip, int port){
		gatewayIP=ip;
		gatewayPort=port;
	}
	public ContextNet(String ip){
		this(ip,DEFAULT_PORT);
	}
	
	@Override
	public void connected(NodeConnection remoteCon) {
		 System.out.println("Conectado!!!");
		 uuid=remoteCon.getUuid();
	}
	
	public boolean checkConnection(){
		return this.uuid!=null && !connection.getSocket().isClosed();
	}
	public void connect() {
		InetSocketAddress address = new InetSocketAddress(gatewayIP, gatewayPort);
		try {
			connection = new MrUdpNodeConnection();	
			connection.addNodeConnectionListener(this);
			connection.connect(address);

		} catch (IOException e) {
			e.printStackTrace();
		//	JOptionPane.showMessageDialog(null, "Servidor nao localizado!");
		}
	}

	
	public void sendData(Serializable msg) throws Exception{
		ArrayList<String> list=new ArrayList<String>();
		sendData(list,msg);
	}
	
	public void sendData(String tag,Serializable msg) throws Exception{
		ArrayList<String> list=new ArrayList<String>();
		list.add(tag);
		sendData(list,msg);
	}
	public void sendData(List<String> tags,Serializable msg) throws Exception{
		if(uuid==null||connection.getSocket().isClosed()) {
			throw new Exception("not connected");
		}
		ApplicationMessage message = new ApplicationMessage();
		message.setTagList(tags);
		
		message.setContentObject((msg));

		connection.sendMessage(message);
		
	}
	public void sendCapabilityValues(String uuid, List<CapabilityValue> value) throws Exception{
		sendCapabilityValues(uuid,"data", value);
	}
	
	public void sendCapabilityValues(String uuid,String description, List<CapabilityValue> values) throws Exception{
		ContextNetValues v=new ContextNetValues();
		v.setUuid(uuid);
		v.setValues(values);
		v.setDescription(description);
		v.setUuidData(UUID.randomUUID().toString());
		sendData(TAG_SIMULATOR_DATA, v);
	}
	
	public void receive(NodeConnectionListener listener){
		connection.addNodeConnectionListener(this);
	}
	@Override
	public void disconnected(NodeConnection arg0) {
	}
	@Override
	public void internalException(NodeConnection arg0, Exception arg1) {		
	}
	@Override
	public void newMessageReceived(NodeConnection arg0, Message arg1) {
	}
	@Override
	public void reconnected(NodeConnection arg0, SocketAddress arg1, boolean arg2, boolean arg3) {	
	}
	@Override
	public void unsentMessages(NodeConnection arg0, List<Message> arg1) {
	
	}
}
