package com.lls.boot.controller;

import com.lls.boot.model.SecKill;
import com.lls.boot.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/************************************
 * SecKillController
 * @author liliangshan
 * @date 2019-03-24
 ************************************/
@Controller
@RequestMapping("/v1/sec_kill")
public class SecKillController {

    @Autowired
    private SecKillService secKillService;

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public ModelAndView getItems(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                 @RequestParam(value = "limit", defaultValue = "20") int limit) {
        List<SecKill> secKills = secKillService.getSecKillList(offset, limit);
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("list", secKills);
        return modelAndView;
    }

    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Long id, Model model) {
        if (id == null) {
            return "redirect:/list";
        }
        SecKill seckill = secKillService.getItem(id);
        if (seckill == null) {
            return "forward:/list";
        }
        model.addAttribute("sec_kill", seckill);
        return "detail";
    }

}
