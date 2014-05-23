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
import javax.persistence.Version;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "ims_note", schema = "public")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="note")
public class Note implements java.io.Serializable {

	private static final long serialVersionUID = -135822655921787L;
	private long noteId;
	private String noteType;
	private String note;
	private Date createdDate;
	private Date lastModifiedDate;
	private Integer version;
	private Item item;

	public Note() {
	}

	public Note(String noteType) {
		this.noteType = noteType;
	}
	
	public Note(long noteId) {
		this.noteId = noteId;
	}
	
	public Note(long noteId, String noteType, String note, Date createdDate,
			Date lastModifiedDate, Integer version, Item item) {
		super();
		this.noteId = noteId;
		this.noteType = noteType;
		this.note = note;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.version = version;
		this.item = item;
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

	@JsonIgnore
	@Version
	@Column(name = "version")
	public Integer getVersion(){
		return version;
	}
	
	private void setVersion(Integer version){
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.getItemcode().hashCode());
		result = prime * result + (int) (noteId ^ (noteId >>> 32));
		result = prime * result
				+ ((noteType == null) ? 0 : noteType.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Note))
			return false;
		Note other = (Note) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.getItemcode().equals(other.item.getItemcode()))
			return false;
		if (noteId != other.noteId)
			return false;
		if (noteType == null) {
			if (other.noteType != null)
				return false;
		} else if (!noteType.equals(other.noteType))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
}
