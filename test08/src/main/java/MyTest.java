
import com.alibaba.fastjson.JSON;
import io.micrometer.core.instrument.util.JsonUtils;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.springframework.cloud.openfeign.support.JsonFormWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MyTest {

    public static void main(String[] agrs){
        List<Student> list = new ArrayList<>();
        list.add(new Student("jim","清华",18,"0"));//0：男 1：女
        list.add(new Student("lilei","北京",18,"0"));
        list.add(new Student("xiaohong","武汉",21,"1"));
        list.add(new Student("hanmeimei","武汉",20,"1"));
        list.add(new Student("tom","北京",18,"0"));
        list.add(new Student("licy","北京",24,"1"));
        list.add(new Student("jack","清华",19,"0"));
        list.add(new Student("lucy","清华",23,"1"));



        List<Student> collect = list.stream()
                                .filter(student -> "清华".equals(student.getSchool()))
                                .collect(Collectors.toList());
        System.out.println(collect);
        collect = list.stream()
                .filter(student -> student.getAge() %2 == 0)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);

        collect = list.stream()
                .filter(student -> student.getAge() %2 == 0)
                .limit(1)
                .collect(Collectors.toList());
        System.out.println(collect);

        collect = list.stream()
                .filter(student -> "清华".equals(student.getSchool()))
                .sorted((s1,s2) -> s2.getAge() - s1.getAge())
                .collect(Collectors.toList());
        System.out.println(collect);

        List<String> collect1 = list.stream()
                .filter(student -> "清华".equals(student.getSchool()))
                .map(Student::getName)
                .collect(Collectors.toList());
        System.out.println(collect1);

        //查找
        boolean b = list.stream().allMatch(student -> "清华".equals(student.getSchool()));
        boolean b1 = list.stream().anyMatch(student -> "清华".equals(student.getSchool()));
        boolean b2 = list.stream().noneMatch(student -> "清华".equals(student.getSchool()));
        Optional<Student> first = list.stream().filter(student -> "清华".equals(student.getSchool())).findFirst();
        System.out.println(first.get());
        Optional<Student> any = list.stream().filter(student -> "清华".equals(student.getSchool())).findAny();
        System.out.println(any.get());



        String[] strs = {"java8", "is", "easy", "to", "use"};
        List<String[]> distinctStrs = Arrays.stream(strs)
                .map(str -> str.split(""))  // 映射成为Stream<String[]>
                .distinct()
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(distinctStrs));

        String[] split = "java8".split("");
        System.out.println(JSON.toJSONString(split));

        List<String> distinctStrs2 = Arrays.stream(strs)
                .map(str -> str.split(""))  // 映射成为Stream<String[]>
                .flatMap(Arrays::stream)  // 扁平化为Stream<String>
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(distinctStrs2));


        List<Integer> collect2 = list.stream().filter(student -> "北京".equals(student.getSchool()))
                .map(Student::getAge).collect(Collectors.toList());
        System.out.println(collect2);


        //调用
        consumerFun(1,(value)->{
            System.out.println(value);
        });

        //调用
        System.out.println(predicateFun(3, x->x==3));
    }


    public static void consumerFun(int value, Consumer<Integer> c){
        c.accept(value);
    }


    public static boolean predicateFun(int value, Predicate<Integer> pre) {
        return pre.test(value);
    }


}
