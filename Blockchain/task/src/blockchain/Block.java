package blockchain;

import java.security.MessageDigest;
import java.util.*;

public class Block{
    public static String encode(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            /* Applies sha256 to our input */
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte elem: hash) {
                String hex = Integer.toHexString(0xff & elem);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public final int id;
    public final long timeStamp;
    public final String previousHash;
    public final String hash;

    public Block(int id,String previousHash ) {
        this.id=id;
        this.timeStamp=new Date().getTime();
        this.previousHash=previousHash;
        this.hash = encode (this.id + this.timeStamp + this.previousHash);
    }
    @Override
    public String toString(){
        return String.format(
                "Block:\n"+
                        "Id: %d\n"+
                        "Timestamp: %d\n"+
                        "Hash of the previous block:\n" +
                        "%s\n"+
                        "Hash of the block:\n" +
                        "%s\n"
                ,this.id,this.timeStamp,this.previousHash,this.hash);
    }
}