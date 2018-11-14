package com.ljh.myspringboot.dwz.stream;

import com.sun.org.apache.xml.internal.serializer.ToStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: java类作用描述
 * @Author: linjinhan
 * @CreateDate: 2018/11/8 16:27
 */
public class StremTest {

    public static void main(String[] args) {
//        int value = Stream.of(1, 2, 3, 4).reduce(100, (sum, item) -> sum + item);
//        Assert.assertSame(value, 110);
        /* 或者使用方法引用 */
//        value = Stream.of(1, 2, 3, 4).reduce(2, Integer::sum);
//        System.out.println(value);
//        StremTest.primaryOccurrence("1", "3", "5", "1", "6", "3");

        ArrayList<Person> l = new ArrayList<Person>();
        l.add(new Person(22,Person.MALE));
        l.add(new Person(25,Person.MALE));
        l.add(new Person(27,Person.FEMALE));
        l.add(new Person(30,Person.MALE));
        l.add(new Person(31,Person.MALE));
        l.add(new Person(33,Person.FEMALE));
        l.add(new Person(26,Person.MALE));
        boysAndGirls(l);

    }

    public static void primaryOccurrence(String... numbers) {
        List<String> l = Arrays.asList(numbers);
        Map<Integer, Integer> r = l.stream()
                .map(e -> new Integer(e))
                .filter(e -> Primes.isPrime(e))
                .collect(Collectors.groupingBy(p -> p, Collectors.summingInt(p -> 1)));
        System.out.println("primaryOccurrence result is: " + r);
    }

    public static void boysAndGirls(List<Person> persons) {
        Map<Integer, Integer> result = persons.parallelStream().filter(p -> p.getAge() >= 25 && p.getAge() <= 35).
                collect(
                        Collectors.groupingBy(p -> p.getSex(), Collectors.summingInt(p -> 1))
                );
        System.out.print("boysAndGirls result is " + result);
        System.out.println(", ratio (male : female) is " + (float) result.get(Person.MALE) / result.get(Person.FEMALE));
    }

}

class Primes {
    public static Boolean isPrime(int n) {
        boolean flag;
        int i;
        flag = true;
        for (i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}


class Person {

    public static Integer MALE = 0;
    public static Integer FEMALE = 1;

    public Person(Integer age, Integer sex) {
        this.age = age;
        this.sex = sex;
    }

    private Integer age;
    private Integer sex;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}

