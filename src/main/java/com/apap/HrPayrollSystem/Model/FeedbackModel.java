package com.apap.HrPayrollSystem.Model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="feedback")
public class FeedbackModel implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pegawai",referencedColumnName="id",nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private PegawaiOutsourcingModel pegawai_outsourcing;	
	
	//Fk to proyek id
	@NotNull
	@Size(max=50)
	@Column(name="proyek",nullable=false)
	private String proyek;
	
	@NotNull
	@Size(max=255)
	@Column(name="feedback",nullable=false)
	private String feedback;
	
	
	@Column(name="tanggal_pengisian_feedback",nullable=true)
	private Date tanggal_pengisian_feedback;
	


	public PegawaiOutsourcingModel getPegawai_outsourcing() {
		return pegawai_outsourcing;
	}

	public void setPegawai_outsourcing(PegawaiOutsourcingModel pegawai_outsourcing) {
		this.pegawai_outsourcing = pegawai_outsourcing;
	}

	public String getProyek() {
		return proyek;
	}

	public void setProyek(String proyek) {
		this.proyek = proyek;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Date getTanggal_pengisian_feedback() {
		return tanggal_pengisian_feedback;
	}

	public void setTanggal_pengisian_feedback(Date tanggal_pengisian_feedback) {
		this.tanggal_pengisian_feedback = tanggal_pengisian_feedback;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	
	
}
