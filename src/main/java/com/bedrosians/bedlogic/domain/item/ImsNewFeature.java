package com.bedrosians.bedlogic.domain.item;

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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.bedrosians.bedlogic.domain.item.enums.Body;
import com.bedrosians.bedlogic.domain.item.enums.DesignLook;
import com.bedrosians.bedlogic.domain.item.enums.DesignStyle;
import com.bedrosians.bedlogic.domain.item.enums.Edge;
import com.bedrosians.bedlogic.domain.item.enums.Grade;
import com.bedrosians.bedlogic.domain.item.enums.MpsCode;
import com.bedrosians.bedlogic.domain.item.enums.Status;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceApplication;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceFinish;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceType;
import com.bedrosians.bedlogic.util.FormatUtil;
import com.bedrosians.bedlogic.util.ImsResultUtil;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude
@Entity
@Table(name = "ims_new_feature", schema = "public")
public class ImsNewFeature implements java.io.Serializable {
   
	private static final long serialVersionUID = -11135822658657L;
	
	private String itemCode;
	private Grade grade;
	private Status status;
	private Body body;
	private Edge edge;
	private MpsCode mpsCode;
	private DesignLook designLook;
	private DesignStyle designStyle;
	private SurfaceApplication surfaceApplication;
	private SurfaceType surfaceType;
	private SurfaceFinish surfaceFinish;
	private Integer warranty;
	private String recommendedGroutJointMin;
	private String recommendedGroutJointMax;
	private Date createdDate;
	private Date launchedDate;
	private Date droppedDate;
	private Date lastModifiedDate;
	private Item item;
	
	public ImsNewFeature() {
	}
	
	public ImsNewFeature(String itemCode) {
		this.itemCode = itemCode;
	}

	@JsonIgnore
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "item"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "item_code", unique = true, nullable = false, length = 15)
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
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
	public MpsCode getMpsCode() {
		return (mpsCode != null? mpsCode : item == null? null :ImsResultUtil.convertInactivecdToMpsCode(item.getInactivecode()));
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
	public Grade getGrade() {
		return this.grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	@Column(name="status")
	@Enumerated(EnumType.STRING)
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
	@Column(name = "last_modified_date", updatable=false)
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
				edge == null && surfaceApplication == null && surfaceType == null && surfaceFinish == null);
		
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
		return "ImsNewFeature ["
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
