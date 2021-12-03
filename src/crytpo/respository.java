/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crytpo;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 *
 * @author Finn
 */
public class respository {
    
    // <editor-fold defaultstate="collapsed" desc="Signature methods">
       //RSA Signature and returns the result ( as bytes ).
	public static byte[] applySig(PrivateKey privateKey, String input) {
		Signature rsa;
		byte[] output = new byte[0];
		try {
			rsa = Signature.getInstance("SHA1withRSA");
			rsa.initSign(privateKey);
			byte[] strByte = input.getBytes();
			rsa.update(strByte);
			byte[] realSig = rsa.sign();
			output = realSig;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return output;
	}
	
	//Verifies a String signature 
	public static boolean verifySig(String publicK, String data, byte[] signature) {
            try {
                //converts string to public key
                PublicKey publicKey = getKeyFromString(publicK);

                //verifies the signature  
                Signature Verify = Signature.getInstance("RSA");
                Verify.initVerify(publicKey);
                Verify.update(data.getBytes());
                return Verify.verify(signature);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
	}
        // </editor-fold>
        
    public static String getStringFromKey(Key key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
    }
    public static PublicKey getKeyFromString(String publicK){
        try {
            byte[] publicBytes = Base64.getDecoder().decode(publicK);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
