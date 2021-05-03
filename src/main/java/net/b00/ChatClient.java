package net.b00;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
/**
 * ͨ����
 * @author zhaoqk
 *
 * 2020��8��10�� ����5:07:31
 */
public class ChatClient extends Thread{
	
	private static StringBuffer sb = new StringBuffer();

    //����һ��Socket����
    Socket socket = null;
    public ChatClient() {
    	
    }
    public ChatClient(String host, int port) {
        try {
            //��Ҫ��������IP��ַ�Ͷ˿ںţ����ܻ����ȷ��Socket����
            socket = new Socket(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        try {
            InputStream s = socket.getInputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            String temp = "";
            while ((len = s.read(buf)) != -1) {
            	String data = new String(buf, 0, len);
            	temp += data;
            	if(len < 1024){
            		new MyRunnable(temp);
            		temp="";
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void parseData(String data){
    	if(data.equals("")){
    		return;
    	}
    	data = StringUtils.getURLDecoderString(data);
    	sb = sb.append(data);
    	String oneData = getOneData();
    	if(oneData == null){
    		return;
    	}
    	JSONObject json = JSONObject.parseObject(oneData);
		if(json.getInteger("type") == 0){//���ӳɹ�
			System.out.println("[���ӳɹ�]");
		}else if(json.getInteger("type") == 1){//������Ϣ
			Main.receivePrivateMessages(json.toJSONString());
		}else if(json.getInteger("type") == 2){//Ⱥ����Ϣ
			Main.receiveGroupMessages(json.toJSONString());
		}else if(json.getInteger("type") == 3){//�¼���Ϣ
			Main.receiveEventMessages(json.toJSONString());
		}else if(json.getInteger("type") == 4){//��ѯ����
			Main.selectResult(json.toJSONString());
		}
    }
    
    public static String getOneData(){
    	String data = sb.toString();
    	if(data.contains("FAFB") && data.contains("FEFF")){
    		int a = data.indexOf("FAFB") + 4;
    		String oneData = data.substring(a, data.indexOf("FEFF",a));
    		sb = new StringBuffer(sb.toString().replace("FAFB" + oneData + "FEFF", ""));
    		return oneData;
    	}
    	return null;
    }
    
    //д����
    public void sendMsg(String msg){
        OutputStream os= null;
        try {
            os= socket.getOutputStream();
            os.write(msg.getBytes());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    class MyRunnable implements Runnable{
    	private Thread t;
    	private String temp;
    	   
    	public MyRunnable(String temp) {
    		this.temp = temp;
			t = new Thread(this);
			t.start();
		}
    	   
		@Override
		public void run() {
			parseData(temp);
		}
    	
    }

}
