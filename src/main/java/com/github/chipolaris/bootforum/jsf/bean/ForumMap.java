package com.github.chipolaris.bootforum.jsf.bean;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.github.chipolaris.bootforum.CachingConfig;
import com.github.chipolaris.bootforum.domain.Forum;
import com.github.chipolaris.bootforum.domain.ForumGroup;
import com.github.chipolaris.bootforum.domain.ForumStat;
import com.github.chipolaris.bootforum.service.GenericService;

// application scope JSF backing bean
@Component
public class ForumMap {
	
	private static final Logger logger = LoggerFactory.getLogger(ForumMap.class);

	@Resource
	private GenericService genericService;

	private TreeNode forumRootTreeNode;

	public TreeNode getForumRootTreeNode() {
		return forumRootTreeNode;
	}
	public void setForumRootTreeNode(TreeNode forumRootTreeNode) {
		this.forumRootTreeNode = forumRootTreeNode;
	}
	
	private DefaultTreeNode managementRootTreeNode;
	
	public DefaultTreeNode getManagementRootTreeNode() {
		return managementRootTreeNode;
	}
	public void setManagementRootTreeNode(DefaultTreeNode managementRootTreeNode) {
		this.managementRootTreeNode = managementRootTreeNode;
	}
	
	@PostConstruct
	public void init() {
		
		List<Forum> forums = genericService.getEntities(Forum.class, Collections.singletonMap("forumGroup", null), 
				"sortOrder", false).getDataObject();

		List<ForumGroup> forumGroups = genericService.getEntities(ForumGroup.class, Collections.singletonMap("parent", null), 
				"sortOrder", false).getDataObject();
		
		forumRootTreeNode = new DefaultTreeNode(null, null);
		forumRootTreeNode.setExpanded(true);
		
		TreeNode rootTreeNode = new DefaultTreeNode("Root", null, forumRootTreeNode);
		rootTreeNode.setExpanded(true);
		
		buildForumTreeNodes(forums, forumGroups, rootTreeNode);
		
		// 
		
		managementRootTreeNode = new DefaultTreeNode(null, null);
		managementRootTreeNode.setExpanded(true);
		
		TreeNode managementTreeNode = new DefaultTreeNode("Root", null, managementRootTreeNode);
		managementTreeNode.setExpanded(true);
		
		buildManagementTree(forums, forumGroups, managementTreeNode);
	}

	private void buildManagementTree(List<Forum> forums, List<ForumGroup> forumGroups, TreeNode parent) {
		
		for(Forum forum : forums) {
			
			TreeNode forumNode = new DefaultTreeNode("Forum", forum, parent);
			forumNode.setExpanded(true);
		}
		
		for(ForumGroup forumGroup : forumGroups) {
			
			TreeNode<ForumGroup> forumGroupNode = new DefaultTreeNode<ForumGroup>("ForumGroup", forumGroup, parent);
			forumGroupNode.setExpanded(true);
			buildManagementTree(forumGroup.getForums(), forumGroup.getSubGroups(), forumGroupNode);
		}
		
		new DefaultTreeNode("AddForum", parent.getData(), parent);
		new DefaultTreeNode("AddForumGroup", parent.getData(), parent);
	}
	
	private void buildForumTreeNodes(List<Forum> forums, List<ForumGroup> forumGroups, TreeNode parent) {
		
		for(Forum forum : forums) {
			
			TreeNode<Forum> forumNode = new DefaultTreeNode<Forum>("Forum", forum, parent);
			forumNode.setExpanded(true);
		}
		
		for(ForumGroup forumGroup : forumGroups) {
			
			TreeNode<ForumGroup> forumGroupNode = new DefaultTreeNode<ForumGroup>("ForumGroup", forumGroup, parent);
			forumGroupNode.setExpanded(true);
			buildForumTreeNodes(forumGroup.getForums(), forumGroup.getSubGroups(), forumGroupNode);
		}
	}
	
	@Cacheable(value=CachingConfig.FORUM_STAT, key="#forum.stat.id")
	public ForumStat getForumStat(Forum forum) {
		logger.info("getForumStat: " + forum.getStat().getId());
		return genericService.getEntity(ForumStat.class, forum.getStat().getId()).getDataObject();
	}
}
