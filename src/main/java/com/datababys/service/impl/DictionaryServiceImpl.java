/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service.impl;


import com.datababys.dao.DictionaryDAO;
import com.datababys.entity.main.Dictionary;
import com.datababys.service.DictionaryService;
import com.datababys.util.Page;
import com.datababys.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DictionaryServiceImpl implements DictionaryService {
	
	
	 @Autowired
	  private DictionaryDAO dictionaryDAO;

	  public Dictionary get(Long id)
	  {
	    return (Dictionary)this.dictionaryDAO.findOne(id);
	  }
	  //通过字典主题和值，来获取名字
	  public String getNameByThemeAndValue(String theme, String val) {
		 /* System.out.println("主题："+theme);
		  System.out.println("值："+val);*/
	    Dictionary dict = this.dictionaryDAO.getNameByThemeAndValue(theme, val);
	    if ((dict != null) && (dict.getId().longValue() > 0L)) {
	    	/*System.out.println("名字："+dict.getName());*/
	      return dict.getName();
	    }
	    return "";
	  }

	  public void saveOrUpdate(Dictionary dictionary)
	  {
	    this.dictionaryDAO.save(dictionary);
	  }

	  public void delete(Long id)
	  {
	    this.dictionaryDAO.delete(id);
	  }

	  public List<Dictionary> findAll(Page page)
	  {
	    org.springframework.data.domain.Page springDataPage = this.dictionaryDAO.findAll(PageUtils.createPageable(page));
	    page.setTotalCount(springDataPage.getTotalElements());
	    return springDataPage.getContent();
	  }

	  public List<Dictionary> findByExample(Specification<Dictionary> specification, Page page)
	  {
	    org.springframework.data.domain.Page springDataPage = this.dictionaryDAO.findAll(specification, PageUtils.createPageable(page));
	    page.setTotalCount(springDataPage.getTotalElements());
	    return springDataPage.getContent();
	  }

	  public List<Dictionary> findByExample(Specification<Dictionary> specification)
	  {
	    List<Dictionary> springDataList = this.dictionaryDAO.findAll(specification);
	    return springDataList;
	  }
      //查询辖区行政级别代码下拉时，把父名称坐位条件，查出下拉框
	  public List<Dictionary> findByThemeName(String themeName, Page page)
	  {
	    if (page == null) {
	      return this.dictionaryDAO.findAllItems(themeName);
	    }
	    org.springframework.data.domain.Page springDataPage = this.dictionaryDAO.findByParentNameAndType(themeName, Dictionary.DictionaryType.ITEM, PageUtils.createPageable(page));
	    page.setTotalCount(springDataPage.getTotalElements());
	    return springDataPage.getContent();
	  }
	
}
