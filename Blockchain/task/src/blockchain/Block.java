package blockchain;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Block{
    private static byte[] getSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(input.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return null;
        }
    }

    private static String arrayToString(byte[] array){
        return arrayToString(array,array.length);
    }
    private static String arrayToString(byte[] array,int count){
        StringBuilder stb = new StringBuilder();
        for(int index = 0; index < Math.min(count,array.length); index++){
            String digit = Integer.toHexString(0xff & array[index]);
            if (digit.length()==1){
                stb.append("0");
            }
            stb.append(digit);
        }
        return stb.toString();
    }

    public final int id;
    public final long timeStamp;
    public final String previousHash;
    public final String hash;
    public final long magicNumber;
    public final long timeGeneration;
    public final int zeros;

    public Block(int id,String previousHash,int zeros) {
        this.id=id;
        this.timeStamp=new Date().getTime();
        this.previousHash=previousHash;
        this.zeros = zeros;
        Random random = new Random();
        String str = id + "" + timeStamp + previousHash;
        //В одном байте два нуля
        int byteIntInZeros = (int)Math.ceil(zeros/2.0);
        //Начало строки для проверки
        String zeroString = "0".repeat(zeros);
        while(true){
            long rnd = Math.abs(random.nextLong());
            byte[] sha = getSHA256(str+rnd);
            if (arrayToString(sha,byteIntInZeros).startsWith(zeroString)){
                this.magicNumber=rnd;
                this.hash=arrayToString(sha);
                this.timeGeneration=(new Date().getTime()-timeStamp)/1000;
                break;
            }
        }
    }
    @Override
    public String toString(){
        return String.format(
                "Block:\n"+
                        "Id: %d\n"+
                        "Timestamp: %d\n"+
                        "Magic number: %d\n"+
                        "Hash of the previous block:\n" +
                        "%s\n"+
                        "Hash of the block:\n" +
                        "%s\n"+
                        "Block was generating for %d seconds\n"
                ,this.id,this.timeStamp,this.magicNumber,this.previousHash,this.hash,this.timeGeneration);
    }
}