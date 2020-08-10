package com.home.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
	
	@Entity
	@Table(name="person_table")
	//Single Table Inheritance by default as well

/*
 * @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
 * 
 * @DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,name =
 * "record_type")
 * 
 * @DiscriminatorValue(value = "person_type")
 */
 
	//Single Table Inheritance and we have to change IDENTITY to strategy = GenerationType.AUTO
	//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
	
	//Joined Table Inheritance 
	@Inheritance(strategy = InheritanceType.JOINED)
	public class Person {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private Integer id;
		
		@Column(name = "name",length = 100, nullable = false)
		private String name;
		
		@Column(name = "gender")
		private String gender;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}
		
		@Override
		public String toString() {
			return "Person [id=" + id + ", name=" + name + ", gender=" + gender + "]";
		}
}

