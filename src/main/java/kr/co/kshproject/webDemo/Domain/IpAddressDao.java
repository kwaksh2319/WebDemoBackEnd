package kr.co.kshproject.webDemo.Domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

@Repository
@Transactional
public class IpAddressDao {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void save(String ipAddress) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        IpAddress ip=new IpAddress();
        try{
            ip.setIp(ipAddress);
            entityManager.getTransaction().begin();
            entityManager.persist(ip);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
    }
}
