package com.nd.redis.controller;

import com.nd.redis.service.RankService;
import com.nd.redis.vo.RankVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rank")
public class RankController {

    @Autowired
    private RankService rankService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/test")
    public String test(){
        redisTemplate.opsForValue().set("name","张三");
        return "success:"+redisTemplate.opsForValue().get("name");
    }

    //TODO: 更行分数 获取实时榜单 获取周榜，获取个人信息

    /**
     * 获取更新分数
     * @param gameId 游戏id
     * @param uId 用户id
     * @param delta 分数增量
     */
    @PostMapping("/update")
    public void updateScore(
            @RequestParam Long gameId,
            @RequestParam Long uId,
            @RequestParam Integer delta
    ){
        rankService.updateScore(gameId,uId,delta);
    }


    //获取实时榜单  多人榜单  List<>  根据游戏，查询uId，分数，排名  把这三个属性，封装成一个对象
    //获取周榜

    /**
     * 获取实时榜单
     * @param gameId
     * @return
     */
    @GetMapping("/getTop")
    public List<RankVO> getTop(@RequestParam Long gameId){
        return rankService.getTop(gameId);
    }

    /**
     * 获取周榜
     * @param gameId
     * @return
     */
    @GetMapping("/getWeekTop")
    public List<RankVO> getWeekTop(@RequestParam Long gameId){
        return rankService.getWeekTop(gameId);
    }

    /**
     * 获取个人信息
     * @param gameId
     * @param uId
     * @return
     */
    //获取个人信息
    @GetMapping("/self")
    public RankVO getSelf(@RequestParam Long gameId,@RequestParam Long uId){
        return rankService.getSelf(gameId,uId);
    }
}
