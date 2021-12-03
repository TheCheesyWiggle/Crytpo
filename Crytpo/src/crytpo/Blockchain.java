
package crytpo;

import java.util.ArrayList;
import java.util.Date;

public class Blockchain {
    
    private static ArrayList<Block> Blockchain;
    private static ArrayList<Transaction> Transactions;
    private static int difficulty;
    private static int minerReward;
    private static int blockSize;
    
    // <editor-fold defaultstate="collapsed" desc="Constructors"> 
    public Blockchain(ArrayList<Block> Blockchain, ArrayList<Transaction> pendingTransactions) {
        this.Blockchain = Blockchain;
        this.Transactions = pendingTransactions;
        this.difficulty = 3;
        this.minerReward = 50;
        this.blockSize = 10;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public ArrayList<Block> getBlockchain() {
        return Blockchain;
    }

    public void setBlockchain(ArrayList<Block> Blockchain) {
        this.Blockchain = Blockchain;
    }

    public ArrayList<Transaction> getTransactions() {
        return Transactions;
    }

    public void setTransactions(ArrayList<Transaction> Transactions) {
        this.Transactions = Transactions;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getMinerReward() {
        return minerReward;
    }

    public void setMinerReward(int minerReward) {
        this.minerReward = minerReward;
    }

    public int getBlockSize() {
        return blockSize;
    }
    
    public void setBlockSize(int blockSize) {    
        this.blockSize = blockSize;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Get Last Block">
    public static Block getLastBlock(ArrayList<Block> Chain) {
        return Chain.get(Chain.size()-1);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Add Block"> 
    public static void addBlock(Block Block,Blockchain Blockchain){
        if(Blockchain.getBlockchain().size()>0){
            Block.setPrevHash(getLastBlock(Blockchain.getBlockchain()).getHash());
        }
        else{
            Block.setPrevHash("none");
        }
        Blockchain.getBlockchain().add(Block);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Genesis Block"> 
    public static void genesisBlock(Blockchain Blockchain){
        ArrayList<Transaction> Transaction = new ArrayList<Transaction>();
        Date Time = new Date();
        Transaction Setup = new Transaction("","",100);
        Transaction.add(Setup);
                
        Block genesis = new Block(0,Transaction);
        Blockchain.addBlock(genesis,Blockchain);
    }
    // </editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Mine pending transactions">
    
    public static void minePendingTransactions(Blockchain Blockchain,String minerAddress){
        //grabs the length of the arraylist PendingTransactions within the blockchain
        int lenPT = Blockchain.getTransactions().size();
        
        //if the length isnt larger than one it doesnt mine the block
        if(lenPT <= 1){
            System.out.println("not enough transactions");
        }
        else{
            //loops in block sized increments
            //if the transaction list is bigger than one block it doesnt crash
            for(int i = 0;i<lenPT;i = i+ Blockchain.getBlockSize()){
                
                int end = i+ Blockchain.getBlockSize();
                
                if(i >= lenPT){
                    end = lenPT;
                }
                
                ArrayList<Transaction> TransactionSlice =  new ArrayList<Transaction>();
                for(int j = 0;j<Blockchain.getTransactions().size();j++){
                    TransactionSlice.add(Blockchain.getTransactions().get(j));
                }
                
                Block newBlock = new Block(Blockchain.getBlockchain().size(),TransactionSlice);
                
                newBlock.setPrevHash(getLastBlock(Blockchain.getBlockchain()).getHash());
                Block.mineBlock(newBlock, Blockchain.getDifficulty());
                
                Blockchain.getBlockchain().add(newBlock);
                
                System.out.println("Added block to blockchain");
                //need to set up system wallet
                Transaction payMiner = new Transaction("", minerAddress, Blockchain.getMinerReward());
                ArrayList<Transaction> UpdatedTransactionList =  new ArrayList<Transaction>();
                UpdatedTransactionList.add(payMiner);
                Blockchain.setTransactions(UpdatedTransactionList);    
            }

        }
    
    }
    // </editor-fold>
}
