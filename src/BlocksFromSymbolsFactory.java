import sprite.Block;

import java.util.Map;

public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacers;
    private Map<String, BlockCreator> blockCreatorMap;

    public BlocksFromSymbolsFactory(Map<String, Integer> spacers, Map<String, BlockCreator> blockCreatorMap) {
        this.blockCreatorMap = blockCreatorMap;
        this.spacers = spacers;
    }

    // returns true if 's' is a valid space symbol.
    public boolean isSpaceSymbol(String s) {
        if(this.spacers.containsKey(s)){
            return true;
        }
        return false;
    }

    // returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {
        if(this.blockCreatorMap.containsKey(s)){
            return true;
        }
        return false;
    }

    // Return a block according to the definitions associated
    // with symbol s. The block will be located at position (xpos, ypos).
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreatorMap.get(s).create(xpos,ypos);
    }

    // Returns the width in pixels associated with the given spacer-symbol.
    public int getSpaceWidth(String s) {
        return this.spacers.get(s);
    }
}