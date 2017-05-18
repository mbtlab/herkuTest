package com.datacenter.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Types;

import sun.misc.BASE64Encoder;
/**
 * 加密工具类
 * Date: 2017-05-03
 * @author Leo
 *
 */
public class EncodeUtil {
	/**
	 * 对字符串进行MD5加密
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String EncoderByMd5(String str) throws NoSuchAlgorithmException,UnsupportedEncodingException{
		//确定计算方式
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		//加密后字符串
		String strEnCode = base64en.encode(md5.digest(str.getBytes("utf-8")));
		return strEnCode;
	}
	/**
	 * 检查签名是否正确
	 * @param newStr
	 * @param signature
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static boolean checkSignature(String newStr,String signature) throws NoSuchAlgorithmException,UnsupportedEncodingException{
		if(EncoderByMd5(newStr).equals(signature))
            return true;
        else
            return false;
	}
	/**
	 * 把字节数组转成16进制数
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes){
		StringBuffer md5Str = new StringBuffer();
		int digital;
		for(int i = 0;i < bytes.length;i ++){
			digital = bytes[i];
			if(digital < 0){
				digital += 256;
			}
			if(digital < 16){
				md5Str.append("0");
			}
			md5Str.append(Integer.toHexString(digital));
		}
		return md5Str.toString().toUpperCase();
	}
	/**
	 * 把字节数组转成MD5
	 * @param input
	 * @return
	 */
	public static String bytesToMD5(byte[] input){
		String md5Str = null;
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] buff = md.digest(input);
			md5Str = bytesToHex(buff);
		}catch(Exception e){
			e.printStackTrace();
		}
		return md5Str;
	}
	/**
	 * 把字符串转成MD5
	 * @param str
	 * @return
	 */
	public static String strToMD5(String str){
		byte[] input = str.getBytes();
		return bytesToMD5(input);
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String signature = EncoderByMd5("2017051021544760752" + "test_token_2017");
		System.out.println("加密签名：" + signature);
		System.out.println("验证:" + checkSignature("hello",signature));
		System.out.println("====" + Types.INTEGER);
		System.out.println(strToMD5(signature));
	}
}
