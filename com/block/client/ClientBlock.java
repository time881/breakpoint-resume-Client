package com.block.client;

import java.io.Serializable;

public class ClientBlock implements Serializable {

	private static final long serialVersionUID = -6253845831347724035L;

	/**
	 * one block to send
	 */
	private byte[] block;
	
	/**
	 * one standard block length
	 */
	private static final int BlockLength = 100;

	/**
	 * one reality block length
	 */
	private int Size;
	
	/**
	 * send to server as basis of a file
	 */
	private String FileID;

	/**
	 * filename
	 */
	private String FileName;
	
	private int offset;


	/**
	 * judgement of file complete, send to Server to stop
	 */
	private boolean Endflag;

	public ClientBlock(byte[] block, String FileID, String Filename,
			int offset, int Size, boolean Endflag) {
		this.block = block;
		this.FileID = FileID;
		this.FileName = Filename;
		this.offset = offset;
		this.Size = Size;
		this.Endflag = Endflag;
	}

	public static int getBlockLength() {
		return BlockLength;
	}

	public boolean isEndflag() {
		return Endflag;
	}

	public void setEndflag(boolean endflag) {
		Endflag = endflag;
	}

	public int getSize() {
		return Size;
	}

	public void setSize(int size) {
		Size = size;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public byte[] getBlock() {
		return block;
	}

	public void setBlock(byte[] block) {
		this.block = block;
	}

	public String getFileID() {
		return FileID;
	}

	public void setFileID(String fileID) {
		FileID = fileID;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}
}
