package com.study.droolscore;

import com.study.droolscore.dao.FoodDao;
import com.study.droolscore.domain.Food;
import com.study.droolscore.domain.ShoppingCar;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * //TODO
 * 目前匹配规则不满足业务，例如 套餐是：巨无霸，可乐，薯条
 * 但是喔购买的是：巨无霸，可乐，薯条，麦乐鸡
 * 这种情况下无法匹配，这里需要统计一个满足条件的合集，用于给客户端获取最优解。
 * 这里可以通过在shoppingCar中增加map属性，套餐name->totalMoney
 * 做更细致的扩展，这里只是demo，所以暂时不考虑太复杂的情景。
 * 如果要满足上面要求，需要把
 * List($foods.size()==@{buyListSize}) from collect(Food(@{fooPromotion} contains name) from $foods)
 * 改为
 * List(size==@{buyListSize}) from collect(Food(@{fooPromotion} contains name) from $foods)
 * <p>
 * 这里的意思是，购物车中包含的那部分食物的数量，应该和套餐的数量一致。
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DroolsTemplateApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    KieSession kieSessionStateFul;

    @Autowired
    StatelessKieSession kieSessionStateLess;

    @Autowired
    FoodDao foodDao;

    ShoppingCar shoppingCar;

    @Before
    public void init() {
        //巨无霸套餐
//        List<Food> demo = foodDao.findAllById(Arrays.asList(1, 2, 5));
//        //无套餐
//        List<Food> demo = foodDao.findAllById(Arrays.asList(2, 5));
        //所有套餐
        List<Food> demo =foodDao.findAllById(Arrays.asList(1,2,3,4,5,6,7,8));
        shoppingCar = new ShoppingCar(demo);
    }

    @Test
    public void kieSessionStateFulTest() {
        kieSessionStateFul.insert(shoppingCar);
        kieSessionStateFul.fireAllRules();
        System.out.println(shoppingCar.getTotalMoney());
        System.out.println("调用链：" + shoppingCar.getRules().toString());
    }

    /**
     * 有状态的会话测试
     * 这里的问题要到javademo里面解释
     * com.droolstest.drools.StateFulSessionTestDemo
     */
    @Test
    public void stateFulTest(){
        kieSessionStateFul.insert(shoppingCar);
        kieSessionStateFul.fireAllRules();
        System.out.println(shoppingCar.getTotalMoney());
        System.out.println("调用链：" + shoppingCar.getRules().toString());
        List<Food> demo2 = foodDao.findAllById(Arrays.asList(2, 5));
        kieSessionStateFul.insert(demo2);
        kieSessionStateFul.fireAllRules();
        System.out.println(shoppingCar.getTotalMoney());
        System.out.println("调用链：" + shoppingCar.getRules().toString());
    }

    @Autowired
    private StatelessKieSession statelessKieSession;
    @Test
    public void stateLessTest(){
        statelessKieSession.execute(shoppingCar);
        System.out.println(shoppingCar.getTotalMoney());
        System.out.println("调用链：" + shoppingCar.getRules().toString());
    }
}
