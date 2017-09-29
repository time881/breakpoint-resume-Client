package com.file.client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.block.client.ClientBlock;

public class ClientAction extends Thread {

	private long length = 0;

	private String FileName = null;

	private String FileID = "C:/Temp/Server/";

	private Object O;

	private String Path;

	private ClientBlock clientBlock;

	public ClientAction(String p, Object o) {
		this.Path = p;
		this.O = o;
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}

	public Object getO() {
		return O;
	}

	public void setO(Object o) {
		O = o;
	}

	@Override
	public void run() {
		super.run();
		try {
			Socket socket = new Socket("127.0.0.1", 5209);
			this.Send(Path, this.O, socket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Notify(Object o) {
		synchronized (o) {
			o.notify();
		}
	}

	private void Send(String Path, Object o, Socket socket) throws Exception {

		byte[] SendByte = new byte[ClientBlock.getBlockLength()];

		int flag = 0;

		int complete = 0;

		File file = new File(Path);

		System.out.println("Client Run..");

		// PrintWriter write = new PrintWriter(socket.getOutputStream());

		ObjectOutputStream objectStream = new ObjectOutputStream(socket
				.getOutputStream());

		FileInputStream fis = new FileInputStream(file);

		while ((length = fis.read(SendByte, 0, SendByte.length)) > 0) {
			synchronized (o) {

				complete = (flag++ * SendByte.length);

				clientBlock = new ClientBlock(SendByte, FileID, file.getName(),
						complete, (int) length, false);

				objectStream.writeObject(clientBlock);

				objectStream.reset();

				if (this.currentThread().interrupted()) {
					System.out.println("Have completed: "
							+ (complete + length + 0.0) * 100 / file.length()
							+ "%");
					o.wait();
				}
			}
		}

		// sent complete flag to Server to stop accept
		objectStream.writeObject(new ClientBlock(null, null, null, 0, 0, true));

		System.out.print("complete");

		fis.close();
		socket.close();
		objectStream.close();
	}
}
