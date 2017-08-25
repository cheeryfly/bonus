package com.bonus.service;

import java.util.List;

import com.bonus.bean.Director;

public interface DirectorService {
	public List<Director> queryDirectors(Director d);
	public void updateDirector(Director d);
}
