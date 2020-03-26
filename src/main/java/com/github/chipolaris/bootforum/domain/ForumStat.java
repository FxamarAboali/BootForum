package com.github.chipolaris.bootforum.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="FORUM_STAT_T")
@TableGenerator(name="ForumStatIdGenerator", table="ENTITY_ID_T", pkColumnName="GEN_KEY", 
	pkColumnValue="FORUM_STAT_ID", valueColumnName="GEN_VALUE", initialValue = 1000, allocationSize=10)
public class ForumStat extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="ForumStatIdGenerator")
	private Long id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="LAST_COMMENT_ID")
	private CommentInfo lastComment; // info about last comment, used for display
	
	@Column(name="COMMENT_COUNT")
	private long commentCount;
	
	@Column(name="VIEW_COUNT")
	private long viewCount;
	
	@Column(name="DISCUSSION_COUNT")
	private long discussionCount;

	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public CommentInfo getLastComment() {
		return lastComment;
	}
	public void setLastComment(CommentInfo lastComment) {
		this.lastComment = lastComment;
	}
	
	public long getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
	}
		
	public long getViewCount() {
		return viewCount;
	}
	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}
	
	public long getDiscussionCount() {
		return discussionCount;
	}
	public void setDiscussionCount(long discussionCount) {
		this.discussionCount = discussionCount;
	}
}
