package com.lls.boot.controller;

import com.lls.boot.dto.Execution;
import com.lls.boot.dto.Exposer;
import com.lls.boot.dto.Result;
import com.lls.boot.enums.SecKillStateEnum;
import com.lls.boot.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/************************************
 * SecKillController
 * @author liliangshan
 * @date 2019-03-24
 ************************************/
@RestController
@RequestMapping("/v1/sec_kill")
public class SecKillRestController {

    private static final Logger logger = LoggerFactory.getLogger(SecKillRestController.class);

    @Autowired
    private SecKillService secKillService;

    @RequestMapping(method = RequestMethod.GET, value = "/time_now")
    public Result<Long> getCurrentTime() {
        return new Result<>(true, System.currentTimeMillis());
    }

    @RequestMapping(value = "/{id}/execution/{executedMd5}", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public Result<Execution> execute(@PathVariable("id") long id,
                                     @PathVariable("executedMd5") String executedMd5,
                                     @CookieValue(value = "phone", required = false) Long phone) {
        Result<Execution> result = new Result<>(true, null);
        try {
            Execution execution = secKillService.executeProcedure(id, String.valueOf(phone), executedMd5);
            result.setData(execution);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            Execution execution = new Execution(id, SecKillStateEnum.INNER_ERROR);
            result.setData(execution);
        }
        return result;
    }

    @RequestMapping(value = "/{id}/exposer", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public Result<Exposer> getExposer(@PathVariable("id") Long id) {
        Result<Exposer> result = new Result<>(false, null);
        try {
            Exposer exposer = secKillService.getSecKillExposer(id);
            result.setData(exposer);
            result.setStatus(true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setStatus(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

}
