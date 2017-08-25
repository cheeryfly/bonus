package com.bonus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonus.bean.Director;
import com.bonus.dao.DirectorDao;
import com.bonus.service.DirectorService;
@Service
public class DirectorServiceImpl implements DirectorService {

	@Autowired
	private DirectorDao directordao;

	@Transactional
	public List<Director> queryDirectors(Director d) {
		return directordao.queryDirectors(d);
	}

	@Transactional
	public void updateDirector(Director d) {
		directordao.updateDirector(d);
	}

}
