package com.datababys.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import com.datababys.common.dto.ComputingCondition;
import com.datababys.common.dto.ComputingSymbol;
import com.datababys.common.dto.DTPager;
import com.datababys.dao.JpqlBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;



/**
 * 公用模板辅助类
 * 
 * @author 方锦文
 * @param <T>
 */
@Component
public class TempAuxiliaryUtils<T> {

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * 查询条件（jpa自带函数使用） 实体类属性有值 自动添加条件目前支持String、Long、Date 目前支持等于 Date类型 只有是begin 和end 开头才会添加入条件
     * 
     * @param T
     * @return
     */
    public Specification<T> buildWhereClause(final T entity) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                Field[] fields = entity.getClass().getDeclaredFields();
                ;
                Predicate p_date;
                try {
                    for (Field field : fields) {
                        Method method = entity.getClass().getMethod(
                                "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
                        Type type = method.getGenericReturnType();
                        Object obj = method.invoke(entity);
                        if (obj != null) {
                            if ("class java.lang.String".equals(type.toString()))
                                if (StringUtils.isNotBlank(obj.toString())) {
                                    Path<String> namePath = root.get(field.getName());
                                    String pattern = obj.toString();
                                    predicateList.add(cb.equal(namePath, pattern));
                                }
                            if ("class java.lang.Long".equals(type.toString())) {
                                Path<Long> namePath = root.get(field.getName());
                                Long pattern = (Long) obj;
                                predicateList.add(cb.equal(namePath, pattern));
                            }
                            if ("class java.util.Date".equals(type.toString())) {
                                Expression<Date> EDate = cb.literal((Date) obj);
                                // 转换传递时间
                                EntityType<T> et = root.getModel();
                                if ("begin".equals(field.getName().substring(0, 5))) {
                                    p_date = cb.greaterThanOrEqualTo(
                                            root.get(et.getSingularAttribute(field.getName(), Date.class)), EDate);
                                    predicateList.add(p_date);
                                }
                                if ("end".equals(field.getName().substring(0, 3))) {
                                    p_date = cb.lessThanOrEqualTo(
                                            root.get(et.getSingularAttribute(field.getName(), Date.class)), EDate);
                                    predicateList.add(p_date);
                                }

                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Predicate[] predicateArr = new Predicate[predicateList.size()];
                return cb.and(predicateList.toArray(predicateArr));
            }

        };
    }

    /**
     * like查询String类型参数
     * 
     * @param entity
     * @return
     */
    public Specification<T> buildWhereClauseLike(final T entity) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                Field[] fields = entity.getClass().getDeclaredFields();
                ;
                Predicate p_date;
                try {
                    for (Field field : fields) {
                        Method method = entity.getClass().getMethod(
                                "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
                        Type type = method.getGenericReturnType();
                        Object obj = method.invoke(entity);
                        if (obj != null) {
                            if ("class java.lang.String".equals(type.toString()))
                                if (StringUtils.isNotBlank(obj.toString())) {
                                    Path<String> namePath = root.get(field.getName());
                                    String pattern = obj.toString();
                                    predicateList.add(cb.like(namePath, "%" + pattern + "%"));
                                }
                            if ("class java.lang.Long".equals(type.toString())) {
                                Path<Long> namePath = root.get(field.getName());
                                Long pattern = (Long) obj;
                                predicateList.add(cb.equal(namePath, pattern));
                            }
                            if ("class java.util.Date".equals(type.toString())) {
                                Expression<Date> EDate = cb.literal((Date) obj);
                                // 转换传递时间
                                EntityType<T> et = root.getModel();
                                if ("begin".equals(field.getName().substring(0, 5))) {
                                    p_date = cb.greaterThanOrEqualTo(
                                            root.get(et.getSingularAttribute(field.getName(), Date.class)), EDate);
                                    predicateList.add(p_date);
                                }
                                if ("end".equals(field.getName().substring(0, 3))) {
                                    p_date = cb.lessThanOrEqualTo(
                                            root.get(et.getSingularAttribute(field.getName(), Date.class)), EDate);
                                    predicateList.add(p_date);
                                }

                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Predicate[] predicateArr = new Predicate[predicateList.size()];
                return cb.and(predicateList.toArray(predicateArr));
            }

        };
    }

    private List<ComputingCondition> conditions;

    /**
     * 查询条件 条件连接（and）（jpa自带函数使用）
     * 
     * @param entity
     * @param conditions 示例：<br/>
     *            ComputingCondition c=new ComputingCondition();<br/>
     *            c.setCondition("orgCode",jkEtLog.getOrgCode() , ComputingSymbol.EQUAL);<br/>
     *            c.setCondition("batchid","YANGZHOU_20140909_20140909" , ComputingSymbol.LIKE);<br/>
     *            c.setCondition("beginDate",new Date() , ComputingSymbol.LESSTHAN);<br/>
     *            tempAuxiliaryUtils.buildWhereClause(c.getCnList());<br/>
     * @return
     */
    public Specification<T> buildWhereClause(List<ComputingCondition> cs) {
        this.conditions = cs;
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                Predicate p_cate;
                Expression<Date> EDate = null;
                EntityType<T> et = root.getModel();
                try {
                    for (ComputingCondition condition : conditions) {
                        if (condition.getFieldValue().getClass() == Date.class)
                            EDate = cb.literal((Date) condition.getFieldValue());
                        if (condition.getSymbol().equals(ComputingSymbol.EQUAL)) {
                            if (EDate != null) {
                                p_cate = cb.equal(
                                        root.get(et.getSingularAttribute(condition.getFieldName(), Date.class)), EDate);
                                predicateList.add(p_cate);
                            } else {
                                Path<String> namePath = root.get(condition.getFieldName());
                                predicateList.add(cb.equal(namePath, condition.getFieldValue()));
                            }
                        } else if (condition.getSymbol().equals(ComputingSymbol.GREATERTHAN)) {
                            if (EDate != null) {
                                p_cate = cb.greaterThan(
                                        root.get(et.getSingularAttribute(condition.getFieldName(), Date.class)), EDate);
                                predicateList.add(p_cate);
                            } else {
                                Path<Number> namePath = root.get(condition.getFieldName());
                                predicateList.add(cb.gt(namePath, (Number) condition.getFieldValue()));
                            }
                        } else if (condition.getSymbol().equals(ComputingSymbol.LESSTHAN)) {
                            if (EDate != null) {
                                p_cate = cb.lessThan(
                                        root.get(et.getSingularAttribute(condition.getFieldName(), Date.class)), EDate);
                                predicateList.add(p_cate);
                            } else {
                                Path<Number> namePath = root.get(condition.getFieldName());
                                predicateList.add(cb.lt(namePath, (Number) condition.getFieldValue()));
                            }
                        } else if (condition.getSymbol().equals(ComputingSymbol.GREATERTHANOREQUAL)) {
                            if (EDate != null) {
                                p_cate = cb.greaterThanOrEqualTo(
                                        root.get(et.getSingularAttribute(condition.getFieldName(), Date.class)), EDate);
                                predicateList.add(p_cate);
                            } else {
                                Path<Number> namePath = root.get(condition.getFieldName());
                                predicateList.add(cb.ge(namePath, (Number) condition.getFieldValue()));
                            }
                        } else if (condition.getSymbol().equals(ComputingSymbol.LESSTHANOREQUAL)) {
                            if (EDate != null) {
                                p_cate = cb.lessThanOrEqualTo(
                                        root.get(et.getSingularAttribute(condition.getFieldName(), Date.class)), EDate);
                                predicateList.add(p_cate);
                            } else {
                                Path<Number> namePath = root.get(condition.getFieldName());
                                predicateList.add(cb.le(namePath, (Number) condition.getFieldValue()));
                            }
                        } else if (condition.getSymbol().equals(ComputingSymbol.LIKE)) {
                            if (EDate == null) {
                                Path<String> namePath = root.get(condition.getFieldName());
                                predicateList.add(cb.like(namePath, "%" + condition.getFieldValue().toString() + "%"));
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Predicate[] predicateArr = new Predicate[predicateList.size()];
                return cb.and(predicateList.toArray(predicateArr));
            }

        };
    }

    /**
     * 不通过jpa调用 重写调用hql方法（带分页）
     * 
     * @param hql
     * @param pageable
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Page findBySearch(String hql, Pageable pageable) {
        JpqlBuilder jpql = new JpqlBuilder();
        if (!hql.isEmpty()) {
            jpql.append(hql);
        }
        return jpql.page(em, pageable);
    }

    /**
     * @param hql
     * @param pageable
     * @param condition 运算条件：<br/>
     *            多个条件示例：ComputingCondition c=new ComputingCondition();<br/>
     *            c.setCondition("id",1 , ComputingSymbol.EQUAL);<br/>
     *            c.setCondition("endDate",new Date() , ComputingSymbol.GREATERTHAN);<br/>
     *            tempAuxiliaryUtils.findBySearch(hql.toString(),
     *            tempAuxiliaryUtils.buildPageRequest(pager),c.getCnList());<br/>
     *            单个条件示例： ComputingCondition c=new ComputingCondition("beginDate",new Date() , ComputingSymbol.EQUAL);
     *            <br/>
     *            tempAuxiliaryUtils.findBySearch(hql.toString(),
     *            tempAuxiliaryUtils.buildPageRequest(pager),c.getCnList());<br/>
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Page findBySearch(StringBuffer hql, Pageable pageable, ComputingCondition condition) {
        JpqlBuilder jpql = new JpqlBuilder();
        if (hql.toString().isEmpty())
            return null;
        if (!hql.toString().matches(".+where.*"))
            hql.append(" where 1=1");
        getConditionsJpql(jpql, hql, condition);
        return jpql.page(em, pageable);
    }

    /**
     * 不通过jpa调用 重写调用hql方法（查询所有数据不带分页）
     * 
     * @param hql
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List findBySearch(String hql) {

        JpqlBuilder jpql = new JpqlBuilder();
        if (!hql.isEmpty()) {
            jpql.append(hql);
        }
        return jpql.list(em);
    }

    /**
     * 不通过jpa调用 重写调用hql方法（查询带条件，数据不分页）
     * 
     * @param hql
     * @param condition 运算条件：<br/>
     *            多个条件示例：ComputingCondition c=new ComputingCondition();<br/>
     *            c.setCondition("id",1 , ComputingSymbol.EQUAL);<br/>
     *            c.setCondition("endDate",new Date() , ComputingSymbol.GREATERTHAN);<br/>
     *            tempAuxiliaryUtils.findBySearch(hql.toString(),c.getCnList());<br/>
     *            单个条件示例： ComputingCondition c=new ComputingCondition("beginDate",new Date() , ComputingSymbol.EQUAL);
     *            <br/>
     *            tempAuxiliaryUtils.findBySearch(hql.toString(),c.getCnList());
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List findBySearch(StringBuffer hql, ComputingCondition condition) {
        JpqlBuilder jpql = new JpqlBuilder();
        if (hql.toString().isEmpty())
            return null;
        if (!hql.toString().matches(".+where.*"))
            hql.append(" where 1=1");
        getConditionsJpql(jpql, hql, condition);
        return jpql.list(em);
    }

    private void getConditionsJpql(JpqlBuilder jpql, StringBuffer hql, ComputingCondition cn) {
        int i = 0;
        String fieldName = "";
        for (ComputingCondition condition : cn.getCnList()) {
            if (condition.getFieldValue() == null)
                continue;
            if (StringUtils.isBlank(condition.getFieldValue().toString()))
                continue;
            i++;
            hql.append(" and ");
            hql.append(condition.getFieldName());
            fieldName = condition.getFieldName();
            fieldName = fieldName.matches(".+\\..*") ? fieldName.replace(".", "_") : fieldName;
            fieldName = fieldName + i;
            if (condition.getSymbol().equals(ComputingSymbol.EQUAL))
                hql.append(" = ");
            else if (condition.getSymbol().equals(ComputingSymbol.GREATERTHAN))
                hql.append(" > ");
            else if (condition.getSymbol().equals(ComputingSymbol.LESSTHAN))
                hql.append(" < ");
            else if (condition.getSymbol().equals(ComputingSymbol.GREATERTHANOREQUAL))
                hql.append(" >= ");
            else if (condition.getSymbol().equals(ComputingSymbol.LESSTHANOREQUAL))
                hql.append(" <= ");
            if (condition.getSymbol().equals(ComputingSymbol.LIKE)) {
                hql.append(" like ");
                jpql.setParameter(fieldName, condition.getFieldValue());
            } else
                jpql.setParameter(fieldName, condition.getFieldValue());
            hql.append(":" + fieldName);
        }
        if (StringUtils.isNotBlank(cn.getGroupField()))
            hql.append(" group by " + cn.getGroupField());
        if (StringUtils.isNotBlank(cn.getOrderByField()))
            hql.append(" order by " + cn.getOrderByField());
        jpql.append(hql.toString());
    }

    /**
     * 查询条件（不通过jpa调用 使用） 实体类属性有值 自动拼接成条件 hql 目前支持String、Long 目前只支持等于
     * 
     * @param T
     * @return
     */
    public void dynamicConditions(final T entity, StringBuffer hql) {
        Field[] fields = entity.getClass().getDeclaredFields();
        ;
        try {
            for (Field field : fields) {
                Method method = entity.getClass().getMethod(
                        "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
                Type type = method.getGenericReturnType();
                Object obj = method.invoke(entity);
                if (obj != null) {
                    if ("class java.lang.String".equals(type.toString()))
                        if (StringUtils.isNotBlank(obj.toString())) {
                            hql.append(" and " + field.getName() + "='" + obj + "'");
                        }
                    if ("class java.lang.Long".equals(type.toString())) {
                        hql.append(" and " + field.getName() + "=" + obj);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置分页
     * 
     * @param pager
     * @return
     */
    public PageRequest buildPageRequest(DTPager pager) {
        return new PageRequest((int) pager.getStart() / pager.getLength(), pager.getLength());
    }

    /**
     * added by songs at 2015-01-20 设置分页带排序
     * 
     * @param pager
     * @return
     */
    public PageRequest buildPageRequest(DTPager pager, Sort sort) {
        return new PageRequest((int) pager.getStart() / pager.getLength(), pager.getLength(), sort);
    }

    /**
     * 将数组转化为对象
     * 
     * @param objs
     * @param entity
     * @param fields
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List objectArrToObject(List objs, T entity, String fields) {
        List list = new ArrayList();
        String[] fles = fields.split(",");
        try {
            for (int i = 0; i < objs.size(); i++) {
                T c = (T) entity.getClass().newInstance();
                for (int j = 0; j < fles.length; j++) {
                    Object[] objects = (Object[]) objs.get(0);
                    Field field = c.getClass().getDeclaredField(fles[j]);
                    Method method = c.getClass().getMethod(
                            "set" + fles[j].substring(0, 1).toUpperCase() + fles[j].substring(1), field.getType());
                    method.invoke(c, objects[j]);
                }
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
