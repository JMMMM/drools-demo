package com.study.droolscore.service;

import com.google.common.collect.Maps;
import com.study.droolscore.dao.DroolsTemplateDao;
import com.study.droolscore.dao.FoodDao;
import com.study.droolscore.domain.ComboTemplate;
import com.study.droolscore.domain.Food;
import com.study.droolscore.domain.TemplateForBillRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
        Optional<String> foodIdsStrOpt = templates.stream().map(ComboTemplate::getFoodIds).reduce((x, y) -> x + "," + y);
        Optional<Set<Integer>> foodIdsOpt = foodIdsStrOpt.map((ids) -> Arrays.asList(ids.split(",")).stream().map(Integer::parseInt).collect(Collectors.toSet()));
        Optional<Map<Integer, String>> foodsMapOpt = foodIdsOpt.map((foodIds) -> foodDao.findAllById(foodIds).stream().collect(Collectors.toMap(Food::getId, Food::getName)));
        Map<Integer, String> foodNamesMap = foodsMapOpt.orElse(Maps.newHashMap());
        return templates.stream().map((template) -> {
            List<String> ids = Arrays.asList(template.getFoodIds().split(","));
            List<String> foodNames = ids.stream().map((id) -> foodNamesMap.getOrDefault(Integer.parseInt(id), "")).collect(Collectors.toList());
            return billRulesConverter(template, foodNames);
        }).collect(Collectors.toList());
    }

    private TemplateForBillRules billRulesConverter(ComboTemplate comboTemplate, List<String> foodNames) {
        List<String> nameFormatter = foodNames.stream().map((name)->"\""+name+"\"").collect(Collectors.toList());
        return new TemplateForBillRules(nameFormatter, comboTemplate.getComboPrice(), comboTemplate.getComboFoodNum(), comboTemplate.getTemplateName());
    }
}
