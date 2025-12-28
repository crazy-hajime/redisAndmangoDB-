package com.nd.redis.service;

import com.nd.redis.dao.RankDao;
import com.nd.redis.vo.RankVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RankService {
    @Autowired
    private RankDao rankDao;

    @Transactional  // 开启事务 保证数据一致性
    public void updateScore(Long gameId, Long uId, Integer delta) {
        //更新实时分数
        rankDao.updateScore(gameId,uId,delta);
        //更新周榜分数
        rankDao.updateWeekScore(gameId,uId,delta);
    }

    public List<RankVO> getTop(Long gameId) {
        //rankDao.getTop(gameId,100); 数据结构是Zset集合，需要的是List<RankVO>
        return zsetToList(rankDao.getTop(gameId,100));
    }

    public List<RankVO> getWeekTop(Long gameId) {
        return zsetToList(rankDao.getWeekTop(gameId,100));
    }

    //Zset集合转换为List集合  stream 流  遍历
    public List<RankVO> zsetToList(Set<ZSetOperations.TypedTuple<String>> zset){
        ArrayList<RankVO> list = new ArrayList<>();

//        zset.stream().forEach(t->{
//            list.add(new RankVO(Long.parseLong(t.getValue()),t.getScore().intValue(),list.size()+1));
//        });
        //默认从1开始  从高到低排序
        int rank = 1;
        for (ZSetOperations.TypedTuple<String> tuple : zset) {
            list.add(new RankVO(Long.valueOf(tuple.getValue()),tuple.getScore().intValue(),rank++));
        }

        return list;
    }

    public RankVO getSelf(Long gameId, Long uId) {
        Long rank = rankDao.getRank(gameId,uId);
        Double score = rankDao.getScore(gameId,uId);
        return new RankVO(uId,score == null ? -1 : score.intValue(),rank == null ? -1 : rank.intValue()+1);
    }
}
