package com.im.vent.schedule;


import com.im.vent.server.MyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
public class SchedulingConfig {
    private static Logger logger = LoggerFactory.getLogger(SchedulingConfig.class);

    @Autowired
    MyServer myServer;

    /**
     * 根据cron表达式格式触发定时任务
     * cron表达式格式:
     * 1.Seconds Minutes Hours DayofMonth Month DayofWeek Year
     * 2.Seconds Minutes Hours DayofMonth Month DayofWeek
     * 顺序:
     * 秒（0~59）
     * 分钟（0~59）
     * 小时（0~23）
     * 天（月）（0~31，但是你需要考虑你月的天数）
     * 月（0~11）
     * 天（星期）（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
     * 年份（1970－2099）
     * <p>
     * 注:其中每个元素可以是一个值(如6),一个连续区间(9-12),一个间隔时间(8-18/4)(/表示每隔4小时),一个列表(1,3,5),通配符。
     * 由于"月份中的日期"和"星期中的日期"这两个元素互斥的,必须要对其中一个设置?.
     * <p>
     * 每月10号 16点 开始执行任务
     * 获取百度token
     */
    @Scheduled(cron = "0 0 16 10 * ?")
    public void cronScheduled() {
        myServer.updateBaiduToken();
    }




}
