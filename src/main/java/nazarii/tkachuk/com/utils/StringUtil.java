package nazarii.tkachuk.com.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class StringUtil {
    private StringUtil() {
        throw new UnsupportedOperationException();
    }

    public static String stringTrim(String line){
        List<String> stringList = Arrays.asList(line.split(","));
        List<String> finalList = new ArrayList<>();
        for (String item: stringList){
            finalList.add(item.trim());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<finalList.size()-1; i++){
            sb.append(finalList.get(i)+",");
        }
        return sb.append(finalList.get(finalList.size()-1)).toString();
    }
}
