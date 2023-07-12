package com.example.demohello.stream;

import com.example.demohello.domain.Person;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author weiyuhui
 * @date 2023/7/10 10:32
 * @description
 */


public class StreamTest {
    public static List<Person> personList = new ArrayList<>();

    static {
        personList.add(new Person("Tom", 8900, "male", "New York"));
        personList.add(new Person("Jack", 7000, "male", "Washington"));
        personList.add(new Person("Lily", 7800, "female", "Washington"));
        personList.add(new Person("Anni", 8200, "female", "New York"));
        personList.add(new Person("Owen", 9500, "male", "New York"));
        personList.add(new Person("Alisa", 7900, "female", "New York"));
    }
    public static void main(String[] args) {



    }

    /**
     * 统计员工人数、平均工资、工资总额、最高工资。
     */
    @Test
    public void test1(){
        //统计员工数量
        Long collect = personList.stream().collect(Collectors.counting());
        // 平均工资
        Double collect1 = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));
        // 最高工资
        Optional<Integer> max1 = personList.stream().map(Person::getSalary).max(Integer::compare);
        Integer max2 = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(), Integer::max);
        // 工资总额
        DoubleSummaryStatistics summaryStatistics = personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));
        Integer max = personList.stream().reduce(0, (sum, p) -> sum + p.getSalary(), Integer::max);
        System.out.println(max);
        System.out.println(summaryStatistics);

        //拼接所有员工姓名
        String concatName = personList.stream().reduce("", (str, p) -> str + "," + p.getName(), String::concat);
        System.out.println(concatName);
    }

    @Test
    public void testGrouping(){
        // 按员工薪资标准分组
        Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        // 按员工性别分组
        Map<String, List<Person>> collect = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        // 按性别分组，再按地区分组
        Map<String, Map<String, List<Person>>> collect1 = personList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getLocation)));
        System.out.println(part);
        System.out.println(collect1);
        System.out.println(collect);
    }
}
