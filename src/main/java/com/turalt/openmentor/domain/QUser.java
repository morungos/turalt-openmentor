package com.turalt.openmentor.domain;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import java.sql.Types;

import com.mysema.query.sql.ColumnMetadata;
import com.mysema.query.sql.RelationalPathBase;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;
import com.turalt.openmentor.dto.User;

public class QUser extends RelationalPathBase<User> {

	private static final long serialVersionUID = -3460999830135820931L;

	public static final QUser user = new QUser("user");

	public final NumberPath<Integer> id = createNumber("id", Integer.class);

	public final StringPath username = createString("username");
	
	public final BooleanPath administrator = createBoolean("administrator");

	public final StringPath email = createString("email");

	public QUser(String variable) {
        super(User.class, forVariable(variable), "null", "USER");
        addMetadata();
    }

	public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(username, ColumnMetadata.named("USERNAME").withIndex(2).ofType(Types.VARCHAR).withSize(64));
        addMetadata(administrator, ColumnMetadata.named("ADMINISTRATOR").withIndex(3).ofType(Types.BIT));
        addMetadata(email, ColumnMetadata.named("EMAIL").withIndex(4).ofType(Types.VARCHAR).withSize(255));
	}
}
