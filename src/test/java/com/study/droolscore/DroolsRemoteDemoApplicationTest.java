package com.study.droolscore;

import com.study.droolscore.dao.FoodDao;
import com.study.droolscore.domain.Food;
import com.study.droolscore.domain.ShoppingCar;
import org.drools.core.io.impl.UrlResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * com.study.droolscore
 *
 * @author jimmy
 * @date 2019-07-09
 * 远程调用demo
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DroolsRemoteDemoApplicationTest {
    ShoppingCar shoppingCar;
    @Autowired
    FoodDao foodDao;

    @Before
    public void init() {
        //巨无霸套餐
//        List<Food> demo = foodDao.findAllById(Arrays.asList(1, 2, 5));
//        //无套餐
        List<Food> demo = foodDao.findAllById(Arrays.asList(2, 5));
        shoppingCar = new ShoppingCar(demo);
    }
    @Test
    public void testWorkbeanch(){
        String url="http://localhost:8080/drools/maven2/com/study/droolscore/1.0.0/droolscore-1.0.0.jar";
        KieServices kieServices = KieServices.Factory.get();
        KieRepository kieRepository = kieServices.getRepository();
        UrlResource resource = (UrlResource) kieServices.getResources().newUrlResource(url);
        resource.setBasicAuthentication("enabled");
        resource.setUsername("User1");
        resource.setPassword("admin@123");
        InputStream is = null;
        try {
            is = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        KieModule kieModule = kieRepository.addKieModule(kieServices.getResources().newInputStreamResource(is));
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(shoppingCar);
        kieSession.fireAllRules();
        System.out.println(shoppingCar.getTotalMoney());
        System.out.println("调用链：" + shoppingCar.getRules().toString());
    }
}
