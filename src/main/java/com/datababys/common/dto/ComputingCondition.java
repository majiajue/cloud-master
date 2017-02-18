package com.datababys.common.dto;

import java.util.ArrayList;
import java.util.List;

/**运算条件类
 * @author 方锦文
 *
 */
public class ComputingCondition {
	
	public ComputingCondition(){
		this.cnList=new ArrayList<ComputingCondition>();
	}
	public ComputingCondition(int i){
		
	}
	public ComputingCondition(String fieldName,Object fieldValue,ComputingSymbol symbol){
		this.fieldName=fieldName;
		this.fieldValue=fieldValue;
		this.symbol=symbol;
		this.cnList=new ArrayList<ComputingCondition>();
		this.cnList.add(this);
	}
	/**只需实例化一个对象，持续调用赋值
	 * @param fieldName
	 * @param fieldValue
	 * @param symbol
	 */
	public void setCondition(String fieldName,Object fieldValue,ComputingSymbol symbol){
		ComputingCondition c=new ComputingCondition(0);
		c.fieldName=fieldName;
		c.fieldValue=fieldValue;
		c.symbol=symbol;
		cnList.add(c);
	}
	/**实例化单个Condition对象并赋值
	 * @param fieldName
	 * @param fieldValue
	 * @param symbol
	 */
	public static void initCondition(String fieldName,Object fieldValue,ComputingSymbol symbol){
		new ComputingCondition(fieldName,fieldValue,symbol);
	}
	/**
	 * 字段名
	 */
	private String fieldName;
	/**
	 * 字段值
	 */
	private Object fieldValue;
	/**
	 * 运算符
	 */
	private ComputingSymbol symbol;
	
	/**
	 * 分组字段
	 */
	private String groupField;
	
	/**
	 * 排序字段
	 */
	private String orderByField;
	
	/**
	 * 条件列表
	 */
	private List<ComputingCondition> cnList;
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Object getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}
	public ComputingSymbol getSymbol() {
		return symbol;
	}
	public void setSymbol(ComputingSymbol symbol) {
		this.symbol = symbol;
	}
	public List<ComputingCondition> getCnList() {
		return cnList;
	}
	public void setCnList(List<ComputingCondition> cnList) {
		this.cnList = cnList;
	}
	public String getGroupField() {
		return groupField;
	}
	public void setGroupField(String groupField) {
		this.groupField = groupField;
	}
	public String getOrderByField() {
		return orderByField;
	}
	public void setOrderByField(String orderByField) {
		this.orderByField = orderByField;
	}
}

