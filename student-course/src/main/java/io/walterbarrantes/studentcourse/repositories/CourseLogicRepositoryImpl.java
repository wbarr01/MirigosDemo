package io.walterbarrantes.studentcourse.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import io.walterbarrantes.studentcourse.course.Course;

@Repository
public class CourseLogicRepositoryImpl implements CourseLogicRepository{

	@PersistenceContext(name = "JPA_DEMO", type = PersistenceContextType.TRANSACTION)
	EntityManager em;
	
	@Override
	public List<Course> findCourseByFields(String code, String title, String description) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Course> cq = cb.createQuery(Course.class);

	    Root<Course> course = cq.from(Course.class);
	    List<Predicate> predicates = new ArrayList<>();
	    
	    if (StringUtils.hasLength(code)) {
	        predicates.add(cb.equal(cb.lower(course.get("code")), code.toLowerCase()));
	    }
	    if (StringUtils.hasLength(title)) {
	        predicates.add(cb.equal(cb.lower(course.get("title")), title.toLowerCase()));
	    }
	    if (StringUtils.hasLength(description)) {
	        predicates.add(cb.like(cb.lower(course.get("description")),"%"+ description.toLowerCase()+"%"));
	    }
	    cq.where(predicates.toArray(new Predicate[0]));
	    List<Course> resultList = em.createQuery(cq).getResultList();

	    return resultList;
	}

}
