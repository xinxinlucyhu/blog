package com.techtalentsouth.techtalentblog.blogpost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* the repository represents the database table itself*/
/*inherit form repository interface...crud repository is an interface that inherits form repository*/
@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
	

}
