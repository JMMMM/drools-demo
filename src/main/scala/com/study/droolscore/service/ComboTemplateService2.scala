package com.study.droolscore.service

import com.study.droolscore.dao.{DroolsTemplateDao, FoodDao}
import com.study.droolscore.domain.{ComboTemplate, TemplateForBillRules}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import scala.jdk.CollectionConverters._

/**
  * com.study.droolscore.service
  *
  * @author jimmy
  * @date 2019-07-10
  */
@Service
class ComboTemplateService2 {

  @Autowired var droolsTemplateDao: DroolsTemplateDao = _
  @Autowired var foodDao: FoodDao = _

  def createTemplate(): List[TemplateForBillRules] = {
    val templates = droolsTemplateDao.findAll().asScala
    val foodIds = templates.map(_.getFoodIds().split(",").toList).flatten.map(Integer.valueOf(_))
    val foodNameMap = foodDao.findAllById(foodIds.asJava).asScala.map(x => x.getId -> x.getName).toMap
    templates.map(template => {
      val foodNames = template.getFoodIds.split(",").toList.map(idStr => foodNameMap(Integer.valueOf(idStr)))
      billRulesConverter(template, foodNames)
    }).toList
  }

  private def billRulesConverter(comboTemplate: ComboTemplate, foodNames: List[String]): TemplateForBillRules = {
    val nameFormatter = foodNames.map(x => s"\"${x}\"")
    new TemplateForBillRules(nameFormatter.asJava, comboTemplate.getComboPrice, comboTemplate.getComboFoodNum, comboTemplate.getTemplateName)
  }
}
