package com.github.liubt.tspdemo.contorller;

import com.github.liubt.tspdemo.service.KafkaSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/operate")
public class EntryController {

    @Autowired
    private KafkaSendService kafkaSendService;

    @RequestMapping(method = RequestMethod.GET)
    public String doOperate(@RequestParam String vin) {
        String topic = this.getTopic(vin);
        kafkaSendService.send(getTopic(vin), this.generateOperateId(topic));
        return "success";
    }

    /**
     * 根据vin码的哈希值的奇偶确定topic
     * 
     * @param vin
     * @return
     */
    private String getTopic(String vin) {
        if(vin.hashCode()%2 == 0) {
            return "processor1";
        } else {
            return "processor2";
        }
    }

    /**
     * 生成操作ID，客户端根据此ID获取操作结果
     * 
     * @param topic
     * @return
     */
    private String generateOperateId(String topic) {
        return new StringBuilder()
                .append(topic)
                .append("-")
                .append(UUID.randomUUID().toString().replace("-", ""))
                .toString();
    }
}
