package com.atguigu.daijia.dispatch.xxl.job;

import com.atguigu.daijia.dispatch.mapper.XxlJobLogMapper;
import com.atguigu.daijia.dispatch.service.NewOrderService;
import com.atguigu.daijia.model.entity.dispatch.XxlJobLog;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*@Project_name:daijia-parent
@Create 2024/11/12 下午12:13
Description:
*/
@Component
public class JobHandler {
    @Autowired
    private XxlJobLogMapper xxlJobLogMapper;
    @Autowired
    private NewOrderService newOrderService;
    @XxlJob("newOrderTaskHandler")
    public void newOrderTaskHandler() {
        XxlJobLog xxlJobLog = new XxlJobLog();
        xxlJobLog.setJobId(XxlJobHelper.getJobId());
        long start = System.currentTimeMillis();
        try {
            newOrderService.executeTask(XxlJobHelper.getJobId());
            xxlJobLog.setStatus(1);
        } catch (Exception e) {
            xxlJobLog.setStatus(0);
        } finally {
            //log记录事物分发日志
            long end=System.currentTimeMillis();
            xxlJobLog.setTimes((int)(end-start));
            xxlJobLogMapper.insert(xxlJobLog);
        }
    }
}
