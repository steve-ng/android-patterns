package com.example.pattern.strategyCache.strategy;

import com.example.pattern.strategyCache.Comment;

public class StrategyContext {
	 
	private CacheStrategy strategy;

	public StrategyContext(CacheStrategy strategy){
	      this.strategy = strategy;
	}
	
	public void storeComment(Comment comment){
		strategy.storeComment(comment);
	}
	
	public Comment getComment(long commentId){
		return strategy.getComment(commentId);
	}
}
