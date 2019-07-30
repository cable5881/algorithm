package com.lqb;

/**
 * @author liqibo
 * @description TODO
 * @date 2019/7/25 15:13
 **/
public class JavacDemo {

    private static final String STATIC_FINAL_VAL = "staticFinalVal";

    private static String staticVal;

    {
        staticVal = "staticFinalVal";
    }

    private String name;

    private int age;

    public JavacDemo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void sayHello() {
        System.out.println(String.format("hello, i'm %s and %d years old", name, age));
    }

    public static void sayBye() {
        System.out.println("886");
    }

    public static void main(String[] args) {
        String name = "lqb";
        int age = 22;
        JavacDemo javacDemo = new JavacDemo(name, age);
        javacDemo.sayHello();
    }
}
