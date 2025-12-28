package com.nd.redis.vo;

import lombok.*;

@Data //自动生成 Getter Setter ToString
@AllArgsConstructor //自动生成全参构造函数
public class RankVO {
    private Long uid;  // 用户ID
    private Integer score; // 分数
    private Integer rank;

    //构造函数
    // Getter Setter
    // ToString排名从 1 开始
}