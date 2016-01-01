package com.turalt.openmentor.domain;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import java.sql.Types;

import com.mysema.query.sql.ColumnMetadata;
import com.mysema.query.sql.RelationalPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;
import com.turalt.openmentor.dto.Person;

public class QPerson extends RelationalPathBase<Person> {

	private static final long serialVersionUID = -843266598245519868L;

	public static final QPerson person = new QPerson("person");

	public final NumberPath<Integer> id = createNumber("id", Integer.class);
	
	public final StringPath owner = createString("owner");

	public final NumberPath<Integer> role = createNumber("role", Integer.class);

	public final StringPath identifier = createString("identifier");

	public final StringPath givenName = createString("givenName");

	public final StringPath familyName = createString("familyName");

	public QPerson(String variable) {
        super(Person.class, forVariable(variable), "null", "PERSON");
        addMetadata();
    }

	public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(owner, ColumnMetadata.named("OWNER").withIndex(2).ofType(Types.VARCHAR).withSize(24));
        addMetadata(role, ColumnMetadata.named("ROLE").withIndex(3).ofType(Types.INTEGER).withSize(10));
        addMetadata(identifier, ColumnMetadata.named("IDENTIFIER").withIndex(4).ofType(Types.VARCHAR).withSize(24));
        addMetadata(givenName, ColumnMetadata.named("GIVEN_NAME").withIndex(5).ofType(Types.VARCHAR).withSize(24));
        addMetadata(familyName, ColumnMetadata.named("FAMILY_NAME").withIndex(6).ofType(Types.VARCHAR).withSize(24));
	}
}
