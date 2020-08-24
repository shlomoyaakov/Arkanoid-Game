import java.awt.Color;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorsParser {
    public ColorsParser() {

    }

    // parse color definition and return the specified color.
    public java.awt.Color colorFromString(String s) {
        s = s.substring(s.indexOf("color") + 6, s.length() - 1);
        Color color = null;
        try {
            if (!s.contains("RGB")) {
                Field field = Class.forName("java.awt.Color").getField(s);
                color = (Color) field.get(null);
            } else {
                Pattern c = Pattern.compile("RGB *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
                Matcher m = c.matcher(s);
                if (m.matches()) {
                    color = new Color(Integer.valueOf(m.group(1)),
                            Integer.valueOf(m.group(2)),
                            Integer.valueOf(m.group(3)));
                }
            }
        } catch (Exception e) {
        System.out.println("Bad color!");
        System.exit(1);
    }
        return color;
    }
}