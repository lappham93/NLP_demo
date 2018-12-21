package com.mit.common.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.mit.common.entities.TimeDoc;
import com.mit.http.exception.SequenceException;
import com.mit.seq.repositories.SeqIdRepo;

@Repository
public class TimeDocRepo<T extends TimeDoc<Long>> {
	@Autowired
	protected MongoOperations mongoOps;

	@Autowired
	protected SeqIdRepo seqIdRepo;

	public void insert(T user) {
		user.setNewed(true);
		mongoOps.insert(user);
	}

	public void save(T user) throws SequenceException {
		long id = (long) user.getId();
		if (id <= 0) {
			user.setNewed(true);
			user.setId(seqIdRepo.getNextSequenceId(mongoOps.getCollectionName(user.getClass())));
		}

		mongoOps.save(user);
	}

	public void insertBatch(List<T> objs) throws SequenceException {
		for (T obj : objs) {
			if (obj.getId() <= 0) {
				obj.setId(seqIdRepo.getNextSequenceId(mongoOps.getCollectionName(obj.getClass())));
			}
		}

		mongoOps.insertAll(objs);
	}

}
