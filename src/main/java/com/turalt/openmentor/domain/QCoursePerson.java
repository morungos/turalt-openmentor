package com.turalt.openmentor.domain;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import java.sql.Types;

import com.mysema.query.sql.ColumnMetadata;
import com.mysema.query.sql.RelationalPathBase;
import com.mysema.query.types.path.NumberPath;
import com.turalt.openmentor.dto.CoursePerson;

public class QCoursePerson extends RelationalPathBase<CoursePerson> {

	private static final long serialVersionUID = -4932081752296563304L;

	public final NumberPath<Integer> id = createNumber("id", Integer.class);

	public final NumberPath<Integer> courseId = createNumber("courseId", Integer.class);

	public final NumberPath<Integer> personId = createNumber("personId", Integer.class);

	public QCoursePerson(String variable) {
        super(CoursePerson.class, forVariable(variable), "null", "COURSE_PERSON");
        addMetadata();
    }

	public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(courseId, ColumnMetadata.named("COURSE_ID").withIndex(2).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(personId, ColumnMetadata.named("PERSON_ID").withIndex(3).ofType(Types.INTEGER).withSize(10).notNull());
	}
}
