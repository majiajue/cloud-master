package com.datababys.entity.main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.datababys.entity.Idable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;


/**
 * @author <a href="mailto:anycc@gmail.com">anycc</a>
 * @since 2014年1月8日 上午10:36:40
 */
@Entity
@Table(name="sys_dictionary")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="com.hp.healthcare.entity.main.Dictionary")
public class Dictionary implements Idable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Length(max = 64)
	@Column(length = 64, nullable = false)
	private String name;

	@Length(max = 128)
	@Column(length = 128)
	private String value;

	@NotNull
	@Range(min = 1L, max = 999L)
	@Column(length = 3, nullable = false)
	private Integer priority;

	@Column(length = 16)
	@Enumerated(EnumType.STRING)
	private DictionaryType type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	private Dictionary parent;

	@OneToMany(mappedBy = "parent", cascade = {
			javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.REMOVE }, orphanRemoval = true)
	@OrderBy("priority ASC")
	private List<Dictionary> children;

	public Dictionary() {
		this.priority = Integer.valueOf(999);

		this.children = new ArrayList<Dictionary>();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public DictionaryType getType() {
		return this.type;
	}

	public void setType(DictionaryType type) {
		this.type = type;
	}

	public Dictionary getParent() {
		return this.parent;
	}

	public void setParent(Dictionary parent) {
		this.parent = parent;
	}

	public List<Dictionary> getChildren() {
		return this.children;
	}

	public void setChildren(List<Dictionary> children) {
		this.children = children;
	}

	public static enum DictionaryType {
		THEME,

		ITEM;
	}
}