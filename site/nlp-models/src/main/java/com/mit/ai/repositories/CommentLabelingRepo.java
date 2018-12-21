package com.mit.ai.repositories;

import org.springframework.stereotype.Repository;

import com.mit.ai.entities.CommentLabeling;
import com.mit.common.repositories.TimeDocRepo;

@Repository
public class CommentLabelingRepo extends TimeDocRepo<CommentLabeling>{

}
