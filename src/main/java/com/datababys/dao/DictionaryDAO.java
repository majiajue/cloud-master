/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.dao;


import com.datababys.entity.main.Dictionary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;

public interface DictionaryDAO extends JpaRepository<Dictionary, Long>, JpaSpecificationExecutor<Dictionary> {
	/*@QueryHints(value={
			@QueryHint(name="org.hibernate.cacheable",value="true"),
			@QueryHint(name="org.hibernate.cacheRegion",value="net.hp.es.adm.healthcare.rphcp.entity.main.Dictionary")
		}
	)
	Page<Dictionary> findByParentNameAndType(String name, DictionaryType dictionaryType, Pageable pageable);
	
	@QueryHints(value={
			@QueryHint(name="org.hibernate.cacheable",value="true"),
			@QueryHint(name="org.hibernate.cacheRegion",value="net.hp.es.adm.healthcare.rphcp.entity.main.Dictionary")
		}
	)
	@Query("FROM Dictionary d WHERE d.parent.name=?1 AND d.type='ITEM' ORDER BY d.priority ASC")
	List<Dictionary> findAllItems(String themeName);
	
	@QueryHints(value={
			@QueryHint(name="org.hibernate.cacheable",value="true"),
			@QueryHint(name="org.hibernate.cacheRegion",value="net.hp.es.adm.healthcare.rphcp.entity.main.Dictionary")
		}
	)
	@Query(" FROM Dictionary A WHERE A.value = ?3  AND A.parent.resources =?2  AND A.parent.name = ?1  ")
	Dictionary getNameByThemeAndValue(String theme, String systemCode, String val);
	
	@Query(" FROM Dictionary A WHERE A.value like ?2   AND A.parent.name = ?1")
	List<Dictionary>  getNameByThemeAndPvalue(String theme,String val);

    @Query("FROM Dictionary d WHERE d.parent.name=?1 AND d.parent.resources=?2 AND d.type='ITEM' ORDER BY d.priority ASC")
    List<Dictionary> findAllItemsByThemeNameAndSystemCode(String themeName, String resources);
*/
	 @QueryHints({@javax.persistence.QueryHint(name="org.hibernate.cacheable", value="true"), @javax.persistence.QueryHint(name="org.hibernate.cacheRegion", value="com.hp.healthcare.entity.main.Dictionary")})
	  public abstract Page<Dictionary> findByParentNameAndType(String paramString, Dictionary.DictionaryType paramDictionaryType, Pageable paramPageable);

	  @QueryHints({@javax.persistence.QueryHint(name="org.hibernate.cacheable", value="true"), @javax.persistence.QueryHint(name="org.hibernate.cacheRegion", value="com.hp.healthcare.entity.main.Dictionary")})
	  @Query("FROM Dictionary d WHERE d.parent.name=?1 AND d.type='ITEM' ORDER BY d.priority ASC")
	  public abstract List<Dictionary> findAllItems(String paramString);
      //数据字典，查询下拉框值的dao层（通过父节点名称，查询所有节点内容）
	  @QueryHints({@javax.persistence.QueryHint(name="org.hibernate.cacheable", value="true"), @javax.persistence.QueryHint(name="org.hibernate.cacheRegion", value="com.hp.healthcare.entity.main.Dictionary")})
	  //竟然用sql语句  
	  @Query(" FROM Dictionary A WHERE A.value = ?2   AND A.parent.name = ?1")
	  public abstract Dictionary getNameByThemeAndValue(String paramString1, String paramString2);
	  
		  
	  
}