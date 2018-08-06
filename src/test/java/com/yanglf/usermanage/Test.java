package com.yanglf.usermanage;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;

public class Test {

    public static void main(String[] args) {

        HashSet<HostAndPort> nodes= new HashSet();
        nodes.add(new HostAndPort("132.232.14.175",7001));
        nodes.add(new HostAndPort("132.232.14.175",7002));
        nodes.add(new HostAndPort("132.232.14.175",7003));
        nodes.add(new HostAndPort("132.232.14.175",7004));
        nodes.add(new HostAndPort("132.232.14.175",7005));
        nodes.add(new HostAndPort("132.232.14.175",7006));
        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("key01","1000");
        System.out.println(cluster.get("key01"));
    }


}