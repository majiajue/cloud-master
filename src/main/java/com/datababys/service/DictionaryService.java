/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service;


import com.datababys.entity.main.Dictionary;
import com.datababys.util.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface DictionaryService {
	/*
	Dictionary get(Long id);

	String getNameByThemeAndValue(String theme, String val, String systemCode);
	
	void saveOrUpdate(Dictionary dictionary);

	void delete(Long id);
	
	List<Dictionary> findAll(Page page);
	List<Dictionary> findByExample(Specification<Dictionary> specification);
	
	List<Dictionary> findByExample(Specification<Dictionary> specification, Page page);
	
	List<Dictionary> findByThemeName(String themeName, Page page);

    List<Dictionary> findByThemeNameAndSystemCode(String themeName, String resources);*/
	
	
	public abstract Dictionary get(Long paramLong);

	  public abstract String getNameByThemeAndValue(String paramString1, String paramString2);

	  public abstract void saveOrUpdate(Dictionary paramDictionary);

	  public abstract void delete(Long paramLong);

	  public abstract List<Dictionary> findAll(Page paramPage);

	  public abstract List<Dictionary> findByExample(Specification<Dictionary> paramSpecification);

	  public abstract List<Dictionary> findByExample(Specification<Dictionary> paramSpecification, Page paramPage);

	  public abstract List<Dictionary> findByThemeName(String paramString, Page paramPage);
}
