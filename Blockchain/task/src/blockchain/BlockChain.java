package blockchain;

import java.util.ArrayList;
import java.util.List;

public class BlockChain{
    private final List<Block> _listBlock;

    public List<Block> getBlockChain(){
        return _listBlock;
    }

    public int size(){
        return _listBlock.size();
    }

    public void addBlock(){
        String previousHash = size() > 0
                ?getBlock(size()-1).hash
                :"0";
        _listBlock.add(new Block(size()+1,previousHash));
    };

    public Block getBlock(int index){
        return _listBlock.get(index);
    }

    public BlockChain(){
        _listBlock=new ArrayList<>();
    }

    public boolean isValidate(){
        String previousHash="0";
        for(Block block:this._listBlock){
            if (block.previousHash!=previousHash) return false;
            previousHash=block.hash;
        }
        return true;
    }

    public String toString(){return toString(size());}
    public String toString(int count){
        StringBuilder stb = new StringBuilder();
        for(int index = 0; index < Math.min(size(),count); index++){
            stb.append(getBlock(index).toString());
            stb.append("\n");
        }
        return stb.toString();
    }
}
