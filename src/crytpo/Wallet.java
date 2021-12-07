
package crytpo;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;



public class Wallet {
    
    public static PrivateKey privatekey;
    public static PublicKey publickey;
    
    
    public static void generateKeyPair() {
        try {
                KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
                keyGen.initialize(2048);
                KeyPair pair = keyGen.generateKeyPair();
                // Set the public and private keys from the keyPair
                privatekey = pair.getPrivate();
                publickey = pair.getPublic();       
        }
        catch(Exception e) {
                throw new RuntimeException(e);
        }
    }
    

    
}
