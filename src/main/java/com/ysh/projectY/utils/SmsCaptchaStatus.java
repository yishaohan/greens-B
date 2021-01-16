package com.ysh.projectY.utils;

public enum SmsCaptchaStatus {
    /*
     created: 已创建,未使用
        used: 被正常使用
      covered: 已创建, 未使用, 被新的请求覆盖
     expired: 已超时
      exceed: 超过最大验证次数
     */
    CREATED, USED, COVERED, EXPIRED, EXCEED
}
