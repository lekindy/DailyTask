package com.kindy.dao;

import java.util.List;

import com.kindy.domain.QueryCondition;
import com.kindy.domain.Task;

public interface DailyTaskDao {
	public List<Task> getTodayTaskList();
	public boolean addNewDailyTask(Task task);
	public boolean updateTodayDailyTask(Task task);
	public List<Task> getTaskListByCondition(QueryCondition condition);

}
