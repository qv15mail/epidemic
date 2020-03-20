package com.supwisdom.datashow.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <a href="StringUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @version $Revision: 1.24 $
 */
public class StringUtil {
    public static final String CURRENT_SYS_ENCODING = System
            .getProperty("file.encoding");

    public static String encodeString(String str, String toEncoding)
            throws UnsupportedEncodingException {
        if (CURRENT_SYS_ENCODING.equals(toEncoding)) {
            return str;
        }
        return new String(str.getBytes(toEncoding), toEncoding);
    }

    public static String add(String s, String add) {
        return add(s, add, StringPool.COMMA);
    }

    public static String add(String s, String add, String delimiter) {
        return add(s, add, delimiter, false);
    }

    public static String add(
            String s, String add, String delimiter, boolean allowDuplicates) {

        if ((add == null) || (delimiter == null)) {
            return null;
        }

        if (s == null) {
            s = StringPool.BLANK;
        }

        if (allowDuplicates || !contains(s, add, delimiter)) {
            if (s != null || s.endsWith(delimiter)) {
                s += add + delimiter;
            } else {
                s += delimiter + add + delimiter;
            }
        }

        return s;
    }

    public static boolean contains(String s, String text) {
        return contains(s, text, StringPool.COMMA);
    }

    public static boolean contains(String s, String text, String delimiter) {
        if ((s == null) || (text == null) || (delimiter == null)) {
            return false;
        }

        if (!s.endsWith(delimiter)) {
            s += delimiter;
        }

        int pos = s.indexOf(delimiter + text + delimiter);

        if (pos == -1) {
            if (s.startsWith(text + delimiter)) {
                return true;
            }

            return false;
        }

        return true;
    }

    public static int count(String s, String text) {
        if ((s == null) || (text == null)) {
            return 0;
        }

        int count = 0;

        int pos = s.indexOf(text);

        while (pos != -1) {
            pos = s.indexOf(text, pos + text.length());
            count++;
        }

        return count;
    }

    public static boolean endsWith(String s, char end) {
        return startsWith(s, (new Character(end)).toString());
    }

    public static boolean endsWith(String s, String end) {
        if ((s == null) || (end == null)) {
            return false;
        }

        if (end.length() > s.length()) {
            return false;
        }

        String temp = s.substring(s.length() - end.length(), s.length());

        if (temp.equalsIgnoreCase(end)) {
            return true;
        } else {
            return false;
        }
    }

    public static String merge(List list) {
        return merge(list, StringPool.COMMA);
    }

    public static String merge(List list, String delimiter) {
        return merge((String[]) list.toArray(new String[0]), delimiter);
    }

    public static String merge(String array[]) {
        return merge(array, StringPool.COMMA);
    }

    public static String merge(String array[], String delimiter) {
        if (array == null) {
            return null;
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < array.length; i++) {
            sb.append(array[i].trim());

            if ((i + 1) != array.length) {
                sb.append(delimiter);
            }
        }

        return sb.toString();
    }



    public static String read(ClassLoader classLoader, String name)
            throws IOException {

        return read(classLoader.getResourceAsStream(name));
    }

    public static String read(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        StringBuffer sb = new StringBuffer();
        String line = null;

        while ((line = br.readLine()) != null) {
            sb.append(line).append('\n');
        }

        br.close();

        return sb.toString().trim();
    }

    public static String remove(String s, String remove) {
        return remove(s, remove, StringPool.COMMA);
    }

    public static String remove(String s, String remove, String delimiter) {
        if ((s == null) || (remove == null) || (delimiter == null)) {
            return null;
        }

        if (s != null && !s.endsWith(delimiter)) {
            s += delimiter;
        }

        while (contains(s, remove, delimiter)) {
            int pos = s.indexOf(delimiter + remove + delimiter);

            if (pos == -1) {
                if (s.startsWith(remove + delimiter)) {
                    s = s.substring(
                            remove.length() + delimiter.length(), s.length());
                }
            } else {
                s = s.substring(0, pos) + s.substring(pos + remove.length() +
                        delimiter.length(), s.length());
            }
        }

        return s;
    }

