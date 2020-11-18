package blockchain;

import java.io.*;
import java.nio.file.Files;

public class Repository {
    public static void  saveBlockChain(BlockChain blockChain, File file) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file)))
        {
            oos.writeObject(blockChain);
        }
    }
    public static BlockChain loadBlockChain(File file) throws IOException, ClassNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)))
        {
            return (BlockChain)ois.readObject();
        }
    }
}
