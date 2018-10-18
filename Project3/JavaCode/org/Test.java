package org;

import RY3jni.CRY3;
import RY3jni.IRY3;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成方法存根
		IRY3     ry = new CRY3();
		int[]  Count       = new int[4];
		char[] charHardID  = new char[32]; //16
		//char[] charPid     = new char[]{'F','C','8','E','8','F','8','4'};

		char[] charPid     = new char[]{'0','0','0','0','0','0','0','0'};
		ry.RY3_Find(charPid, Count);
		ry.RY3_Open(1);
		ry.RY3_GetHardID(charHardID);
		String id = String.valueOf(charHardID);



//		存MD5
		{
		byte[] testbuf = new byte[128];
		for(int i=0; i<32; i++){
			testbuf[i] = (byte)charHardID[i];
		}
		byte[] md5buf  = new byte[16];
		ry.RY3_MD5(testbuf, 128, md5buf);
		ry.RY3_Write(0, md5buf, 16);
		}



//		取ID的MD5
		{
		byte[] testbuf = new byte[128];
		for(int i=0; i<32; i++){
			testbuf[i] = (byte)charHardID[i];
		}
		byte[] md5buf  = new byte[16];
		ry.RY3_MD5(testbuf, 128, md5buf);

//		取狗内存的MD5
		byte[] tmpbuf  = new byte[16];
		ry.RY3_Read(0, tmpbuf, 16);

//		进行验证
		boolean ok =true;
		for(int i = 0 ; i < 16 ; i ++){
			if(md5buf[i]!=tmpbuf[i]){
				ok=false;
				break;
			}
		}

		System.out.println(ok);
		}

		ry.RY3_Close(true);
	}

}
