package com.sxs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author sxs
 * @create 2022-08-26 22:51
 */
@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void string_test(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("city", "beijing");
        Object city = valueOperations.get("city");
        System.out.println("city = " + city);

        valueOperations.set("name", "sxs", 10, TimeUnit.SECONDS);

        Boolean aBoolean = valueOperations.setIfAbsent("city", "najing");
        System.out.println(aBoolean);

    }
    @Test
    public void hash_test(){
        HashOperations hash = redisTemplate.opsForHash();
        hash.put("001", "name", "张三");
        hash.put("001", "age", "12");
        hash.put("001", "sex", "1");

        String name = (String) hash.get("001","name");
        String age =(String) hash.get("001", "age");
        System.out.println("name = " + name);
        System.out.println("age = " + age);

        Set keys = hash.keys("001");
        for (Object key : keys) {
            Object o = hash.get("001", key);
            System.out.println(key+":"+o);
        }
        List values = hash.values("001");
        values.stream().forEach((item)->{
            System.out.println(item);
        });
    }

    @Test
    public void list_test(){
        ListOperations list = redisTemplate.opsForList();
        list.leftPushAll("ages", "1","2","3","4");

        //取值
        List<String> ages = list.range("ages", 0, -1);
        for (String age : ages) {
            System.out.println(age);
        }

        System.out.println("--pop--");
        Long size = list.size("ages");
        for (Long i = 0L; i < size; i++) {
            Object ages1 = list.rightPop("ages");
            System.out.println(ages1);
        }
    }

    @Test
    public void set_test(){
        SetOperations set = redisTemplate.opsForSet();
        //存值
        Long aLong = set.add("sexs", "0", "1", "0");
        System.out.println("aLong = " + aLong);

        //取值
        Set sexs = set.members("sexs");
        for (Object sex : sexs) {
            System.out.println("sex = " + sex);
        }
        //删除
        Long remove = set.remove("sexs", "0", "1");
        System.out.println("remove = " + remove);
    }

    @Test
    public void zset_test(){
        ZSetOperations zSet = redisTemplate.opsForZSet();
        zSet.add("citys", "sh", 1);
        zSet.add("citys", "wh",2);
        zSet.add("citys", "sz",1.5);

        //取值
        Set<String> citys = zSet.range("citys", 0, -1);
        for (String city : citys) {
            System.out.println(city);
        }

        //
        Double score = zSet.incrementScore("citys", "sz", 1);
        System.out.println("score = " + score);
        //
        Long aLong = zSet.remove("citys", "wh");
        System.out.println("aLong = " + aLong);


    }
}
