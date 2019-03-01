package com.lind.basic.nosql;

import com.lind.basic.config.JedisLock;
import com.lind.basic.config.RedisConfig;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.BoundGeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest()
public class RedisTest {

  private final static String key = "geo";
  @Autowired
  private RedisTemplate redisTemplate;
  @Autowired
  private JedisLock redisLock;
  @Autowired
  private JedisLock jedisLock;
  @Autowired
  private RedisConfig redisConfig;

  @Test
  public void setTest() throws Exception {

    redisTemplate.opsForValue().set("ok", "test");
    System.out.println(
        "setTest:" + redisTemplate.opsForValue().get("ok")
    );
  }

  @Test
  public void hello() {
    Assert.assertEquals(1, 1);
  }

  /**
   * 方法强制为同步方法.
   */
  void queue() {
    String value = String.valueOf(System.currentTimeMillis() + 5);
    if (redisLock.tryLock("product", "1")) {
      System.out.println("print synchronized result:" + LocalDateTime.now().toString()
          + ",id:"
          + Thread.currentThread().getId());
      redisLock.releaseLock("product", "1");
    }
  }

  @Test
  public void distributeLock() throws Exception {
    new Thread(() -> {
      for (int i = 0; i < 5; i++) {
        queue();
      }
    }).start();
  }

  void queue2() {
    if (jedisLock.tryLock("lind", "test")) {
      for (int i = 0; i < 5; i++) {
        System.out.println("print synchronized result:" + LocalDateTime.now().toString()
            + ",id:"
            + Thread.currentThread().getId());
      }
      jedisLock.releaseLock("lind", "test");
    }
  }

  /**
   * 代金卷例子.
   * set结构保证了value的唯一性.
   */
  @Test
  public void setCoupon() {
    final String COUPON_KEY = "coupon";

    for (int i = 0; i < 100; i++) {
      redisTemplate.opsForSet().add(COUPON_KEY, String.format("abc%s", i));
      redisTemplate.opsForSet().add(COUPON_KEY, String.format("abc%s", i));
    }
    Assert.assertEquals(Long.valueOf(100), redisTemplate.opsForSet().size(COUPON_KEY));
    redisTemplate.opsForSet().pop(COUPON_KEY);
    Assert.assertEquals(Long.valueOf(99), redisTemplate.opsForSet().size(COUPON_KEY));

  }

  /**
   * 用户消费top10.
   * sortList结构做实时排名.
   */
  @Test
  public void sortListTop() {
    final String CONSUMPTION_KEY = "consumption";

    redisTemplate.opsForZSet().add(CONSUMPTION_KEY, "person1", 1);
    redisTemplate.opsForZSet().add(CONSUMPTION_KEY, "person2", 2);
    redisTemplate.opsForZSet().add(CONSUMPTION_KEY, "person3", 1);

    for (Object o : redisTemplate.opsForZSet().rangeByScore(CONSUMPTION_KEY, 1, 1)) {
      System.out.println(o);
    }

  }

  @Test
  public void distributeLock2() {

    new Thread(() -> {
      for (int i = 0; i < 5; i++) {
        queue2();
      }
    }).start();
  }

  /**
   * 地理位置测试.
   */
  @Test
  public void geoTest() {
    BoundGeoOperations boundGeoOperations = redisTemplate.boundGeoOps("CHINA:CITY");
    Point nanjing = new Point(118.803805, 32.060168);
    boundGeoOperations.add(nanjing, "南京市");
    Point beijing = new Point(116.397039, 39.9077);
    boundGeoOperations.add(beijing, "北京市");
    Point shanghai = new Point(120.52, 30.40);
    boundGeoOperations.add(shanghai, "上海市");
    //geodist：获取两个地理位置的距离
    Distance distance = boundGeoOperations.distance("南京市", "北京市", Metrics.KILOMETERS);
    System.out.println("南京市到北京市之间的距离是：" + distance.getValue() + "km");

    Distance distance2 = boundGeoOperations.distance("南京市", "上海市", Metrics.KILOMETERS);
    System.out.println("南京市到上海市之间的距离是：" + distance2.getValue() + "km");

    //geohash：获取某个地理位置的geohash值
    List<String> list = boundGeoOperations.hash("南京市");
    System.out.println("南京市的geoHash = " + list.get(0));

    //geopos：获取某个地理位置的坐标
    List<Point> pointList = boundGeoOperations.position("南京市");
    System.out.println("南京市的经纬度为 = " + pointList.get(0));

    //georadius：根据给定地理位置坐标获取指定范围内的地理位置集合
    //查询南京市1000KM范围内的城市
    Circle within = new Circle(nanjing, 1000000);
    //设置geo查询参数
    RedisGeoCommands.GeoRadiusCommandArgs geoRadiusArgs = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs();
    //查询返回结果包括距离和坐标
    geoRadiusArgs = geoRadiusArgs.includeCoordinates().includeDistance();
    //按查询出的坐标距离中心坐标的距离进行排序
    geoRadiusArgs.sortAscending();
    //限制查询返回的数量
    geoRadiusArgs.limit(2);
    GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults = boundGeoOperations.radius(within, geoRadiusArgs);
    List<GeoResult<RedisGeoCommands.GeoLocation<String>>> geoResultList = geoResults.getContent();
    for (GeoResult geoResult : geoResultList) {
      System.out.println("geoRadius  " + geoResult.getContent());
    }

    //georadiusbymember：根据给定地理位置获取指定范围内的地理位置集合
    geoRadiusArgs.limit(1);
    geoResults = boundGeoOperations.radius("南京市", new Distance(1000000), geoRadiusArgs);
    geoResultList = geoResults.getContent();
    for (GeoResult geoResult : geoResultList) {
      System.out.println("geoRadiusByMember  " + geoResult.getContent());
    }
    //删除位置信息,此命令不是geo提供的，是使用zrem命令删除的
   boundGeoOperations.remove("南京市");
  }

  /**
   * 查看用户在线状态情况 1在线，0离线.
   */
  @Test
  public void bitmapTest() {
    final String onlineKey = "online:";
    for (int i = 0; i < 100; i++) {
      redisTemplate.opsForValue().setBit(onlineKey, i, i % 2 == 0);
    }
    for (int i = 0; i < 10; i++) {

      System.out.println(i + "=" + redisTemplate.opsForValue().getBit(onlineKey, i));
    }
    System.out.println("online:" + redisConfig.bitCount(onlineKey));
  }

  /**
   * 统一数组里数据唯一性.
   * IP地址去重复.
   */
  @Test
  public void hyperLogLogTest() {
    final String loglogKey = "loglog:";

    String[] arr = new String[100];
    for (int i = 0; i < 100; i++) {
      arr[i] = "A" + new Random().nextInt(10) + 1;
    }
    redisTemplate.opsForHyperLogLog().add(loglogKey, arr);
    System.out.println("loglog:" + redisTemplate.opsForHyperLogLog().size(loglogKey));
  }
}
