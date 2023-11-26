package kr.co.kshproject.webDemo.Domain.BeginOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeginOrderRepository extends JpaRepository<BeginOrder,Long> {
}
