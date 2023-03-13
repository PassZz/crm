package crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
    对Date类型数据进行处理的工具类
 */
public class DateUtils {
    /**
     * 对指定的date对象进行格式化
     * @param date 日期数据
     * @return 返回指定日期数据的字符串类型
     */
    public static String formatDateTime(Date date){
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
    }

}
