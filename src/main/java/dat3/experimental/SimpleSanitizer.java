package dat3.experimental;

public class SimpleSanitizer {
    public static String simpleSanitize(String s){
        return s.replaceAll("<[^>]*>", "");
    }
}
