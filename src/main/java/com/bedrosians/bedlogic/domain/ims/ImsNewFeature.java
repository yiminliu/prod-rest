package com.bedrosians.bedlogic.domain.ims;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.bedrosians.bedlogic.domain.ims.enums.Body;
import com.bedrosians.bedlogic.domain.ims.enums.DesignLook;
import com.bedrosians.bedlogic.domain.ims.enums.DesignStyle;
import com.bedrosians.bedlogic.domain.ims.enums.Edge;
import com.bedrosians.bedlogic.domain.ims.enums.Grade;
import com.bedrosians.bedlogic.domain.ims.enums.MpsCode;
import com.bedrosians.bedlogic.domain.ims.enums.Status;
import com.bedrosians.bedlogic.domain.ims.enums.SurfaceApplication;
import com.bedrosians.bedlogic.domain.ims.enums.SurfaceFinish;
import com.bedrosians.bedlogic.domain.ims.enums.SurfaceType;
import com.bedrosians.bedlogic.util.FormatUtil;


@Entity
@Table(name = "ims_new_feature", schema = "public")
@DynamicUpdate
@DynamicInsert
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Indexed
public class ImsNewFeature implements java.io.Serializable {
   
	private static final long serialVersionUID = -11135822658657L;
	
	private String itemCode;
	private Grade grade = null;
	private Status status = null;
	private Body body = null;
	private Edge edge = null;
	private MpsCode mpsCode = null;
	private DesignLook designLook = null;
	private DesignStyle designStyle = null;
	private SurfaceApplication surfaceApplication = null;
	private SurfaceType surfaceType = null;
	private SurfaceFinish surfaceFinish = null;
	private Integer warranty;
	private String recommendedGroutJointMin;
	private String recommendedGroutJointMax;
	private Date createdDate;
	private Date launchedDate;
	private Date droppedDate;
	private Date lastModifiedDate;
	private Integer version;
	private Ims item;
	
	
	@JsonIgnore
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "item"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "item_code", unique = true, nullable = false)
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@ContainedIn
	public Ims getItem() {
		return this.item;
	}

	public void setItem(Ims item) {
		this.item = item;
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
	
	@JoinColumn(name = "edge")
	@Enumerated(EnumType.STRING)
	public Edge getEdge() {
		return this.edge;
	}

	public void setEdge(Edge edge) {
		this.edge = edge;
	}

	@Column(name="mps_code")
	@Enumerated(EnumType.STRING)
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	public MpsCode getMpsCode() {
		return mpsCode;
	}

	public void setMpsCode(MpsCode mpsCode) {
		this.mpsCode = mpsCode;
	}

	@Column(name = "body")
	@Enumerated(EnumType.STRING)
	public Body getBody() {
		return this.body;
	}

	public void setBody(Body body) {
		this.body = body;
	}
	
	@Column(name = "grade")
	@Enumerated(EnumType.STRING)
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	public Grade getGrade() {
		return this.grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	@Column(name="status")
	@Enumerated(EnumType.STRING)
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Column(name="design_look")
	@Enumerated(EnumType.STRING)
	public DesignLook getDesignLook() {
		return this.designLook;
	}

	public void setDesignLook(DesignLook designLook) {
		this.designLook = designLook;
	}

	@Column(name="design_style")
	@Enumerated(EnumType.STRING)
	public DesignStyle getDesignStyle() {
		return this.designStyle;
	}

	public void setDesignStyle(DesignStyle designStyle) {
		this.designStyle = designStyle;
	}
		
	@Column(name = "surface_application")
	@Enumerated(EnumType.STRING)
	public SurfaceApplication getSurfaceApplication() {
		return this.surfaceApplication;
	}

	public void setSurfaceApplication(SurfaceApplication surfaceApplication) {
		this.surfaceApplication = surfaceApplication;
	}

	@Column(name = "surface_type")
	@Enumerated(EnumType.STRING)
	public SurfaceType getSurfaceType() {
		return this.surfaceType;
	}

	public void setSurfaceType(SurfaceType surfaceType) {
		this.surfaceType = surfaceType;
	}

	@Column(name = "surface_finish")
	@Enumerated(EnumType.STRING)
	public SurfaceFinish getSurfaceFinish() {
		return this.surfaceFinish;
	}

	public void setSurfaceFinish(SurfaceFinish surfaceFinish) {
		this.surfaceFinish = surfaceFinish;
	}
	
    @Column(name="recommended_grout_joint_min")
	public String  getRecommendedGroutJointMin() {
		return recommendedGroutJointMin;
	}

	public void setRecommendedGroutJointMin(String  recommendedGroutJointMin) {
		this.recommendedGroutJointMin = recommendedGroutJointMin;
	}
	
	 @Column(name="recommended_grout_joint_max")
	 public String  getRecommendedGroutJointMax() {
		return recommendedGroutJointMax;
	 }

	 public void setRecommendedGroutJointMax(String recommendedGroutJointMax) {
		this.recommendedGroutJointMax = recommendedGroutJointMax;
	}
    
	@Column(name="warranty")
	public Integer getWarranty() {
		return warranty;
	}

	public void setWarranty(Integer warranty) {
		this.warranty = warranty;
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
	@Column(name = "launched_date", length = 13)
	public Date getLaunchedDate() {
		return this.launchedDate;
	}

	public void setLaunchedDate(Date launchedDate) {
		this.launchedDate = launchedDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dropped_date", length = 13)
	public Date getDroppedDate() {
		return FormatUtil.process(this.droppedDate);
	}

	public void setDroppedDate(Date droppedDate) {
		this.droppedDate = droppedDate;
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
	@Transient
	public boolean isEmpty(){
		return (grade == null && status == null && mpsCode == null && designLook == null && designStyle == null && body == null && 
				edge == null && surfaceApplication == null && surfaceType == null && surfaceFinish == null && warranty == null && 
				recommendedGroutJointMin == null && recommendedGroutJointMax == null);
		
	}

	public ImsNewFeature() {
	}
	
	public ImsNewFeature(String itemCode) {
		this.itemCode = itemCode;
	}
	
	public ImsNewFeature(String itemCode, Grade grade, Status status,
			Body body, Edge edge, MpsCode mpsCode, DesignLook designLook,
			DesignStyle designStyle, SurfaceApplication surfaceApplication,
			SurfaceType surfaceType, SurfaceFinish surfaceFinish,
			Integer warranty, String recommendedGroutJointMin,
			String recommendedGroutJointMax, Date createdDate,
			Date launchedDate, Date droppedDate, Date lastModifiedDate,
			Integer version) {
		super();
		this.itemCode = itemCode;
		this.grade = grade;
		this.status = status;
		this.body = body;
		this.edge = edge;
		this.mpsCode = mpsCode;
		this.designLook = designLook;
		this.designStyle = designStyle;
		this.surfaceApplication = surfaceApplication;
		this.surfaceType = surfaceType;
		this.surfaceFinish = surfaceFinish;
		this.warranty = warranty;
		this.recommendedGroutJointMin = recommendedGroutJointMin;
		this.recommendedGroutJointMax = recommendedGroutJointMax;
		this.createdDate = createdDate;
		this.launchedDate = launchedDate;
		this.droppedDate = droppedDate;
		this.lastModifiedDate = lastModifiedDate;
		this.version = version;
	}

	@JsonIgnore
	@Transient
	static public List<String> allProperties(){
		return Arrays.asList("grade", "status", "mpsCode", "designLook", "designStyle", "body", "edge", "surfaceApplication", 
				               "surfaceType" , "surfaceFinish", "recommendedGroutJoint", "createdDate", "launchedDate", "lastModifiedDate");
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemCode == null) ? 0 : itemCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImsNewFeature other = (ImsNewFeature) obj;
		if (itemCode == null) {
			if (other.itemCode != null)
				return false;
		} else if (!itemCode.equals(other.itemCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NewFeature ["
				+ "itemcode=" + itemCode 
				+ ", imsEdge=" + edge
				+ ", mpsCode =" + mpsCode
				+ ", body=" + body 
				+ ", grade=" + grade 
				+ ", status=" + status
				+ ", imsSurfaceApplication=" + surfaceApplication
				+ ", imsDesignLook=" + designLook 
				+ ", imsDesignStyle=" + designStyle 
				+ ", imsSurfaceType=" + surfaceType
				+ ", imsSurfaceFinish=" + surfaceFinish 
				+ ", createdDate=" + createdDate 
				+ ", launchedDate=" + launchedDate
				+ ", lastModifiedDate=" + lastModifiedDate 
				+ "]";
	}
	
}
