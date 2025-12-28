package com.nd.redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Set;

@Repository
public class RankDao {

    @Autowired
    private StringRedisTemplate redisTemplate;
    // 更新实时分数
    public void updateScore(Long gameId, Long uId, Integer delta) {
        String key = "game:score:"+gameId;
        redisTemplate.opsForZSet().incrementScore(key,uId.toString(),delta);
    }
    // 更新周分数
    public void updateWeekScore(Long gameId, Long uId, Integer delta) {
        //WeekFields.ISO.weekOfWeekBasedYear()
// 获取当前日期时间的周数（基于ISO标准）

// 在Redis的有序集合中增加指定用户的分数
// 参数说明：weekKey-有序集合的键名，uId.toString()-成员标识，delta-要增加的分数值
        redisTemplate.opsForZSet().incrementScore(weekKey(gameId),uId.toString(),delta);
    }

    public Set<ZSetOperations.TypedTuple<String>> getTop(Long gameId, int i) {
        String key = "game:score:"+gameId;
        //从高到低排序
        return redisTemplate.opsForZSet().reverseRangeWithScores(key,0,i-1);

    }

    public String weekKey(Long gameId) {
        int week = LocalDateTime.now().get(WeekFields.ISO.weekOfWeekBasedYear());
// 构建Redis中的周分数键，格式为"game:weekScore:游戏ID:周数"
        return  "game:weekScore:"+gameId+":"+week;

    }


    public Set<ZSetOperations.TypedTuple<String>> getWeekTop(Long gameId, int i) {

        return redisTemplate.opsForZSet().reverseRangeWithScores(weekKey(gameId),0,i-1);
    }

    public Long getRank(Long gameId, Long uId) {
        String key = "game:score:"+gameId;
        return redisTemplate.opsForZSet().reverseRank(key,uId.toString());
    }

    public Double getScore(Long gameId, Long uId) {
        String key = "game:score:"+gameId;
        return redisTemplate.opsForZSet().score(key,uId.toString());
    }
}
