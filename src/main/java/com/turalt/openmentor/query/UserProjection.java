package com.turalt.openmentor.query;

import com.mysema.query.Tuple;
import com.mysema.query.types.MappingProjection;
import com.turalt.openmentor.domain.QUser;
import com.turalt.openmentor.dto.User;

import static com.turalt.openmentor.domain.QUser.user;

final public class UserProjection extends MappingProjection<User> {

	private static final long serialVersionUID = -7545268772931848373L;
	
	public UserProjection(QUser user) {
        super(User.class, 
        	user.id, user.username, user.administrator, user.email);
    }


	@Override
	protected User map(Tuple tuple) {
		// TODO Auto-generated method stub

    	User product = new User(tuple.get(user.username));

        product.setId(tuple.get(user.id));
        product.setAdministrator(tuple.get(user.administrator));
        product.setEmail(tuple.get(user.email));
        
        return product;
	}
}
