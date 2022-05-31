package com.shepherd.common.test;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/3/14 10:02
 */

public class Test {
    public static void main(String[] args) {
       String str = "8673";
        BigDecimal bigDecimal = new BigDecimal(str);
        System.out.println(bigDecimal);

    }

    public static char[] VALID_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
    private static Random random = new Random(System.currentTimeMillis());
    int length = 4;

    String generate(int seed) {
        char[] sortUrl = new char[length];
        for (int i = 0; i < length; i++) {
            sortUrl[i] = VALID_CHARS[seed % VALID_CHARS.length];
            seed = random.nextInt(Integer.MAX_VALUE) % VALID_CHARS.length;
        }
        return new String(sortUrl);
    }

    /**
     * 这里的实现不考虑url，直接生成随机字符串即可，这个算法如果容量比较大的时候，性能会变低，因此要根据使用情况选择合适的长度
     *
     * @param url
     * @return
     */
    public String generate(String url) {
        String shortUrl;
        shortUrl = generate(random.nextInt(Integer.MAX_VALUE));
        return shortUrl;
    }


    /**
     * 在进制表示中的字符集合
     */
    final static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private static String to62RadixString(long seq) {
        StringBuilder sBuilder = new StringBuilder();
        while (true) {
            int remainder = (int) (seq % 62);
            sBuilder.append(digits[remainder]);
            seq = seq / 62;
            if (seq == 0) {
                break;
            }
        }
        return sBuilder.reverse().toString();
    }



}
