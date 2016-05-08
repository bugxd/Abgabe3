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


        String s = "e2c690b0d6ff6086ad37af7e773a22d33c269e481eb0820b648408765c6c92a09865378e7b7f750cbe963d744f4d9ad5dc33d37344b68ddc7523329ac4624aa3c0c0661cfc18dc397a48c5ac67f9992e38cdb3dc7aa92634c0fd93c3b529539c2444508cda1d23ae09c433c733b04e4c64eb8f74b35aefe76746795a8cf052ce8b3a8dc6ff19264264b7e2867948a16424f7a973d618d38e6075b569e053c5bcdf5a01df8ff0111a7dbc9a12b41172d91a28234e4b63d6a60ea49225deb9a252e5c321abfc312704c617bb47ff4d9e54e4b438aaa612625b6143546bb64c3b70d4124edb8e534e264990743f622867d401a5fe1049c95269405fc0293eca415c";
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
