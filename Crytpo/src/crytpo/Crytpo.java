/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crytpo;

import java.util.ArrayList;

/**
 *
 * @author Finn
 */
public class Crytpo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //intializing arraylists        
        ArrayList<Block> chain = new ArrayList<Block>();
        ArrayList<Transaction> pendingTransactions = new ArrayList<Transaction> ();
        
        
        
        //testing wallets
        Wallet walletA;
        Wallet Miner;
        walletA = new Wallet();
	Miner = new Wallet();
        walletA.generateKeyPair();
        Miner.generateKeyPair();
                
        System.out.println(respository.getStringFromKey(walletA.publickey));
        System.out.println("///////////////////////////////////");
        System.out.println(respository.getStringFromKey(walletA.privatekey));
        
        //testing blockchain
        Blockchain Blockchain = new Blockchain(chain,pendingTransactions);
        Blockchain.genesisBlock(Blockchain);
        
        //testing transactions
        Transaction test = new Transaction(respository.getStringFromKey(walletA.publickey),respository.getStringFromKey(Miner.publickey),100);
        pendingTransactions.add(test);
        Transaction test1 = new Transaction(respository.getStringFromKey(walletA.publickey),respository.getStringFromKey(walletA.publickey),100);
        pendingTransactions.add(test1);
        
        //Create a test transaction from WalletA to Miner 
	Transaction transaction = new Transaction(respository.getStringFromKey(walletA.publickey),respository.getStringFromKey( Miner.publickey), 5);
	transaction.generateSignature(walletA.privatekey);
        
        //testing mining
        Blockchain.setTransactions(pendingTransactions);

        Blockchain.minePendingTransactions(Blockchain,respository.getStringFromKey(Miner.publickey));        
        for(int i = 0; i<Blockchain.getBlockchain().size();i++){
            System.out.println(Blockchain.getBlockchain().get(i).toString());
            for(int j = 0; j<Blockchain.getBlockchain().get(i).getTranscation().size();j++){
                System.out.println(Blockchain.getBlockchain().get(i).getTranscation().get(i).toString());
            }
        }
        
        System.out.println("Length: "+Blockchain.getBlockchain().size());
    }
    
}
