package com.datababys.common;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
@Component
public class MPIUtil {
	@PersistenceContext
	private  EntityManager em;
	
	
	/**根据pix查询各表返回list
	 * @param <T>
	 * @param residentsInfo 居民信息
	 * @param c  要查询的表映射的实体entity类
	 * @param sortAttr  指定排序字段
	 * @param args 可变参数 如果有第四个参数 第四个参数查询数目，有第五个参数说明要逆序排列
	 * @return
	 */
//	public <T> List<T> findAllListByPix(ResidentsInfo residentsInfo,Class<T> c,String sortAttr,Integer...args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
//		List<T> list=new ArrayList<T>();
//		String pix = residentsInfo.getPix();
//		
//		String className=c.getSimpleName();
//		List<String> idList=residentsInfoService.getAllIdcardByCurrentPix(pix);
//			String hql="FROM "+className+" A WHERE A.idcard IN (:idList) order by A."+sortAttr;
//			if(9==args[0]){
//				hql+=" desc";
//			}
//			TypedQuery<T> query = em.createQuery(hql, c);
//			query.setParameter("idList", idList);  
//			if(args.length>1){
//				query.setFirstResult(0).setMaxResults(args[1]);
//			}
//			list = query.getResultList();
//			return list;
//	    }
//	
	/**根据idcard查询各表返回list
	 * @param <T>
	 * @param idList 该居民关联的所有id
	 * @param c  要查询的表映射的实体entity类
	 * @param sortAttr  指定排序字段
	 * @param args 可变参数 如果有第四个参数 第四个参数查询数目，有第五个参数说明要逆序排列
	 * @return
	 */
	public <T> List<T> findAllListByIdList(List<String> idList,Class<T> c,String sortAttr,Integer...args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		List<T> list=new ArrayList<T>();
		String className=c.getSimpleName();
			String hql="FROM "+className+" A WHERE A.idcard IN (:idList) order by A."+sortAttr;
			if(9==args[0]){
				hql+=" desc";
			}
			TypedQuery<T> query = em.createQuery(hql, c);
			query.setParameter("idList", idList);  
			if(args.length>1){
				query.setFirstResult(0).setMaxResults(args[1]);
			}
			list = query.getResultList();
			return list;
	    }
	
}
