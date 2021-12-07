/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crytpo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Finn
 */
public class Crytpo {
    
    //intializing arraylists 
    //public static ArrayList<Block> Blockchain = new ArrayList<Block>();
    public static ArrayList<Transaction> transactionPool = new ArrayList<Transaction> ();

    
    
    public static void main(String[] args) {
        menu();
        
        /*
        Wallet walletA = new Wallet();
        walletA.generateKeyPair();
        Wallet miner= new Wallet();
        miner.generateKeyPair();
        
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
        */
    }
    
    public static void menu(){
        System.out.println("|--------------------------|"
                       + "\n| >>------> Menu <------<< |"
                       + "\n|--------------------------|"
                       + "\n| 1)  Menu                 |"
                       + "\n| 2)  Mine                 |"
                       + "\n| 3)  Generate wallet      |"
                       + "\n| 4)  Display Blockchain   |"
                       + "\n| 5)  View wallet Balance  |"
                       + "\n| 6)  Quit                 |"
                       + "\n|--------------------------|");
        System.out.println("Enter the number corresponding to your choice: ");
        try{
            Scanner input = new Scanner(System.in);
            int userInput = input.nextInt();
            switch(userInput){
                case 1:
                    menu();
                    break;
                case 2:
                    System.out.println("Double check to make sure you enter the correct address");
                    System.out.println("Enter wallet address:");
                    String walletAddress = input.next();
                    Blockchain.minePendingTransactions(transactionPool , walletAddress); 
                    break;
                case 3:
                    //generates wallet
                    Wallet walletA = new Wallet();
                    walletA.generateKeyPair();
                    //outputs public and private key
                    System.out.println("Public Key: "+respository.getStringFromKey(walletA.publickey)
                        +"\nPrivate Key: "+respository.getStringFromKey(walletA.privatekey));
                    break;
                case 4:
                    System.out.println("4");
                    break;
                case 5:
                    System.out.println("5");
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
        }catch(Exception e){
            System.out.println(e);
            System.exit(0);
        }
        
    }
}