    public static String replace(String s, char oldSub, char newSub) {
        return replace(s, oldSub, new Character(newSub).toString());
    }

    public static String replace(String s, char oldSub, String newSub) {
        if ((s == null) || (newSub == null)) {
            return null;
        }

        char[] c = s.toCharArray();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < c.length; i++) {
            if (c[i] == oldSub) {
                sb.append(newSub);
            } else {
                sb.append(c[i]);
            }
        }

        return sb.toString();
    }

    public static String replace(String s, String oldSub, String newSub) {
        if ((s == null) || (oldSub == null) || (newSub == null)) {
            return null;
        }

        int y = s.indexOf(oldSub);

        if (y >= 0) {
            StringBuffer sb = new StringBuffer();

            int length = oldSub.length();
            int x = 0;

            while (x <= y) {
                sb.append(s.substring(x, y));
                sb.append(newSub);
                x = y + length;
                y = s.indexOf(oldSub, x);
            }

            sb.append(s.substring(x));

            return sb.toString();
        } else {
            return s;
        }
    }

    public static String replace(String s, String[] oldSubs, String[] newSubs) {
        if ((s == null) || (oldSubs == null) || (newSubs == null)) {
            return null;
        }

        if (oldSubs.length != newSubs.length) {
            return s;
        }

        for (int i = 0; i < oldSubs.length; i++) {
            s = replace(s, oldSubs[i], newSubs[i]);
        }

        return s;
    }

    public static String reverse(String s) {
        if (s == null) {
            return null;
        }

        char[] c = s.toCharArray();
        char[] reverse = new char[c.length];

        for (int i = 0; i < c.length; i++) {
            reverse[i] = c[c.length - i - 1];
        }

        return new String(reverse);
    }

    public static String shorten(String s) {
        return shorten(s, 20);
    }

    public static String shorten(String s, int length) {
        return shorten(s, length, "..");
    }

    public static String shorten(String s, String suffix) {
        return shorten(s, 20, suffix);
    }

    public static String shorten(String s, int length, String suffix) {
        if (s == null || suffix == null) {
            return null;
        }

        if (s.length() > length) {
            s = s.substring(0, length) + suffix;
        }

        return s;
    }

    public static String[] split(String s) {
        return split(s, StringPool.COMMA);
    }

    public static String[] split(String s, String delimiter) {
        if (s == null || delimiter == null) {
            return new String[0];
        }

        s = s.trim();

        if (!s.endsWith(delimiter)) {
            s += delimiter;
        }

        if (s.equals(delimiter)) {
            return new String[0];
        }

        List nodeValues = new ArrayList();

        if (delimiter.equals("\n") || delimiter.equals("\r")) {
            try {
                BufferedReader br = new BufferedReader(new StringReader(s));

                String line = null;

                while ((line = br.readLine()) != null) {
                    nodeValues.add(line);
                }

                br.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } else {
            int offset = 0;
            int pos = s.indexOf(delimiter, offset);

            while (pos != -1) {
                nodeValues.add(s.substring(offset, pos));

                offset = pos + delimiter.length();
                pos = s.indexOf(delimiter, offset);
            }
        }

        return (String[]) nodeValues.toArray(new String[0]);
    }

    public static boolean[] split(String s, String delimiter, boolean x) {
        String[] array = split(s, delimiter);
        boolean[] newArray = new boolean[array.length];

        for (int i = 0; i < array.length; i++) {
            boolean value = x;

            try {
                value = Boolean.valueOf(array[i]).booleanValue();
            } catch (Exception e) {
            }

            newArray[i] = value;
        }

        return newArray;
    }

    public static double[] split(String s, String delimiter, double x) {
        String[] array = split(s, delimiter);
        double[] newArray = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            double value = x;

            try {
                value = Double.parseDouble(array[i]);
            } catch (Exception e) {
            }

            newArray[i] = value;
        }

        return newArray;
    }

    public static float[] split(String s, String delimiter, float x) {
        String[] array = split(s, delimiter);
        float[] newArray = new float[array.length];

        for (int i = 0; i < array.length; i++) {
            float value = x;

            try {
                value = Float.parseFloat(array[i]);
            } catch (Exception e) {
            }

            newArray[i] = value;
        }

        return newArray;
    }

    public static int[] split(String s, String delimiter, int x) {
        String[] array = split(s, delimiter);
        int[] newArray = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            int value = x;

            try {
                value = Integer.parseInt(array[i]);
            } catch (Exception e) {
            }

            newArray[i] = value;
        }

        return newArray;
    }

    public static long[] split(String s, String delimiter, long x) {
        String[] array = split(s, delimiter);
        long[] newArray = new long[array.length];

        for (int i = 0; i < array.length; i++) {
            long value = x;

            try {
                value = Long.parseLong(array[i]);
            } catch (Exception e) {
            }

            newArray[i] = value;
        }

        return newArray;
    }

    public static short[] split(String s, String delimiter, short x) {
        String[] array = split(s, delimiter);
        short[] newArray = new short[array.length];

        for (int i = 0; i < array.length; i++) {
            short value = x;

            try {
                value = Short.parseShort(array[i]);
            } catch (Exception e) {
            }

            newArray[i] = value;
        }

        return newArray;
    }

    public static final String stackTrace(Throwable t) {
        String s = null;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            t.printStackTrace(new PrintWriter(baos, true));
            s = baos.toString();
        } catch (Exception e) {
        }

        return s;
    }

    public static boolean startsWith(String s, char begin) {
        return startsWith(s, (new Character(begin)).toString());
    }

    public static boolean startsWith(String s, String start) {
        if ((s == null) || (start == null)) {
            return false;
        }

        if (start.length() > s.length()) {
            return false;
        }

        String temp = s.substring(0, start.length());

        if (temp.equalsIgnoreCase(start)) {
            return true;
        } else {
            return false;
        }
    }

    public static String trimLeading(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return s.substring(i, s.length());
            }
        }

        return StringPool.BLANK;
    }

    public static String trimTrailing(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return s.substring(0, i + 1);
            }
        }

        return StringPool.BLANK;
    }

    public static String wrap(String text) {
        return wrap(text, 80, "\n");
    }

    public static String wrap(String text, int width, String lineSeparator) {
        if (text == null) {
            return null;
        }

        StringBuffer sb = new StringBuffer();

        try {
            BufferedReader br = new BufferedReader(new StringReader(text));

            String s = StringPool.BLANK;

            while ((s = br.readLine()) != null) {
                if (s.length() == 0) {
                    sb.append(lineSeparator);
                } else {
                    String[] tokens = s.split(StringPool.SPACE);
                    boolean firstWord = true;
                    int curLineLength = 0;

                    for (int i = 0; i < tokens.length; i++) {
                        if (!firstWord) {
                            sb.append(StringPool.SPACE);
                            curLineLength++;
                        }

                        if (firstWord) {
                            sb.append(lineSeparator);
                        }

                        sb.append(tokens[i]);

                        curLineLength += tokens[i].length();

                        if (curLineLength >= width) {
                            firstWord = true;
                            curLineLength = 0;
                        } else {
                            firstWord = false;
                        }
                    }
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return sb.toString();
    }

    /**
     * Copyright (C), 2000-2006, Kingstar Co., Ltd.<br>
     * File name: .java<br>
     * Description: StringUtil判断输入的字符是否只包含字母和数字，如果有其它字符则返回true,否则返回false<br>
     * Return: boolean<br>
     * Modify History: <br>
     * 操作类型    操作人     操作时间       操作内容<br>
     * ======================================<br>
     * 创建      韩纪伟     2006-5-11  <br>
     *
     * @author 韩纪伟
     * @version
     * @since 1.0
     */
    public static boolean inputIfCharAndNum(String inputStr) {
        Pattern p = Pattern.compile("\\w+");
        Matcher m = p.matcher(inputStr);
        if (m.matches()) {
            //除字母和数字外还包含其它字符
            return false;
        } else {
            return true;
        }
    }

    /**
     * Copyright (C), 2000-2006, Kingstar Co., Ltd.<br>
     * File name: .java<br>
     * Description: StringUtil判断输入的是否全部为数字，不全是数字返回true<br>
     * Return: boolean<br>
     * Modify History: <br>
     * 操作类型    操作人     操作时间       操作内容<br>
     * ======================================<br>
     * 创建      韩纪伟     2006-5-11  <br>
     *
     * @author 韩纪伟
     * @version
     * @since 1.0
     */
    public static boolean inputIfNum(String inputStr) {
        char[] ch = inputStr.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i] < '0') || (ch[i] > '9')) {
                return false;
            }
        }
        return true;
    }

    /**
     * Copyright (C), 2000-2006, Kingstar Co., Ltd.<br>
     * File name: .java<br>
     * Description: StringUtil数字转换为中文<br>
     * Return: String<br>
     * Modify History: <br>
     * 操作类型    操作人     操作时间       操作内容<br>
     * ======================================<br>
     * 创建      韩纪伟     2006-5-11  <br>
     *
     * @author 韩纪伟
     * @version
     * @since 1.0
     */
    public static String changeToBig(double value) {
        char[] hunit = {'拾', '佰', '仟'}; //段内位置表示
        char[] vunit = {'万', '亿'};      //段名表示
        char[] digit = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};  //数字表示
        long midVal = (long) (value * 100);                               //转化成整形
        String valStr = String.valueOf(midVal);                          //转化成字符串
        String head = valStr.substring(0, valStr.length() - 2);             //取整数部分
        String rail = valStr.substring(valStr.length() - 2);               //取小数部分

        String prefix = "";                                              //整数部分转化的结果
        String suffix = "";                                              //小数部分转化的结果
        //处理小数点后面的数
        if (rail.equals("00")) {                                         //如果小数部分为0
            suffix = "整";
        } else {
            suffix = digit[rail.charAt(0) - '0'] + "角" + digit[rail.charAt(1) - '0'] + "分";//否则把角分转化出来
        }
        //处理小数点前面的数
        char[] chDig = head.toCharArray();                                      //把整数部分转化成字符数组
        char zero = '0';                                                        //标志'0'表示出现过0
        byte zeroSerNum = 0;                                                  //连续出现0的次数
        for (int i = 0; i < chDig.length; i++) {                                      //循环处理每个数字
            int idx = (chDig.length - i - 1) % 4;                                       //取段内位置
            int vidx = (chDig.length - i - 1) / 4;                                      //取段位置
            if (chDig[i] == '0') {                                                  //如果当前字符是0
                zeroSerNum++;                                                     //连续0次数递增
                if (zero == '0') {                                                  //标志
                    zero = digit[0];
                } else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
                    prefix += vunit[vidx - 1];
                    zero = '0';
                }
                continue;
            }
            zeroSerNum = 0;                                                     //连续0次数清零
            if (zero != '0') {                                                   //如果标志不为0,则加上,例如万,亿什么的
                prefix += zero;
                zero = '0';
            }
            prefix += digit[chDig[i] - '0'];                                        //转化该数字表示
            if (idx > 0) prefix += hunit[idx - 1];
            if (idx == 0 && vidx > 0) {
                prefix += vunit[vidx - 1];                                            //段结束位置应该加上段名如万,亿
            }
        }

        if (prefix.length() > 0)
            prefix += '圆';                               //如果整数部分存在,则有圆的字样
        return prefix + suffix;                                                //返回正确表示
    }

    public static String intOrLikeIntListTransToStr(List list) {
        StringBuffer buf = new StringBuffer(" ( ");
        for (int i = 0; i < list.size(); i++) {
            buf.append(list.get(i));
            if (i == list.size() - 1) {
                buf.append(")");
            } else {
                buf.append(",");
            }
        }
        return buf.toString();
    }

    public static String strListTransToStr(List<String> list) {
        StringBuffer buf = new StringBuffer(" ( ");
        for (int i = 0; i < list.size(); i++) {
            buf.append("'");
            buf.append(list.get(i));
            buf.append("'");
            if (i == list.size() - 1) {
                buf.append(")");
            } else {
                buf.append(",");
            }
        }
        return buf.toString();
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    
	/**
	 * 右对齐，左侧增加0
	 *
	 * @param sourceStr
	 *          字符串
	 * @param ilen
	 *          最终长度
	 * @return
	 */
	public static String leftZeroAppend(String sourceStr, int ilen) {
		if (null == sourceStr) {
			return "";
		}
		String res = sourceStr;
		int i = getHzStringLen(res);// res.length();
		int j = 0;
		if (i >= ilen)
			res = res.substring(0, ilen);
		else {
			for (j = 0; j < ilen - i; j++)
				res = "0" + res;
		}
		return res;
	}
	/**
	 * 计算含有中文字符的字符串长度 中文占2位
	 * 
	 * @param sStr
	 * @return
	 */
	public static int getHzStringLen(String sStr) {
		if (null == sStr) {
			return 0;
		}
		int i = 0, j = 0;
		String chinese = "[\u4e00-\u9fa5]";
		try {
			while (j < sStr.length()) {
				String temp = sStr.substring(j, j + 1);
				// 判断是否为中文字符
				if (temp.matches(chinese)) {
					i += 2;
				} else {
                    i += 1;
                }
				j = j + 1;
			}
			return i;
		} catch (Exception e) {
			return 0;
		}
	}

    /**
     * 获取当前文件夹路径
     * @return
     */
    public static String getPath()
    {
        //当前盘符路径，会获得tomcat/bin文件夹所在的路径
        File file = new File("");
        String path = file.getAbsolutePath().replaceAll("\\\\", "/");
        int mark = path.lastIndexOf("/");
        //修改路径，获得tomcat/webapps/data文件夹路径
        String newPath = path.substring(0, mark) + "/webapps/";
        File f = new File(newPath);
        if (!f.exists())
        {
            f.mkdir();
        }
        return newPath;
    }

    public static String fmtDate(String inDate){
        String tmpDate;
        if (inDate.length()>8){
            tmpDate = inDate.substring(0,10).replaceAll("-","");
        }else{
            tmpDate = inDate;
        }
        return tmpDate;
    }

    //二进制图片写入文件
    public static void buff2Image(byte[] b,String tagSrc) throws Exception
    {
        FileOutputStream fout = new FileOutputStream(tagSrc);
        //将字节写入文件
        fout.write(b);
        fout.close();
    }

    //图片读取为二进制
    public static byte[] image2Bytes(String imgSrc) throws Exception
    {
        FileInputStream fin = new FileInputStream(new File(imgSrc));
        //可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
        byte[] bytes  = new byte[fin.available()];
        //将文件内容写入字节数组，提供测试的case
        fin.read(bytes);

        fin.close();
        return bytes;
    }

    public static String hexStr2binaryStr(String hexString)
    {
        if (hexString == null || hexString.length() % 2 != 0) {
            return null;
        }
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++)
        {
            tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4);
        }
        return bString;
    }

    public static String reversePhyid(String inphyid){
        if (inphyid.length()%2 >0){
            return "";
        }
        String stmp = "";
        for (int i=inphyid.length();i>0;i=i-2){
            stmp += inphyid.substring(i-2,i);
        }
        return stmp;
    }

    public static void main(String[] args){
//	    for (int i=0;i<1000;i++){
//	        String key = "key"+i;
//	        int value = i+10;
//	        RedisUtil.set(key,String.valueOf(value),90);
//        }
        //String ss = hexStr2binaryStr("0000");
        //System.out.println(ss);
//        String rmtDev="--------";
//        int devNo=2;
//        String crtlcode="1";
//        String tmpVal = rmtDev.substring(0,devNo-1)+crtlcode+rmtDev.substring(devNo);
//        System.out.println(tmpVal);
//        String ss = "XKPS_00987";
//        System.out.println(ss.substring(0,ss.indexOf("_")));B2870802  020887B2

        //System.out.println(reversePhyid("B2870802"));
//        String ss = "20181030113955";
//        String tmp = ss.substring(0,4)+"-"+ss.substring(4,6)+"-"+ss.substring(6,8)+" "+ss.substring(8,10)+":"+ss.substring(10,12)+":"+ss.substring(12,14);
//        System.out.println(tmp);
//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
//        list.forEach(n -> System.out.println(n));

        String str = "my name is AlgerFan";

        System.out.println("--------------filter------------");
        // 把每个单词的长度调用出来
        Stream.of(str.split(" ")).filter(s -> s.length() > 2)
                .map(String::length).forEach(System.out::println);

    }

}