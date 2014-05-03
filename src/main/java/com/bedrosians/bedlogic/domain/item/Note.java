package com.bedrosians.bedlogic.domain.item;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;


@Entity
@Table(name = "ims_note", schema = "public")
public class Note implements java.io.Serializable {

	private static final long serialVersionUID = -135822655921787L;
	private long noteId;
	private String noteType;
	private String note;
	private Date createdDate;
	private Date lastModifiedDate;
	private Item item;

	public Note() {
	}

	public Note(String noteType) {
		this.noteType = noteType;
	}
	
	public Note(long noteId) {
		this.noteId = noteId;
	}
	
	@JsonIgnore
	@Id
	@Column(name = "note_id", unique = true, nullable = false, precision = 10, scale = 0)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="ims_note_id_seq_gen")
	@SequenceGenerator(name="ims_note_id_seq_gen", sequenceName="ims_note_note_id_seq")
	public long getNoteId() {
		return this.noteId;
	}

	public void setNoteId(long noteId) {
		this.noteId = noteId;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item_code", nullable = false)
	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	@Column(name="note_type")
	public String getNoteType() {
		return noteType;
	}

	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}
	
	@Column(name="note")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "created_date", updatable=false)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	
	@Temporal(TemporalType.DATE)
	@Column(name = "last_modified_date")
	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
