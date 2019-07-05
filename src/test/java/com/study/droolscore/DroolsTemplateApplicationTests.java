package com.study.droolscore;

import com.study.droolscore.domain.Food;
import com.study.droolscore.domain.ShoppingCar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DroolsTemplateApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    KieSession kieSession2;

    List<Food> demoA = Arrays.asList(new Food("巨无霸", 17D), new Food("可乐", 17D), new Food("薯条", 17D));

    List<Food> demoB = Arrays.asList(new Food("巨无霸", 17D), new Food("可乐", 17D));

    ShoppingCar shoppingCar = new ShoppingCar(demoB);

    @Test
    public void testHelloWord() {
        kieSession2.insert(shoppingCar);
        kieSession2.fireAllRules();
        System.out.println(shoppingCar.getTotalMoney());
    }

}
