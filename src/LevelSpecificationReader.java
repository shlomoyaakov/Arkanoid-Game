import game.GeneralLevel;
import game.LevelInformation;
import sprite.Block;
import sprite.Sprite;
import sprite.StandardBackground;
import sprite.Velocity;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;


public class LevelSpecificationReader {
    private List<LevelInformation> levelList;
    private Map<String, Object> map;
    private List<Block> blockList;
    private BlocksFromSymbolsFactory blockSym;
    private BufferedReader reader;

    public LevelSpecificationReader() {
        this.levelList = new ArrayList<>();
        this.map = new TreeMap<>();
        this.blockList = new ArrayList<>();
    }

    public List<LevelInformation> fromReader(java.io.Reader r) {
        List<String> stringList = stringsFromReader(r);
        while (!stringList.isEmpty()) {
            understandContext(stringList);
            stringList.clear();
            stringList = stringsFromReader(r);
        }
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            System.err.println("Failed closing the file");
        }
        return this.levelList;
    }

    private List<String> stringsFromReader(java.io.Reader r) {
        List<String> list = new ArrayList<>();
        Boolean flag = true;
        try {
            if (this.reader == null) {
                reader = new BufferedReader(r);
            }
            // print each read line
            String line = reader.readLine();
            while (line != null) {
                if (line.equals("START_LEVEL")) {
                    line = reader.readLine();
                    flag = false;
                    continue;
                }
                if (flag) {
                    line = reader.readLine();
                    continue;
                }
                if (line.equals("END_LEVEL")) {
                    break;
                }
                list.add(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file");
        } catch (IOException e) {
            System.err.println("Failed reading file:"
                    + ", message:" + e.getMessage());
            e.printStackTrace(System.err);
        }
        return list;
    }

    public void understandContext(List<String> list) {
        this.blockSym = null;
        this.map.clear();
        List<String> blockContext = new ArrayList<>();
        Boolean flag = false;
        try {
            for (String line : list) {
                String[] splitLine = line.split(":");
                if (splitLine[0].equals("block_definitions")) {
                    makeBlockDef(splitLine[1]);
                    continue;
                }
                if (splitLine[0].equals("background")) {
                    mapBackground(splitLine);
                    continue;
                }
                if (splitLine[0].equals("ball_velocities")) {
                    mapBalls(splitLine[0], splitLine[1]);
                    continue;
                }
                if (splitLine[0].equals("START_BLOCKS")) {
                    flag = true;
                    continue;
                }
                if (splitLine[0].equals("END_BLOCKS")) {
                    flag = false;
                    mapBlock(blockContext);
                    continue;
                }
                if (flag) {
                    blockContext.add(line);
                    continue;
                }
                if (splitLine[0].equals("level_name")) {
                    this.map.put(splitLine[0], splitLine[1]);
                } else {
                    this.map.put(splitLine[0], Integer.parseInt(splitLine[1]));
                }
            }
        } catch (Exception ex) {
            System.out.println("Wrong level definition format");
            System.exit(1);
        }
        LevelInformation l = makeLevel();
        this.levelList.add(l);
    }

    private void mapBalls(String key, String v) {
        List<Velocity> velocityList = new ArrayList<>();
        String[] velocities = v.split(" ");
        try {
            for (int i = 0; i < velocities.length; i++) {
                String[] velocity = velocities[i].split(",");
                velocityList.add(Velocity.fromAngleAndSpeed(Double.parseDouble(velocity[0])
                        , Double.parseDouble(velocity[1])));
            }
        } catch (Exception ex) {
            System.out.println("Wrong ball velocities");
            System.exit(1);
        }
        this.map.put(key, velocityList);
        this.map.put("num_balls", velocityList.size());
    }

    private void makeBlockDef(String line) {
        java.io.Reader reader = null;
        try {
            reader = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(line));
            this.blockSym = BlocksDefinitionReader.fromReader(reader);
        } catch (Exception e) {
            System.err.println("Unable to find file: " + line);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + line);
            }
        }

    }

    private void mapBackground(String[] splitLine) {
        try {
            if (splitLine[1].contains("image")) {
                Image image = new LoadImg(splitLine[1]).getImg();
                StandardBackground background = new StandardBackground(Color.lightGray);
                background.setImg(image);
                this.map.put(splitLine[0], background);
            } else {
                Color color = new ColorsParser().colorFromString(splitLine[1]);
                this.map.put(splitLine[0], new StandardBackground(color));
            }
        }catch (Exception ex) {
            System.out.println("unable to load background");
            System.exit(1);
        }

    }

    private void mapBlock(List<String> list) {
        try{
            this.blockList.clear();
            int x = (int) (this.map.get("blocks_start_x"));
            int y = (int) this.map.get("blocks_start_y");
            for (String s : list) {
                char[] charArray = s.toCharArray();
                for (char c : charArray) {
                    String check = "" + c + "";
                    if (this.blockSym.isSpaceSymbol(check)) {
                        x = x + this.blockSym.getSpaceWidth(check);
                    } else {
                        Block b = this.blockSym.getBlock(check, x, y);
                        this.blockList.add(b);
                        x = x + (int) b.getWidth();
                    }
                }
                x = (int) (this.map.get("blocks_start_x"));
                y = y + (int) this.map.get("row_height");
            }
        }catch (Exception ex) {
            System.out.println("unable to create blocks");
            System.exit(1);
        }
    }
/*
    private LevelInformation makeLevel() {
        return new GeneralLevel(this.map, this.blockList);
    }
    */
private LevelInformation makeLevel() {
    return new LevelInformation() {
        public int numberOfBalls() {
            return (int) map.get("num_balls");
        }
        private List<Block> list = new ArrayList<>(blockList);
        private Map<String,Object> defLevel = new HashMap<>(map);
        @Override
        public List<Velocity> initialBallVelocities() {
            return (List<Velocity>) map.get("ball_velocities");
        }

        @Override
        public int paddleSpeed() {
            return (int) defLevel.get("paddle_speed");
        }

        @Override
        public int paddleWidth() {
            return (int) defLevel.get("paddle_width");
        }

        @Override
        public String levelName() {
            return (String) defLevel.get("level_name");
        }

        @Override
        public Sprite getBackground() {
            return (Sprite) defLevel.get("background");
        }

        @Override
        public List<sprite.Block> blocks() {
            for(Block b:list) {
                b.restart();
            }
            return this.list;
        }

        @Override
        public int numberOfBlocksToRemove() {
            return (int) defLevel.get("num_blocks");
        }
    };
}
}