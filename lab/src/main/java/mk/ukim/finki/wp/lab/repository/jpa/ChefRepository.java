package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Chef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChefRepository extends JpaRepository<Chef, Long> {
}
