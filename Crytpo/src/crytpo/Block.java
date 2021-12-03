
package crytpo;

import java.util.ArrayList;
import java.security.MessageDigest;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;


public class Block {
    
    private int Index; //block number
    private  ArrayList<Transaction> Transcation; //transaction data
    private double time; //time block was created
    private String prevHash;//previous hash
    private String Hash;//hash
    private int Nonce;//nonce
    
    // <editor-fold defaultstate="collapsed" desc="Constructors"> 
    public Block(int Index, ArrayList<Transaction> Transcation) {
        this.Index = Index;
        this.Transcation = Transcation;
        Date Time = new Date();
        this.time = Time.getTime();
        this.prevHash = prevHash;
        this.Hash = calculateHash(this);
        this.Nonce = 0;
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">  
    public int getIndex() {
        return Index;
    }

    public void setIndex(int Index) {
        this.Index = Index;
    }

    public ArrayList<Transaction> getTranscation() {
        return Transcation;
    }

    public void setTranscation(ArrayList<Transaction> Transcation) {
        this.Transcation = Transcation;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }

    public String getHash() {
        return Hash;
    }

    public void setHash(String Hash) {
        this.Hash = Hash;
    }

    public int getNonce() {
        return Nonce;
    }

    public void setNonce(int Nonce) {
        this.Nonce = Nonce;
    }
    
    
    // </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="Block attributes to string"> 
    @Override
    public String toString(){
        return "Block"+Index + "{Transcation = " + Transcation + ", time = " + time + ", prevHash = " + prevHash + ", Hash = " + Hash + ", Nonce = " + Nonce + '}';
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Calculate block hash">
    public static String calculateHash(Block Block) {
        String hashTransactions = "";
        
        //loops through the transaction arraylist int the block
        for(int i = 0; i<(Block.getTranscation()).size();i++){
            
            //gets the transaction its on in the list
            Transaction Transaction = Block.getTranscation().get(i);
            //gets the transaction hash
            hashTransactions = hashTransactions + Transaction.getHash();
        }
        
        //gets all the data in the object and converts it to a string
        String hashString = Integer.toString(Block.getNonce())+Double.toString(Block.getTime())+hashTransactions+Block.getPrevHash()+Integer.toString(Block.getIndex());
        //encodes it
        SHA256InJava hash = new SHA256InJava();
        String hashEncoded = hash.getSHA256Hash(hashString);
        //returns encoded block
        return hashEncoded;
    }
    // </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="Mine Block"> 
    public static void mineBlock(Block Block,int difficulty){
        /*
        ArrayList<Integer> Array = new ArrayList<Integer>();
        
        for(int i = 0;i<difficulty;i++){
            Array.add(i);
        }
        */
        
        String hashPuzzle = "1234";
        
        //loops until it finds the hash we are looking for
        while(!Block.getHash().substring(0, 4).equals(hashPuzzle)){
            //updates the nonce
            Block.setNonce(Block.getNonce()+1);
            //updates the hash
            Block.setHash(Block.calculateHash(Block));
            //outputs for testing purposes
            System.out.println("Nonce: "+Block.getNonce());
            System.out.println("Hash Attempt: "+Block.getHash());
            System.out.println("Hash Wanted: "+hashPuzzle);
        }
        System.out.println("Block Mined");
    }
    // </editor-fold>
}
