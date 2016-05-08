package ab3.impl.Nachnamen;

import ab3.PasswordTools;


import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordToolsImpl implements PasswordTools {

	public static final int hLen = 32;

	@Override
	public SaltedHash createSaltedHash(String password) {
		SaltedHash sh = new SaltedHash();

		sh.setSalt(getNextSalt());
		sh.setHash(generateHash(password.getBytes(),sh.getSalt()));

		//print Hash as Hex String
		printHexHash(sh.getHash());

		return sh;
	}

	/* /
	* Generate Hash with salt
	* concatenate password and salt in one array and generate hash with MessageDigest Class
	* */
	public static byte[] generateHash(byte[] pw, byte[] salt){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			final byte[] all = concatArray(pw, salt);
			md.update(all);

		}
		catch (Exception ex){
			System.out.println(ex.toString());
		}
		return md.digest(pw);
	}

	/* /
	 * Concatenates two arrays
	 * */
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

	/*
	* Print password hash as hex string for testing
	* */
	public static void printHexHash(byte[] byteData){
		StringBuffer hexString = new StringBuffer();
		for (int i=0;i<byteData.length;i++) {
			String hex=Integer.toHexString(0xff & byteData[i]);
			if(hex.length()==1) hexString.append('0');
			hexString.append(hex);
		}
		System.out.println(hexString);
	}

	/*
	* Generate random salt with SecureRandom Class
	* */
	public static byte[] getNextSalt() {
		Random r = new SecureRandom();
		byte[] salt = new byte[16];
		r.nextBytes(salt);
		return salt;
	}

	@Override
	public boolean checkSaltedHash(String password, SaltedHash hash) {
		byte[] checkHash = generateHash(password.getBytes(), hash.getSalt());

		return checkArray(hash.getHash(), checkHash);
	}

	/*
	* Compares two arrays
	* return true if equals
	* */
	public static boolean checkArray(byte[] arr1,byte[] arr2){
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
		if(dkLen > (Math.pow(2,32) - 1) * hLen){
            System.out.println("derived key too long");
			return null;
		}

        //the number of hLen-byte blocks in the derived key
        //round up to the next greater or equal integer
		int l =  (dkLen - 1) / hLen + 1;
        //the number of byte in the last block
		int r = dkLen - (l - 1) * hLen;//l minus eins

        byte[] dk = getT(password,salt,iterations,1);
        for(int i = 2; i < dkLen; i++){
            byte[] T_I =  getT(password,salt,iterations,i);
            byte[] dk_help = dk;
            dk = concatArray(dk_help,T_I);

        }

        byte[] DK = getArrayValues(dk, dkLen);

        if(DK != null)
		    return DK;
        else
            return null;
	}

    public byte[] getArrayValues(byte[] arr, int x){
        if(arr.length < x)
            return null;

        byte[] ret = new byte[x];
        for(int i = 0; i < x; i++){
            ret[i] = arr[i];
        }

        return ret;
    }

    /*
    * recursive implementation to get block t_i from DK
    * */
    public byte[] getT(byte[] P, byte[] S, int c, int i){


        byte[] u_old = calculateU_1(P,S,c,getByteOfInt(i));
        byte[] t = u_old;
        for(int j = 2; j < c; j++){
            byte[] u_new = generateHash(P,u_old);
            t = xorArray(t,u_new);
            u_old = u_new;
        }

        return t;
    }

    public byte[] calculateU_1(byte[] P, byte[] S, int c, byte[] i){
        return generateHash(P, concatArray(S,i));
    }

    /*
    * return first xor second for each byte
    * */
    public byte[] xorArray(byte[] first, byte[] second){
        byte[] result = new byte[Math.min(first.length, second.length)];

        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) (((int) first[i]) ^ ((int) second[i]));
        }

        return result;
    }

    /*
    * returns int value in 4 byte
    * */
    public byte[] getByteOfInt(int i){
        return ByteBuffer.allocate(4).putInt(i).array();
    }

}
