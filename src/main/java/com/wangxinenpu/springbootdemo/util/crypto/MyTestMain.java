package com.wangxinenpu.springbootdemo.util.crypto;

public class MyTestMain {

	public static void main(String[] args) {
		String psd = "123456";
		SM4Utils sm4 = new SM4Utils();
		String enpsd = sm4.encryptData_ECB(psd);
		String denpsd = sm4.decryptData_ECB(enpsd);
		System.out.println(enpsd);
		System.out.println(denpsd);
		

	}

}
