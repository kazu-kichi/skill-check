package q005;

import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 作業時間管理クラス
 * 自由に修正してかまいません
 */
@Data
public class WorkData {
    /** 社員番号 */
    private String number;

    /** 部署 */
    private String department;

    /** 役職 */
    private String position;

    /** Pコード */
    private String pCode;

    /** 作業時間(分) */
    private int workTime;

    WorkData(String str) {
        String[] arr = str.split(",");
        if (arr.length != 5 && NumberUtils.isCreatable(arr[4])) {
            throw new IllegalArgumentException();
        }
        number = arr[0];
        department = arr[1];
        position = arr[2];
        pCode = arr[3];
        workTime = NumberUtils.createInteger(arr[4]);
    }
}
