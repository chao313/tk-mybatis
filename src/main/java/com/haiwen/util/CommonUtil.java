package com.haiwen.util;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    public static final String ZIP = "zip";
    public static final String RAR = "rar";
    public static final String TARGZ = ".tar.gz";


    /**
     * 根据classPath获取文件内容的list
     * <p>
     * UTF-8
     *
     * @param classPath
     * @return
     * @throws IOException
     */
    public static List<String> getListByClassPath(String classPath) throws IOException {
        File file = ResourceUtils.getFile("classpath:" + classPath);
        return FileUtils.readLines(file, Charsets.UTF_8);
    }


    /**
     * @param source        源字符串
     * @param regExpression 正则表达式
     * @param index         取匹配的index
     * @return
     */
    public static List<String> RegexValueList(String source, String regExpression, Integer index) {
        List<String> rets = new ArrayList();
        Pattern p = Pattern.compile(regExpression);
        Matcher m = p.matcher(source);

        while (m.find()) {
            rets.add(m.group(index));
        }
        return rets;
    }

    /**
     * 返回匹配到的第一个 -> 没有就算返回""
     *
     * @param source        源字符串
     * @param regExpression 正则表达式
     * @param index         取匹配的index
     * @return
     */
    public static String RegexValueListFirst(String source, String regExpression, Integer index) {
        List<String> rets = CommonUtil.RegexValueList(source, regExpression, index);
        if (rets.size() > 0) {
            return rets.get(0);
        } else {
            return "";
        }
    }

    /**
     * 从CSV中读取数据，格式类似
     * 62857,ytcms.zip
     *
     * @param classPath
     * @return
     * @throws IOException
     */
    public static Map<Integer, List<String>> readListFromClassCSV(String classPath) throws IOException {
        Map<Integer, List<String>> line2Values = new HashMap<>();
        List<String> lines = CommonUtil.getListByClassPath(classPath);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (!StringUtils.isEmpty(line)) {
                String[] split = line.split(",");
                line2Values.put(i, Arrays.asList(split));
            }
        }
        return line2Values;
    }


}
