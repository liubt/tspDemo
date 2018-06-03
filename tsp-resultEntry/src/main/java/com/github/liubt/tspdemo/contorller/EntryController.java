package com.github.liubt.tspdemo.contorller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/result")
public class EntryController {

    @Resource(name = "redis1Template")
    private StringRedisTemplate redis1Template;
    @Resource(name = "redis2Template")
    private StringRedisTemplate redis2Template;

    @RequestMapping(value= "/{operateId}", method = RequestMethod.GET)
    public String getResult(@PathVariable String operateId) {
        StringRedisTemplate redisTemplate = getRedisTemplate(operateId);
        if(!redisTemplate.hasKey(operateId)) {
            return "无结果";
        }
        return redisTemplate.opsForValue().get(operateId);
    }

    private StringRedisTemplate getRedisTemplate(String operateId) {
        if(operateId.startsWith("processor1")) {
            return redis1Template;
        } else {
            return redis2Template;
        }
    }
}
