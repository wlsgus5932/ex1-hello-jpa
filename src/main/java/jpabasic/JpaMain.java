package jpabasic;

import javassist.bytecode.Descriptor;
import net.bytebuddy.dynamic.DynamicType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setUsername("userA");
            Member member1 = new Member();
            member1.setUsername("userB");
            Team team = new Team();
            List<Member> resultList = em.createQuery("select m from Member m", Member.class).getResultList();
            team.setMembers(resultList);

            List<Team> selectTFromTeamT = em.createQuery("select t from Team t", Team.class).getResultList();
            for (Team team1 : selectTFromTeamT) {
                System.out.println(team1);
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
