package game;
import sprite.Block;
import sprite.Sprite;
import sprite.Velocity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GeneralLevel implements LevelInformation {
    private Map<String,Object> map;
    private List<Block> blockList;
    public GeneralLevel(Map<String,Object>  map,List<Block> list){
        this.blockList = new ArrayList<>(list);
        this.map = new TreeMap<>(map);
    }
    public int numberOfBalls() {
        return (int) map.get("num_balls");
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return (List<Velocity>) map.get("ball_velocities");
    }

    @Override
    public int paddleSpeed() {
        return (int) map.get("paddle_speed");
    }

    @Override
    public int paddleWidth() {
        return (int) map.get("paddle_width");
    }

    @Override
    public String levelName() {
        return (String) map.get("level_name");
    }

    @Override
    public Sprite getBackground() {
        return (Sprite) map.get("background");
    }

    @Override
    public List<sprite.Block> blocks() {
        return new ArrayList<>(this.blockList);
    }

    @Override
    public int numberOfBlocksToRemove() {
        return (int) map.get("num_blocks");
    }
}
