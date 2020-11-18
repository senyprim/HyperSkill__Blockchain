package blockchain;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("blockchain.ser");
        BlockChain blockChain=null;
//        if (file.isFile()){
//            BlockChain load=Repository.loadBlockChain(file);
//            if (load.isValidate()) {
//                blockChain=load;
//            }
//
//        }
        if (blockChain==null)
        {
            System.out.print("Enter how many zeros the hash must start with: ");
            blockChain = new BlockChain(new Scanner(System.in).nextInt());
        }
        for(int i=0;i<10;i++){
            blockChain.addBlock();
            Repository.saveBlockChain(blockChain,file);
        }
        if (blockChain.isValidate()){
                System.out.println(blockChain.toString(5));
            }
        }
}
