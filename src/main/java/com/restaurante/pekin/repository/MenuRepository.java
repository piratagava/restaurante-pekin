package com.restaurante.pekin.repository;

import com.restaurante.pekin.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hernan
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
