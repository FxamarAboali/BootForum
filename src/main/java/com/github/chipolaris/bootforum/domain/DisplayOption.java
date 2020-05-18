package com.github.chipolaris.bootforum.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name="DISPLAY_OPTION_T")
public class DisplayOption extends BaseEntity {

	@Id
	private Long id;
	
	@Column(name = "SHOW_MOST_VIEWS_DIS")
	private boolean showMostViewsDiscussions;
	
	@Column(name = "NUM_MOST_VIEWS_DIS")
	private int numMostViewsDiscussions;
	
	@Column(name = "SHOW_MOST_COMMENTS_DIS")
	private boolean showMostCommentsDiscussions;
	
	@Column(name = "NUM_MOST_COMMENTS_DIS")
	private int numMostCommentsDiscussions;
	
	@Column(name = "SHOW_MOST_RECENT_DIS")
	private boolean showMostRecentDiscussions;
	
	@Column(name = "NUM_MOST_RECENT_DIS")
	private int numMostRecentDiscussions;
	
	@Column(name = "SHOW_DISC_FOR_TAG")
	private boolean showDiscussionsForTag;
	
	@Column(name = "NUM_DISC_PER_TAG")
	private int numDiscussionsPerTag;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="DISPLAY_OPT_DSP_TAG_T",
		joinColumns=@JoinColumn(name="DISPLAY_OPTION_ID"),
		inverseJoinColumns=@JoinColumn(name="TAG_ID"))
	@OrderColumn(name="ORDERED")
	private List<Tag> displayTags;
	
	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isShowMostViewsDiscussions() {
		return showMostViewsDiscussions;
	}
	public void setShowMostViewsDiscussions(boolean showMostViewsDiscussions) {
		this.showMostViewsDiscussions = showMostViewsDiscussions;
	}
	
	public int getNumMostViewsDiscussions() {
		return numMostViewsDiscussions;
	}
	public void setNumMostViewsDiscussions(int numMostViewsDiscussions) {
		this.numMostViewsDiscussions = numMostViewsDiscussions;
	}
	
	public boolean isShowMostCommentsDiscussions() {
		return showMostCommentsDiscussions;
	}
	public void setShowMostCommentsDiscussions(boolean showMostCommentsDiscussions) {
		this.showMostCommentsDiscussions = showMostCommentsDiscussions;
	}
	
	public int getNumMostCommentsDiscussions() {
		return numMostCommentsDiscussions;
	}
	public void setNumMostCommentsDiscussions(int numMostCommentsDiscussions) {
		this.numMostCommentsDiscussions = numMostCommentsDiscussions;
	}
	
	public boolean isShowMostRecentDiscussions() {
		return showMostRecentDiscussions;
	}
	public void setShowMostRecentDiscussions(boolean showMostRecentDiscussions) {
		this.showMostRecentDiscussions = showMostRecentDiscussions;
	}
	
	public int getNumMostRecentDiscussions() {
		return numMostRecentDiscussions;
	}
	public void setNumMostRecentDiscussions(int numMostRecentDiscussions) {
		this.numMostRecentDiscussions = numMostRecentDiscussions;
	}
	
	public boolean isShowDiscussionsForTag() {
		return showDiscussionsForTag;
	}
	public void setShowDiscussionsForTag(boolean showDiscussionsForTag) {
		this.showDiscussionsForTag = showDiscussionsForTag;
	}
	
	public int getNumDiscussionsPerTag() {
		return numDiscussionsPerTag;
	}
	public void setNumDiscussionsPerTag(int numDiscussionsPerTag) {
		this.numDiscussionsPerTag = numDiscussionsPerTag;
	}
	
	public List<Tag> getDisplayTags() {
		return displayTags;
	}
	public void setDisplayTags(List<Tag> displayTags) {
		this.displayTags = displayTags;
	}
}
