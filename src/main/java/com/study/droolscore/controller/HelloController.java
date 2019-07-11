package com.study.droolscore.controller;

import com.study.droolscore.dao.FoodDao;
import com.study.droolscore.domain.Food;
import com.study.droolscore.domain.ShoppingCar;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * com.study.droolscore.controller
 *
 * @author jimmy
 * @date 2019-07-11
 */
@RestController
public class HelloController {
    @Autowired
    private KieSession kieSessionStateFul;

    @Autowired
    private FoodDao foodDao;

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request) {
        String idsStr = request.getParameter("ids");
        List<Integer> ids = Arrays.asList(idsStr.split(",")).stream().map(x -> Integer.valueOf(x)).collect(Collectors.toList());
        List<Food> demo = foodDao.findAllById(ids);
        ShoppingCar shoppingCar = new ShoppingCar(demo);
        kieSessionStateFul.insert(shoppingCar);
        kieSessionStateFul.fireAllRules();
        System.out.println(kieSessionStateFul.getGlobal("runningCount"));
        return "总价：" + shoppingCar.getTotalMoney() + "</br>" + "调用链:" + shoppingCar.getRules().toString();
    }
}
