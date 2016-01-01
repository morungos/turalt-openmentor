package com.turalt.openmentor.domain;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import java.sql.Types;

import com.mysema.query.sql.ColumnMetadata;
import com.mysema.query.sql.RelationalPathBase;
import com.mysema.query.types.path.NumberPath;
import com.turalt.openmentor.dto.Assignment;

public class QAssignment extends RelationalPathBase<Assignment> {

	private static final long serialVersionUID = 7885662487635508326L;

	public final NumberPath<Integer> id = createNumber("id", Integer.class);

	public QAssignment(String variable) {
        super(Assignment.class, forVariable(variable), "null", "ASSIGNMENT");
        addMetadata();
    }
	
	public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
	}
}
