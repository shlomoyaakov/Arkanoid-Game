
import geometry.Point;
import geometry.Rectangle;
import sprite.Block;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class BlocksDefinitionReader {

    public static BlocksFromSymbolsFactory fromReader(java.io.Reader r) {
        Map<String, String> stringMap = new TreeMap<>();
        Map<String, Integer> spacers = new TreeMap<>();
        Map<String, BlockCreator> blockCreators = new TreeMap<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(r);
            String line = reader.readLine();
            while (line != null) {
                String[] splitLine = line.split(" ");
                if (splitLine[0].equals("#")) {
                    line = reader.readLine();
                    continue;
                }
                if (splitLine[0].equals("default")) {
                    mapDef(splitLine, stringMap);
                }
                if (splitLine[0].equals("bdef")) {
                    blockDef(splitLine, stringMap, blockCreators);
                }
                if (splitLine[0].equals("sdef")) {
                    spacerDef(splitLine, spacers);
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find the file");
        } catch (IOException e) {
            System.err.println("Failed reading the file" + ", message:" + e.getMessage());
            e.printStackTrace(System.err);
        } catch (Exception ex) {
            System.err.println("Wrong block definition format");
            System.exit(1);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing the file");
            }
        }
        return new BlocksFromSymbolsFactory(spacers, blockCreators);
    }

    public static void mapDef(String[] splitLine, Map<String, String> map) {
        try {
            for (int i = 1; i < splitLine.length; i++) {
                String[] keyAndValue = splitLine[i].split(":");
                map.put(keyAndValue[0], keyAndValue[1]);
            }
        } catch (Exception ex) {
            System.err.println("Wrong default format in blocks definition");
            System.exit(1);
        }
    }

    public static void spacerDef(String[] splitLine, Map<String, Integer> spacers) {
        try {
            String name = splitLine[1].split(":")[1];
            int width = Integer.parseInt(splitLine[2].split(":")[1]);
            spacers.put(name, width);
        } catch (Exception ex) {
            System.err.println("Wrong spacers definitions format");
            System.exit(1);
        }
    }

    public static void blockDef(String[] splitLine, Map<String, String> defMap, Map<String, BlockCreator>
            blockCreator) {
        try {
            Map<String, String> stringMap = new TreeMap<>(defMap);
            String symbol = splitLine[1].split(":")[1];
            for (int i = 1; i < splitLine.length; i++) {
                String[] keyAndValue = splitLine[i].split(":");
                stringMap.put(keyAndValue[0], keyAndValue[1]);
            }
            blockCreator.put(symbol, new BlockCreator() {
                @Override
                public Block create(int xpos, int ypos) {
                    double width = 0, height = 0;
                    if (stringMap.containsKey("width")) {
                        width = Double.parseDouble(stringMap.get("width"));
                    }
                    if (stringMap.containsKey("height")) {
                        height = Double.parseDouble(stringMap.get("height"));
                    }
                    Block b = new Block(new Rectangle(new Point((double) xpos, (double) ypos), width, height));
                    initialize(b);
                    return b;
                }

                private void initialize(Block b) {
                    Map<Integer, Color> color = new TreeMap<>();
                    Map<Integer, Image> img = new TreeMap<>();
                    b.setValue(Integer.parseInt(stringMap.get("hit_points")));
                    for (Map.Entry<String, String> entry : stringMap.entrySet()) {
                        if ((entry.getKey().contains("fill") && !(entry.getValue().contains("image")))) {
                            String[] fill = entry.getKey().split("-");
                            if (fill.length > 1) {
                                color.put(Integer.parseInt(fill[1]), new ColorsParser().
                                        colorFromString(entry.getValue()));
                            } else {
                                color.put(1, new ColorsParser().colorFromString(entry.getValue()));
                            }
                        }
                        if (entry.getKey().contains("fill") && (entry.getValue().contains("image"))) {
                            String[] fill = entry.getKey().split("-");
                            Image image = new LoadImg(entry.getValue()).getImg();
                            if (fill.length > 1) {
                                img.put(Integer.parseInt(fill[1]), image);
                            } else {
                                img.put(1, image);
                            }
                        }

                    }
                    if (!color.containsKey(1) && !img.containsKey(1)) {
                        System.err.println("Wrong block definition");
                        System.exit(1);
                    }
                    b.setColor(color);
                    if (!img.isEmpty()) {
                        b.setImg(img);
                    }
                    if (stringMap.containsKey("stroke")) {
                        Color stroke = new ColorsParser().colorFromString(stringMap.get("stroke"));
                        b.setStroke(stroke);
                    }
                }
            });

        } catch (Exception ex) {
            System.err.println("Wrong block definition");
            System.exit(1);
        }

    }
}