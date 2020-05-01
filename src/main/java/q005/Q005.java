package q005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Q005 データクラスと様々な集計
 *
 * 以下のファイルを読み込んで、WorkDataクラスのインスタンスを作成してください。
 * resources/q005/data.txt
 * (先頭行はタイトルなので読み取りをスキップする)
 *
 * 読み込んだデータを以下で集計して出力してください。
 * (1) 役職別の合計作業時間
 * (2) Pコード別の合計作業時間
 * (3) 社員番号別の合計作業時間
 * 上記項目内での出力順は問いません。
 *
 * 作業時間は "xx時間xx分" の形式にしてください。
 * また、WorkDataクラスは自由に修正してください。
 *
[出力イメージ]
部長: xx時間xx分
課長: xx時間xx分
一般: xx時間xx分
Z-7-31100: xx時間xx分
I-7-31100: xx時間xx分
T-7-30002: xx時間xx分
（省略）
194033: xx時間xx分
195052: xx時間xx分
195066: xx時間xx分
（省略）
 */
public class Q005 {

    private static InputStream openDataFile() {
        return Q005.class.getResourceAsStream("data.txt");
    }

    private static Optional<List<WorkData>> read(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(inputStream, Charset.forName("UTF-8")))) {

            return Optional.of(reader.lines()
                .skip(1)
                .map(WorkData::new)
                .collect(Collectors.toList()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        read(openDataFile())
            .ifPresent(v -> {
                //NOTE:性能重視なら自前で集計メソッド書いた方がいい

                //役職別
                v.stream()
                    .collect(
                        Collectors.groupingBy(WorkData::getPosition,
                            Collectors.summingLong(WorkData::getWorkTime)))
                    .forEach((x, y) -> System.out.println(x + ": " + format(y)));
                //P code
                v.stream()
                    .collect(
                        Collectors.groupingBy(WorkData::getPCode,
                            Collectors.summingLong(WorkData::getWorkTime)))
                    .forEach((x, y) -> System.out.println(x + ": " + format(y)));
                //社員番号
                v.stream()
                    .collect(
                        Collectors.groupingBy(WorkData::getNumber,
                            Collectors.summingLong(WorkData::getWorkTime)))
                    .forEach((x, y) -> System.out.println(x + ": " + format(y)));
                }
            );
    }

    private static String format(long minute) {
        return (minute / 60) + "時間" + (minute % 60) + "分";
    }
}
// 完成までの時間: 2時間