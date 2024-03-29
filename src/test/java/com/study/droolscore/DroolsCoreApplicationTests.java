package com.study.droolscore;

import com.study.droolscore.domain.Food;
import com.study.droolscore.domain.ShoppingCar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DroolsCoreApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    KieSession kieSession;

    List<Food> planA = Arrays.asList(new Food("巨无霸", new BigDecimal(17D)), new Food("可乐", new BigDecimal(17D)), new Food("薯条", new BigDecimal(17D)));
    ShoppingCar shoppingCar = new ShoppingCar(planA);

    @Test
    public void testHelloWord() {
        kieSession.insert(shoppingCar);
        kieSession.fireAllRules();
        System.out.println(shoppingCar.getTotalMoney());
        System.out.println("调用链：" + shoppingCar.getRules().toString());
    }

}
