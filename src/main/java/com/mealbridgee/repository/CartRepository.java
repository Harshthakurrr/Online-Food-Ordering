package com.mealbridgee.repository;

import com.mealbridgee.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long>
{

}