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

import io.walterbarrantes.studentcourse.student.Student;

@Repository
public class StudentLogicRepositoryImpl implements StudentLogicRepository{
	@PersistenceContext(name = "JPA_DEMO", type = PersistenceContextType.TRANSACTION)
	EntityManager em;
	@Override
	public List<Student> findStudentByFields(String firstName, String lastName) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Student> cq = cb.createQuery(Student.class);

	    Root<Student> student = cq.from(Student.class);
	    List<Predicate> predicates = new ArrayList<>();
	    
	    if (StringUtils.hasLength(firstName)) {
	        predicates.add(cb.equal(cb.lower(student.get("firstName")), firstName.toLowerCase()));
	    }
	    if (StringUtils.hasLength(lastName)) {
	        predicates.add(cb.equal(cb.lower(student.get("lastName")), lastName.toLowerCase()));
	    }
	    cq.where(predicates.toArray(new Predicate[0]));
	    List<Student> resultList = em.createQuery(cq).getResultList();

	    return resultList;
	}

}
