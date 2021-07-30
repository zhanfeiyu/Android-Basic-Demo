package com.mike.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mike.demo.bean.User;
import com.mike.demo.bean.User1;
import com.mike.demo.bean.User2;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SerializationTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /*
    测试普通java对象的序列化和反序列化
    1, 类内部的对象成员如果为null，不会被序列化，如下面User里的job
       但是数组里成员如果为null，会被序列化，参考test2

       {"name":"lily","age":18,"isStudent":false}
        User{name='lily', age=18, isStudent=false, job=null}
     */
    @Test
    public void test1() {
        User user1 = new User("lily", 18, false);
        Gson gson = new Gson();
        String json = gson.toJson(user1);
        System.out.println(json);

        User user2 = gson.fromJson(json, User.class);
        System.out.println(user2);
    }

    /*
    测试数组的序列化和反序列化

    结果：
    [{"name":"lucy","age":20,"isStudent":true},{"name":"tom","age":25,"isStudent":false},null]
    User{name='lucy', age=20, isStudent=true, job=null}
    User{name='tom', age=25, isStudent=false, job=null}
    null
     */
    @Test
    public void test2() {
        User[] users1 = new User[3];
        users1[0] = new User("lucy", 20, true);
        users1[1] = new User("tom", 25, false);
        users1[2] = null;

        Gson gson = new Gson();
        String json = gson.toJson(users1);
        System.out.println(json);

        User[] users2 = gson.fromJson(json, User[].class);
        System.out.println(users2[0]);
        System.out.println(users2[1]);
        System.out.println(users2[2]);
    }

    /*
    测试List的序列化和反序列化
    反序列化时候不能使用List.class作为类型,需要使用TypeToken转换类型

    [{"name":"lucy","age":20,"isStudent":true},{"name":"tom","age":25,"isStudent":false},null]
    User{name='lucy', age=20, isStudent=true, job=null}
    User{name='tom', age=25, isStudent=false, job=null}
    null
    */
    @Test
    public void test3() {
        List<User> list1 = new ArrayList<>();
        list1.add(new User("lucy", 20, true));
        list1.add(new User("tom", 25, false));
        list1.add(null);

        Gson gson = new Gson();
        String json = gson.toJson(list1);
        System.out.println(json);

        Type type = new TypeToken<List<User>>() {
        }.getType();
        List<User> list2 = gson.fromJson(json, type);

        System.out.println(list2.get(0));
        System.out.println(list2.get(1));
        System.out.println(list2.get(2));
    }

    /*
    1，测试Map的序列化和反序列化
    2，反序列化时候需要使用TypeToken转换类型
    3，HashMap会被覆盖：同样的key时候
    4，null成员不会被序列化，反序列化会返回一个空对象
    5，只有Map没有把null序列化出去
    {"0":{"name":"lucy","age":20,"isStudent":true},"1":{"name":"tom","age":25,"isStudent":false}}
    User{name='lucy', age=20, isStudent=true, job=null}
    User{name='tom', age=25, isStudent=false, job=null}
    null
    */
    @Test
    public void test4() {
        Map<String, User> map1 = new HashMap<>();
        map1.put("0", new User("lucy", 20, true));
        map1.put("1", new User("tom", 25, false));
        map1.put("2", null);

        Gson gson = new Gson();
        String json = gson.toJson(map1);
        System.out.println(json);

        Type type = new TypeToken<Map<String, User>>() {}.getType();
        Map<String, User> map2 = gson.fromJson(json, type);

        System.out.println(map2.get("0"));
        System.out.println(map2.get("1"));
        System.out.println(map2.get("2"));
    }


    /*
    测试Set的序列化和反序列化
    反序列化时候需要使用TypeToken转换类型

    [null,{"name":"lucy","age":20,"isStudent":true},{"name":"tom","age":25,"isStudent":false}]
    null
    User{name='lucy', age=20, isStudent=true, job=null}
    User{name='tom', age=25, isStudent=false, job=null}
    */
    @Test
    public void test5() {
        Set<User> set1 = new HashSet<>();
        set1.add(new User("lucy", 20, true));
        set1.add(new User("tom", 25, false));
        set1.add(null);

        Gson gson = new Gson();
        String json = gson.toJson(set1);
        System.out.println(json);

        Type type = new TypeToken<Set<User>>() {
        }.getType();
        Set<User> set2 = gson.fromJson(json, type);

        Iterator<User> iterator = set2.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            System.out.println(user);
        }
    }

    /*
    测试Set的序列化和反序列化，反序列化时候可以使用List type代替
    反序列化时候需要使用TypeToken转换类型
    [null,{"name":"lucy","age":20,"isStudent":true},{"name":"tom","age":25,"isStudent":false}]
    null
    User{name='lucy', age=20, isStudent=true, job=null}
    User{name='tom', age=25, isStudent=false, job=null}
    */
    @Test
    public void test6() {
        Set<User> set1 = new HashSet<>();
        set1.add(new User("lucy", 20, true));
        set1.add(new User("tom", 25, false));
        set1.add(null);

        Gson gson = new Gson();
        String json = gson.toJson(set1);
        System.out.println(json);

        Type type = new TypeToken<List<User>>() {}.getType();
        List<User> list = gson.fromJson(json, type);

        System.out.println(list.get(0));
        System.out.println(list.get(1));

        System.out.println(list.get(2));
    }

    /*
    测试使用@SerializedName控制变量的序列化名称
    {"id":1,"n":"linda","p":"123","s":"f"}
    User1{id=1, userName='linda', userPassword='123', sex='f'}
     */
    @Test
    public void test7() {
        Gson gson = new Gson();
        User1 user1 = new User1(1, "linda", "123", "f");
        String json = gson.toJson(user1);
        System.out.println(json);

        User1 user11 = gson.fromJson(json, User1.class);
        System.out.println(user11);
    }

    /*
    测试使用@Expose 控制变量是否参与序列化
    需要使用GsonBuilder
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create(); //没有注解的不序列化
    {"id":2,"userName":"tommy","userPassword":"123","sex":"m"}
    User1{id=2, userName='tommy', userPassword='null', sex='m'}
    */
    @Test
    public void test8() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        User2 user = new User2(2, "tommy", "123", "m");
        String json = gson.toJson(user);
        System.out.println(json);

        User2 user_ = gson.fromJson(json, User2.class);
        System.out.println(user_);
    }


}