package com.turalt.openmentor.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;

import com.mysema.query.sql.SQLQuery;
import com.turalt.openmentor.dto.Pager;
import com.turalt.openmentor.dto.User;
import com.turalt.openmentor.query.UserProjection;
import com.turalt.openmentor.repository.UserRepository;

import static com.turalt.openmentor.domain.QUser.user;

public class SimpleUserRepository implements UserRepository {

	private QueryDslJdbcTemplate template;
	
	private UserProjection projection = new UserProjection(user);

	@Required
    public void setTemplate(QueryDslJdbcTemplate template) {
        this.template = template;
    }

	@Override
	public List<User> getUsers(Pager pager) {
		SQLQuery sq = template.newSqlQuery().from(user).orderBy(user.username.asc());
		
		if (pager.hasOffset()) {
		    sq = sq.offset(pager.getOffset());
		}
		if (pager.hasLimit()) {
			sq = sq.limit(pager.getLimit());
		}
		
		return template.query(sq, projection);
	}

	@Override
	public Long getCourseCount() {
		SQLQuery sq = template.newSqlQuery().from(user);
		return template.count(sq);
	}

	@Override
	public User findUser(String username) {
		SQLQuery sq = template.newSqlQuery().from(user).where(user.username.eq(username));
		return template.queryForObject(sq, projection);
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

}
