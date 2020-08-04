package com.lhl.util;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * @athor:lhl
 * @create:2020-02-01 16:26
 * 将jsp pojo参数绑定的字符串转为date
 */
public class CustomDateFormatConvert implements Converter<String, Date> {

    /**
     * 按照 yyyy-MM-dd HH:mm 来转换
     * @param source
     * @return
     */
    @Override
    public Date convert(String source) {
        return DateUtil.StringToDate(source,"yyyy-MM-dd HH:mm");
    }
}
