package ab3.impl.Nachnamen;

import ab3.PasswordTools;


import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

public class PasswordToolsImpl implements PasswordTools {
	
	@Override
	public SaltedHash createSaltedHash(String password) {
		// TODO Auto-generated method stub


		SaltedHash sh = new SaltedHash();


		sh.setSalt(getNextSalt());
		sh.setHash(generateHash(password,sh.getSalt()));

		//print Hash as Hex String
		printHexHash(sh.getHash());

		return sh;
	}

	public static byte[] generateHash(String pw, byte[] salt){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			final byte[] all = concatArray(pw.getBytes(), salt);
			md.update(all);

		}
		catch (Exception ex){
			System.out.println(ex.toString());
		}
		return md.digest(pw.getBytes());
	}

	public static byte[] concatArray(byte[] arr1,byte[] arr2){
		byte[] all = new byte[arr1.length+arr2.length];

		for(int i = 0; i < arr1.length; i++){
			all[i] = arr1[i];
		}

		for(int i = 0; i < arr2.length; i++){
			all[i+arr1.length] = arr2[i];
		}

		return all;
	}

	public static void printHexHash(byte[] byteData){
		StringBuffer hexString = new StringBuffer();
		for (int i=0;i<byteData.length;i++) {
			String hex=Integer.toHexString(0xff & byteData[i]);
			if(hex.length()==1) hexString.append('0');
			hexString.append(hex);
		}
		System.out.println(hexString);
	}

	public static byte[] getNextSalt() {
		Random r = new SecureRandom();
		byte[] salt = new byte[16];
		r.nextBytes(salt);
		return salt;
	}

	@Override
	public boolean checkSaltedHash(String password, SaltedHash hash) {
		byte[] checkHash = generateHash(password, hash.getSalt());

		return chaeckArray(hash.getHash(), checkHash);
	}

	public static boolean chaeckArray(byte[] arr1,byte[] arr2){
		if(arr1.length != arr2.length)
			return false;

		for(int i = 0 ; i < arr1.length; i++){
			if(arr1[i] != arr2[i])
				return false;
		}

		return true;
	}

	@Override
	public byte[] PBKDF2(byte[] password, byte[] salt, int iterations, int dkLen) {
		// TODO Auto-generated method stub
		return null;
	}

}
