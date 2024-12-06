package com.github.itdachen.actuator;

import java.util.stream.Stream;

/**
 * TestMain
 *
 * @author 王大宸
 * @date 2024-10-21 14:47
 */
public class TestMain {


    public static void main(String[] args) {

        Stream.iterate(0, n -> n + 1)
                .limit(10) // 限制流中元素的数量，这里只是为了示例
                .forEach(System.out::println); // 打印流中的每个数字
    }

}
