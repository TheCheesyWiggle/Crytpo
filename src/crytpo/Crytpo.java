
package crytpo;

import java.util.ArrayList;


public class Crytpo {
    
    //intializing arraylists 
    private static ArrayList<Block> chain = new ArrayList<Block>();
    private static ArrayList<Transaction> transactionPool = new ArrayList<Transaction> ();
    public static Blockchain blockchain = new Blockchain(chain,transactionPool);
    
    
    public static void main(String[] args) {
        Blockchain.genesisBlock(blockchain);
        menu();
        
        /*

        
        
        //testing transactions
        Transaction test = new Transaction(respository.getStringFromKey(walletA.publickey),respository.getStringFromKey(Miner.publickey),100);
        pendingTransactions.add(test);
        Transaction test1 = new Transaction(respository.getStringFromKey(walletA.publickey),respository.getStringFromKey(walletA.publickey),100);
        pendingTransactions.add(test1);
        
        //Create a test transaction from WalletA to Miner 
	Transaction transaction = new Transaction(respository.getStringFromKey(walletA.publickey),respository.getStringFromKey( Miner.publickey), 5);
	transaction.generateSignature(walletA.privatekey);
        
        
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
                       + "\n| 5)  Wallet Actions       |"
                       + "\n| 6)  Quit                 |"
                       + "\n|--------------------------|");
        System.out.println("Enter the number corresponding to your choice: ");
        try{
            int userInput = respository.userinputInt();
            switch(userInput){
                case 1:
                    menu();
                    break;
                case 2:
                    System.out.println("Double check to make sure you enter the correct address");
                    System.out.println("Enter wallet address:");
                    String walletAddress = respository.userinputString();
                    Blockchain.minePendingTransactions(blockchain , walletAddress); 
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
                    for(int i = 0; i<Blockchain.getBlockchain().size();i++){
                        System.out.println(Blockchain.getBlockchain().get(i).toString());
                        for(int j = 0; j<Blockchain.getBlockchain().get(i).getTranscation().size();j++){
                            System.out.println(Blockchain.getBlockchain().get(i).getTranscation().get(i).toString());
        
                        }
                    }
                    break;
                case 5:
                    walletActions();
                    break;
                case 6:
                    //save blockchain to a file 
                    //import blockchain when it reastarts
                    System.exit(0);
                    break;
            }
        }catch(Exception e){
            System.out.println(e);
            System.exit(0);
        }
        
    }
    
    public static void walletActions(){
        /*
        input wallet addresses to access funds
        
        
        
        
        
        
        
        */
        System.out.println("|--------------------------|"
                       + "\n| >>-----> Wallet <-----<< |"
                       + "\n|--------------------------|"
                       + "\n| 1)  Balance              |"
                       + "\n| 2)  Transfer             |"
                       + "\n| 3)  Menu                 |"
                       + "\n|--------------------------|");
        System.out.println("Enter the number corresponding to your choice: ");
        try{
            int userInput = respository.userinputInt();
            switch(userInput){
                case 1:
                    //show balance
                    break;
                case 2:
                    System.out.println("Enter the Public wallet address of the person you would like to send money too: ");
                    String recipientAddress = respository.userinputString();
                    System.out.println("Enter the Public wallet address of the person you would like to send money too: ");
                    String senderAddress = respository.userinputString();
                    System.out.println("Enter the amount of coins you would like to send: ");
                    int amount = respository.userinputInt();
                    Transaction transfer = new Transaction(senderAddress,recipientAddress,amount);
                    transactionPool.add(transfer);
                    System.out.println("Succesful Transaction!");
                    walletActions();
                    break;
                case 3:
                    
                    break;
                case 4:
                    menu();
                    break;
                
            } 
        }
        catch(Exception e){
                    
        }

    }
    

}
