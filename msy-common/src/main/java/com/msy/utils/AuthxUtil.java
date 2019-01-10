package com.msy.utils;

import java.io.ByteArrayOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AuthxUtil
{
	private static Log log = LogFactory.getLog("AuthxUtil");
	private static final String splitString = "~_~";

	private static String asciiToHex(String value)
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < value.length(); i++)
		{
			sb.append(Integer.toString((int) value.charAt(i), 16));
		}
		return sb.toString();
	}

	private static String hexToAscii(String value)
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < value.length(); i = i + 2)
		{
			sb.append((char) Integer.parseInt(value.substring(i, i + 2), 16));
		}
		return sb.toString();
	}

	public static String encryptByMd5(String value)
	{
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try
		{
			byte[] btInput = value.getBytes("UTF-8");
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e)
		{
			log.error(e.getMessage());
		}
		return null;
	}

	public static String getRandNumber(int n)
	{
		String rn = "";
		if (n > 0 && n < 10)
		{
			Random r = new Random();
			String str = "";
			for (int i = 0; i < n; i++)
			{
				str += "9";
			}
			int num = Integer.parseInt(str);
			while (rn.length() < n)
			{
				rn = "" + r.nextInt(num);
			}
		} else
		{
			rn = "0";
		}
		return rn;
	}

	public static String encryptRememberMe(List<String> list)
	{
		String rememberMe = "";
		for (String key : list)
		{
			if (rememberMe != "")
			{
				rememberMe += splitString;
			}
			rememberMe += key;
		}
		rememberMe = Base64.encodeBase64String(rememberMe.getBytes());
		rememberMe = AuthxUtil.asciiToHex(rememberMe);
		return rememberMe;
	}

	public static List<String> unencryptRememberMe(String rememberMe)
	{
		List<String> list = new ArrayList<String>();
		rememberMe = AuthxUtil.hexToAscii(rememberMe);
		byte[] bt = Base64.decodeBase64(rememberMe);
		rememberMe = new String(bt);
		String[] rm = rememberMe.split(splitString);
		for (String key : rm)
		{
			list.add(key);
		}
		return list;
	}

	/**
	 * * 生成密钥对 *
	 * 
	 * @return KeyPair *
	 * @throws EncryptException
	 */
	public static KeyPair generateRSAKeyPair(int keySize)
	{
		try
		{
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA", new BouncyCastleProvider());
			// 没什么好说的了，这个值关系到块加密的大小，可以更改，但是不要太大，否则效率会低
			keyPairGen.initialize(keySize, new SecureRandom());
			KeyPair keyPair = keyPairGen.generateKeyPair();
			// saveKeyPair(keyPair);
			return keyPair;
		} catch (Exception e)
		{
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * * 加密 *
	 * 
	 * @param key
	 *            加密的密钥 *
	 * @param data
	 *            待加密的明文数据 *
	 * @return 加密后的数据 *
	 * @throws Exception
	 */
	public static byte[] encryptByRSA(PublicKey pk, byte[] data)
	{
		try
		{
			Cipher cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, pk);
			// 获得加密块大小，如：加密前数据为128个byte，而key_size=1024加密块大小为127byte,加密后为128个byte;因此共有2个加密块，第一个127byte第二个为1个byte
			int blockSize = cipher.getBlockSize();
			// 获得加密块加密后块大小
			int outputSize = cipher.getOutputSize(data.length);
			int leavedSize = data.length % blockSize;
			int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
			byte[] raw = new byte[outputSize * blocksSize];
			int i = 0;
			while (data.length - i * blockSize > 0)
			{
				if (data.length - i * blockSize > blockSize)
				{
					cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
				} else
				{
					cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
				}
				i++;
			}
			return raw;
		} catch (Exception e)
		{
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * * 解密 *
	 * 
	 * @param key
	 *            解密的密钥 *
	 * @param raw
	 *            已经加密的数据 *
	 * @return 解密后的明文 *
	 * @throws Exception
	 */
	public static byte[] decryptByRSA(PrivateKey pk, byte[] raw)
	{
		try
		{
			Cipher cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(cipher.DECRYPT_MODE, pk);
			int blockSize = cipher.getBlockSize();
			ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
			int j = 0;
			while (raw.length - j * blockSize > 0)
			{
				bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
				j++;
			}
			return bout.toByteArray();
		} catch (Exception e)
		{
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * 描述: 获取随机验证码
	 * 
	 * @author zhengqy
	 * @date 2014年4月22日
	 * @return
	 */
	public static String getCheckCode()
	{
		// 验证码的最小长度
		final int codeMinLength = 5;
		String code = "";
		Random r = new Random();
		String str = "";
		for (int i = 0; i < 4; i++)
		{
			str += "9";
		}
		int num = Integer.parseInt(str);
		while (code.length() < codeMinLength)
		{
			code += r.nextInt(num);
		}

		return code;
	}

	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String MD5(String input) {
		try {
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(input.getBytes());
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < md.length; i++) {
				String shaHex = Integer.toHexString(md[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static void main(String[] args) throws Exception
	{
//		String str = "vhom123456";
//		str = AuthxUtil.asciiToHex(str);
//		System.out.println(str);
//		str = AuthxUtil.hexToAscii(str);
//		System.out.println(str);
//
//		System.out.println(AuthxUtil.encryptByMd5("123456"));
//		str = "1234567887654321mohv";
//		StringBuffer sb = new StringBuffer();
//		sb.append(str);
//
//		System.out.println(sb.reverse().toString());
//		List<String> list = new ArrayList<String>();
//		list.add("123456789");
//		list.add("1111111111111111111111111");
//		System.out.println(AuthxUtil.encryptRememberMe(list));
//
//		KeyPair keyPair = AuthxUtil.generateRSAKeyPair(512);
//		String test = "vhom1234567887654321";
//		System.out.println(keyPair.getPublic());
//		System.out.println(keyPair.getPrivate());
//		byte[] en_test = AuthxUtil.encryptByRSA(keyPair.getPublic(), test.getBytes());
//		System.out.println(new String(en_test));
//		byte[] de_test = AuthxUtil.decryptByRSA(keyPair.getPrivate(), en_test);
//		System.out.println(new String(de_test));
		
		System.out.println(AuthxUtil.encryptByMd5("123456"));
		System.out.println(AuthxUtil.encryptByMd5("E10ADC3949BA59ABBE56E057F20F883E"));
	}

}
