package com.sxs;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author sxs
 * @create 2022-08-26 22:29
 */
public class JedisTest {

    @Test
    public void test(){
        //1 获取链接
        Jedis jedis = new Jedis("124.221.229.116", 6379);
        jedis.auth("123456");
        //2
//        jedis.set("username", "张三");
        jedis.set("username", "zhangsan");
        String username = jedis.get("username");
        System.out.println("username = " + username);

        //hash
        jedis.hset("myhash", "name", "sxs");
        String hget = jedis.hget("myhash", "name");
        System.out.println("hget = " + hget);

        //通用
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }

//        jedis.del("username");
        //3 关闭
        jedis.close();
    }
}
