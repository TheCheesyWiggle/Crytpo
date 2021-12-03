
package crytpo;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Date;


public class Transaction {
    
    private String sender; 
    private String reciever;
    private double amount;
    private double time;
    private String Hash;
    public byte[] signature; // this is to prevent anybody else from spending funds in our wallet.
    
    // <editor-fold defaultstate="collapsed" desc="Constructors"> 
    public Transaction(String sender, String reciever, double amount) {
        this.sender = sender;
        this.reciever = reciever;
        this.amount = amount;
        Date Time = new Date();
        this.time = Time.getTime();
        this.Hash = calculateHash(this);
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Calculate hash"> 
    public static String calculateHash(Transaction Transaction){
        //gets all the data in the object and converts it to a string
        String hashString = Transaction.getSender()
                + Transaction.getReciever()
                + Double.toString(Transaction.getAmount())
                + Double.toString(Transaction.getTime());
        
        //encodes it
        SHA256InJava hash = new SHA256InJava();
        String hashEncoded = hash.getSHA256Hash(hashString);
        //returns encoded block
        return hashEncoded;
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters & Setters"> 
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getHash() {
        return Hash;
    }

    public void setHash(String Hash) {
        this.Hash = Hash;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "Transaction{" + "sender=" + sender + ", reciever=" + reciever + ", amount=" + amount + ", time=" + time + ", Hash=" + Hash + ", signature=" + signature + '}';
    }
    
    
    
    //Signs all the data we dont wish to be tampered with.
    public void generateSignature(PrivateKey privateKey) {
	String data = sender + reciever + Double.toString(amount)	;
	signature = respository.applySig(privateKey,data);		
    }
    //Verifies the data we signed hasnt been tampered with
    public boolean verifiySignature() {
	String data = sender + reciever + Double.toString(amount);
	return respository.verifySig(sender, data, signature);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Signature methods">
       //RSA Signature and returns the result ( as bytes ).
	public static byte[] applySig(PrivateKey privateKey, String input) {
		Signature dsa;
		byte[] output = new byte[0];
		try {
			dsa = Signature.getInstance("RSA");
			dsa.initSign(privateKey);
			byte[] strByte = input.getBytes();
			dsa.update(strByte);
			byte[] realSig = dsa.sign();
			output = realSig;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return output;
	}
	
	//Verifies a String signature 
	public static boolean verifySig(PublicKey publicKey, String data, byte[] signature) {
		try {
			Signature Verify = Signature.getInstance("RSA");
			Verify.initVerify(publicKey);
			Verify.update(data.getBytes());
			return Verify.verify(signature);
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
        // </editor-fold>
}
