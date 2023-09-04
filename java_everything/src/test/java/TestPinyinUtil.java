import Util.PinyinUtil;

public class TestPinyinUtil {
    public static void main(String[] args) {
        System.out.println(PinyinUtil.get("汤老湿", true));
        System.out.println(PinyinUtil.get("汤老湿", false));
        System.out.println(PinyinUtil.get("红鲤鱼绿鲤鱼与驴", false));
        System.out.println(PinyinUtil.get("红鲤鱼绿鲤鱼与驴", true));
    }
}
