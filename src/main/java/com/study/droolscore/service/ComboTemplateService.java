package com.study.droolscore.service;

import com.study.droolscore.dao.DroolsTemplateDao;
import com.study.droolscore.dao.FoodDao;
import com.study.droolscore.domain.ComboTemplate;
import com.study.droolscore.domain.Food;
import com.study.droolscore.domain.TemplateForBillRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * com.study.droolscore.service
 *
 * @author jimmy
 * @date 2019-07-09
 */
@Service
public class ComboTemplateService {

    @Autowired
    private DroolsTemplateDao droolsTemplateDao;

    @Autowired
    private FoodDao foodDao;

    public List<TemplateForBillRules> createTemplate() {
        List<ComboTemplate> templates = droolsTemplateDao.findAll();
        String foodIdsStr = templates.stream().map(ComboTemplate::getFoodIds).reduce((x, y) -> x + "," + y).get();
        Set<Integer> foodIds = Arrays.asList(foodIdsStr.split(",")).stream().map(Integer::parseInt).collect(Collectors.toSet());
        Map<Integer, Food> foodNamesMap = foodDao.findAllById(foodIds).stream().collect(Collectors.toMap(Food::getId, Function.identity()));
        return templates.stream().map((template) -> {
            List<String> ids = Arrays.asList(template.getFoodIds().split(","));
            List<Food> foodNames = ids.stream().map((id) -> foodNamesMap.get(Integer.parseInt(id))).collect(Collectors.toList());
            return billRulesConverter(template, foodNames);
        }).collect(Collectors.toList());
    }

    private TemplateForBillRules billRulesConverter(ComboTemplate comboTemplate, List<Food> foods) {
        return new TemplateForBillRules(foods,comboTemplate.getComboPrice(), comboTemplate.getComboFoodNum(), comboTemplate.getTemplateName());
    }
}
