package q002;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;

@Data
@NoArgsConstructor(access= AccessLevel.PRIVATE)
class Item {
    private Integer id;
    private String name;

    /**
     * @param str "ID,名字" の形式
     */
    Item(String str) {
        String[] arr = str.split(",");
        if (arr.length != 2 && NumberUtils.isCreatable(arr[0])) {
            throw new IllegalArgumentException();
        }
        id = NumberUtils.createInteger(arr[0]);
        name = arr[1];
    }

    @Override
    public String toString() {
        return id + "," + name;
    }
}
