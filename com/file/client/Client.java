package com.file.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {

		try {
			Object objectSyn = new Object();

			String Path = new String("C:/Temp/test.txt");

			ClientAction c = new ClientAction(Path, objectSyn);

			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			String readline = br.readLine();

			while (!"stop".equals(readline)) {
				if ("start".equals(readline)) {
					c.start();
				} else if ("wait".equals(readline)) {
					c.interrupt();
				} else if ("notify".equals(readline)) {
					c.Notify(objectSyn);
				}
				readline = br.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
