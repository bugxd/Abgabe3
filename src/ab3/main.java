package ab3;

import ab3.impl.Nachnamen.PasswordToolsImpl;

import java.security.MessageDigest;

/**
 * Created by Luki on 02.05.2016.
 */
public class main {
    public static void main(String[] args)throws Exception
    {
        PasswordToolsImpl pti = new PasswordToolsImpl();

        PasswordToolsImpl.printHexHash(pti.PBKDF2("password".getBytes(), "salt".getBytes(), 4096, 256));


        String s = "73ab53c7d08db6f56e89fb9d05bb9e1896e168307bf13777736747d8f16bfc9b79447fd47219a8ec3b914e97536373c0639c4c08a5e476d2871083ae34f0519f4340dee4145ce07dabc9e9fc2252a2016835ef79af3d79e8aea88976f5ffc53b7ca8b7015bd1c30024bded23ac3a2d492b1d79bc05b093182c5b1cf016c73b24736e3ed36e9f035e991711736e3cb79f6f8b175e8e344a182b58f561fec5f72458a1228725c099120fa4041b4c790adcaa210e5b43f40d000bab815497200bf23daa4541430496b295e47b0b5d6b07468efd8d542abe308b5d2116bfc16d161050973872034219a2e25ad8d30a5f0e09f3e4816ae9b161cf88cc9f4c690ff9f9";
        System.out.println(s.length());



        /*
        PasswordTools.SaltedHash sh = pti.createSaltedHash("password");

        if(pti.checkSaltedHash("password",sh))
            System.out.print("correct");
        else
            System.out.println("false");


        */

       /* String password = "123456";

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("Hex format : " + sb.toString());

        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<byteData.length;i++) {
            String hex=Integer.toHexString(0xff & byteData[i]);
            if(hex.length()==1) hexString.append('0');
            hexString.append(hex);
        }
        System.out.println("Hex format : " + hexString.toString());*/
    }
}
