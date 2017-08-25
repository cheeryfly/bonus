package com.bonus.dao;

import java.util.List;

import com.bonus.bean.Director;

public interface DirectorDao {
	public List<Director> queryDirectors(Director d);
	public void updateDirector(Director d);
	

}
