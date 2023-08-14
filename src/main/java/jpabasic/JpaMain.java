package jpabasic;

import javassist.bytecode.Descriptor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Iterator;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            //비영속 상태(아무것도 아닌 상태)
            Member member = new Member();
            member.setId(10L);
            member.setName("helloJPA");

            //영속 상태 (DB 저장 x)
            em.persist(member);

//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println(member.getName() + member.getId());
//            }

            //트랙잭션 commit -> DB저장
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
