import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {
	
	static final int PORT_NUM = 9090;

	@Override
	public void run() {
		
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT_NUM);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Socket clientSocket = null;
		BufferedReader bufferedReader = null;
		OutputStream outputStream = null;
		
		String str = "";
		System.out.println("서버 연결 대기중");
		try {
			clientSocket = serverSocket.accept();
			System.out.println("서버 연결 되었습니다");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true){
			System.out.println("채팅 대기중");
			//if(clientSocket.isConnected()){
				try{
					
					bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					str = bufferedReader.readLine(); //줄 단위로만 읽는다. 엔터값
					System.out.println(str);
					if(str==null) break;
					
					str += "\r\n";
					outputStream = clientSocket.getOutputStream();
					outputStream.write(str.getBytes());
					outputStream.flush();
					
				}catch(IOException e){
					e.printStackTrace();
				}
//				finally{
//					if(str!=null){
//						try {
//							System.out.println("finally 실행");
//							//bufferedReader.close();
//							//clientSocket.close(); //여기를 주석처리 해주면 Port가 이미 사용되고 있다고 뜬다.
//							//serverSocket.close(); //serverSocket을 닫게 되면 연결이 끊어졌을 때 다시 연결이 안된다.
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}					
//					}				
//				}
			//}
		}
		
	}
}
