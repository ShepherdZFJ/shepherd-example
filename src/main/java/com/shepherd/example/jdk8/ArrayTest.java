package com.shepherd.example.jdk8;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/5/6 00:27
 */

// 我们发现 arraycopy 是一个 native 方法,接下来我们解释一下各个参数的具体意义

import java.util.Arrays;

/**
 *   复制数组
 * src 源数组
 * srcPos 源数组中的起始位置
 * dest 目标数组
 * destPos 目标数组中的起始位置
 * length 要复制的数组元素的数量
 * public static native void arraycopy(Object src,  int  srcPos,
 *                                         Object dest, int destPos,
 *                                         int length);
 */
public class ArrayTest {
    public static void main(String[] args) {
        testSystemArrayCopy();
        testArrayCopyOf();
    }

    static void testSystemArrayCopy() {
        int[] a = new int[10];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        a[3] = 3;
        // 把位置2后面的3
        System.arraycopy(a, 2, a, 3, 3);
        a[2]=99;
        for (int i = 0; i < a.length; i++) {
            // 输出0 1 99 2 3 0 0 0 0 0
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    static void testArrayCopyOf(){
        int[] a = new int[3];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        int[] b = Arrays.copyOf(a, 10);
        System.out.println("b.length="+b.length);
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }
    }



}

