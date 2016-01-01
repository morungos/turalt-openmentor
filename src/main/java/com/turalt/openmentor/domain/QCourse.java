package com.turalt.openmentor.domain;

import java.sql.Types;

import com.mysema.query.sql.ColumnMetadata;
import com.mysema.query.sql.RelationalPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;
import com.turalt.openmentor.dto.Course;

import static com.mysema.query.types.PathMetadataFactory.*;

public class QCourse extends RelationalPathBase<Course> {

	private static final long serialVersionUID = -8831890174166154360L;
	
	public static final QCourse course = new QCourse("course");

	public final NumberPath<Integer> id = createNumber("id", Integer.class);
	
	public final NumberPath<Integer> ownerId = createNumber("id", Integer.class);

    public final StringPath identifier = createString("identifier");

    public final StringPath title = createString("title");

	public QCourse(String variable) {
        super(Course.class, forVariable(variable), "null", "COURSE");
        addMetadata();
    }
	
	public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(ownerId, ColumnMetadata.named("OWNER_ID").withIndex(2).ofType(Types.INTEGER).withSize(10));
        addMetadata(identifier, ColumnMetadata.named("IDENTIFIER").withIndex(3).ofType(Types.VARCHAR).withSize(24).notNull());
        addMetadata(title, ColumnMetadata.named("TITLE").withIndex(4).ofType(Types.VARCHAR).withSize(24).notNull());
	}
}
