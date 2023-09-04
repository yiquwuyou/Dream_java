import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.Arrays;

public class TestPinyin4j {
    public static void main(String[] args) {
        // 这个方法的参数，是一个字符（一个汉字）
        // 返回值，是一个 String[] ，由于汉字里面存在多音字，这里就是把所有可能的读音，都列出来了
        String[] result = PinyinHelper.toHanyuPinyinStringArray('参');
        System.out.println(Arrays.toString(result));
    }
}
