import com.lhl.util.DateUtil;

import java.util.Date;

/**
 * @athor:lhl
 * @create:2020-01-31 20:46
 * 测试通过
 */
public class DateUtilTest {

    public static void main(String[] args) {
        DateUtil dateUtil=new DateUtil();
        Date date=new Date();
//        System.out.println(date);
//        String dateToString = dateUtil.DateToString(date, "yyyy-MM-dd  hh:mm:ss");
//        System.out.println(dateToString);

        Date date1 = dateUtil.StringToDate("2020-01-31  08:49:01", "yyyy-MM-dd  hh:mm:ss");
        System.out.println(date1);

    }

}
