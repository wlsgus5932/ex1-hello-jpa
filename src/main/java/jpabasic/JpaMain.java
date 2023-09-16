package jpabasic;

import javassist.bytecode.Descriptor;
import net.bytebuddy.dynamic.DynamicType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Movie movie = new Movie();
            movie.setDirector("aaa");
            movie.setActor("bbb");
            movie.setName("바람");
            movie.setPrice(10000);
            em.persist(movie);

            Member member = new Member();
            member.setUsername("userA");
            em.persist(member);

            List<Member> resultList = em.createQuery("select m from Member m", Member.class).getResultList();
            for (Member member1 : resultList) {
                System.out.println(member1);
            }

            em.flush();
            em.clear();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
