import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public boolean isAlienSorted_(String[] words, String order) {
        int[] orderMap = new int[26];
        for (int i = 0; i < order.length(); i++){
            orderMap[order.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < words.length - 1; i++) {

            for (int j = 0; j < words[i].length(); j++) {
                // If we do not find a mismatch letter between words[i] and words[i + 1],
                // we need to examine the case when words are like ("apple", "app").
                if (j >= words[i + 1].length()) return false;

                if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                    int currentWordChar = words[i].charAt(j) - 'a';
                    int nextWordChar = words[i + 1].charAt(j) - 'a';
                    if (orderMap[currentWordChar] > orderMap[nextWordChar]) return false;
                        // if we find the first different letter and they are sorted,
                        // then there's no need to check remaining letters
                    else break;
                }
            }
        }

        return true;
    }
    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < order.length(); i++)
            map.put(order.charAt(i), i);

        main:
        for (int i = 0; i < words.length - 1; i++){
            int l1 = words[i].length();
            int l2 = words[i + 1].length();
            int j = 0, k = 0;

            while (j < l1 && k < l2){
                if (map.get(words[i].charAt(j)) < map.get(words[i + 1].charAt(k)))
                    continue main;
                if (map.get(words[i].charAt(j)) > map.get(words[i + 1].charAt(k)))
                    return false;
                j++;
                k++;
            }
            if (l1 > l2)
                return false;
        }
        return true;
    }
}