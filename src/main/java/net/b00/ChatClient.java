package net.b00;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
/**
 * 通信类
 * @author zhaoqk
 *
 * 2020年8月10日 下午5:07:31
 */
public class ChatClient extends Thread{
	
	private static StringBuffer sb = new StringBuffer();

    //定义一个Socket对象
    Socket socket = null;
    public ChatClient() {
    	
    }
    public ChatClient(String host, int port) {
        try {
            //需要服务器的IP地址和端口号，才能获得正确的Socket对象
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
		if(json.getInteger("type") == 0){//连接成功
			System.out.println("[连接成功]");
		}else if(json.getInteger("type") == 1){//好友消息
			Main.receivePrivateMessages(json.toJSONString());
		}else if(json.getInteger("type") == 2){//群聊消息
			Main.receiveGroupMessages(json.toJSONString());
		}else if(json.getInteger("type") == 3){//事件消息
			Main.receiveEventMessages(json.toJSONString());
		}else if(json.getInteger("type") == 4){//查询返回
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
    
    //写操作
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
