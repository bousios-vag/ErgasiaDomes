
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

// http://www.java2s.com/Code/Java/File-Input-Output/Readfilecharacterbycharacter.htm
public class TagMatching {

    public static String cleanTag(String tag) {
        if (tag.contains(" ")) {
            String[] words = tag.split(" ");
            return words[0] + ">";
        } else {
            return tag;
        }
    }

    public static boolean tags_match(String tag1, String tag2) {
        String cleanedtag1 = cleanTag(tag1);
        String cleanedtag2 = cleanTag(tag2).replace("</", "<");

        return cleanedtag1.equals(cleanedtag2);
    }

    public static void main(String[] args) {
        StringStack stack = new StringStackImpl();

        File file = new File("example.html");
        if (!file.exists()) {
            System.out.println(args[0] + " does not exist.");
            return;
        }

        try {
            FileInputStream fis = new FileInputStream(file);

            char previous = ' ';
            char current = ' ';
            String buf = "";

            while (fis.available() > 0) {
                previous = current;
                current = (char) fis.read();
                
                System.out.println(current);

                if (current == '<') {
                    buf = "<";
                    continue;
                }

                if (current == '>') {
                    if (buf.charAt(0) == '<') {
                        buf = buf + current;

                        String tag = buf;

                        if (buf.startsWith("<!")) {
                            buf = "";
                            continue;
                        }

                        if (tag.startsWith("</")) {
                            String cleanedtag = cleanTag(tag);
                            String toptag = stack.pop();

                            if (!tags_match(toptag, cleanedtag)) {
                                stack.printStack(System.out);
                                
                                System.out.println("mismatch: " + cleanedtag + " vs " + toptag);
                                
                                System.exit(-1);
                            }
                        } else {
                            String cleanedtag = cleanTag(tag);
                            stack.push(tag);
                        }
                    }
                }
                buf = buf + current;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
