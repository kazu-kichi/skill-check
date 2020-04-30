package q003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Q003 集計と並べ替え
 *
 * 以下のデータファイルを読み込んで、出現する単語ごとに数をカウントし、アルファベット辞書順に並び変えて出力してください。
 * resources/q003/data.txt
 * 単語の条件は以下となります
 * - "I"以外は全て小文字で扱う（"My"と"my"は同じく"my"として扱う）
 * - 単数形と複数形のように少しでも文字列が異れば別単語として扱う（"dream"と"dreams"は別単語）
 * - アポストロフィーやハイフン付の単語は1単語として扱う（"isn't"や"dead-end"）
 *
 * 出力形式:単語=数
 *
[出力イメージ]
（省略）
highest=1
I=3
if=2
ignorance=1
（省略）

 * 参考
 * http://eikaiwa.dmm.com/blog/4690/
 */
public class Q003 {
    /**
     * データファイルを開く
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q003.class.getResourceAsStream("data.txt");
    }

    // I意外を小文字にする
    private static String getLowerCase(String value) {
        if (!"I".equals(value)) {
            return value.toLowerCase();
        }
        return value;
    }

    // 出現数をカウント
    private static void countMerge(Map<String, Integer> map, String value) {
        if (map.containsKey(value)) {
            int count = map.get(value);
            map.put(value, ++count);
        } else {
            map.put(value, 1);
        }
    }

    private static Optional<Map<String, Integer>> read(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, Charset.forName("UTF-8")))) {
            Map<String, Integer> result = new HashMap<>();

            reader.lines()
                    .forEach(line ->
                            Arrays.stream(line.split("[ ,.;]"))
                                    .filter(s -> !s.isEmpty())
                                    .map(Q003::getLowerCase)
                                    .forEach(v -> countMerge(result, v))
                    );

            return Optional.of(result);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        read(openDataFile())
                .ifPresent(v ->
                        v.entrySet().stream()
                                .sorted(Comparator.comparing(Map.Entry::getKey, String.CASE_INSENSITIVE_ORDER))
                                .forEach(System.out::println)
                );
    }
}
// 完成までの時間: 2時間