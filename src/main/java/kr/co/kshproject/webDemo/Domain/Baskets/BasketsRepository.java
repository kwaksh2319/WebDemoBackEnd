package kr.co.kshproject.webDemo.Domain.Baskets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketsRepository extends JpaRepository<Baskets,Long> {
}
