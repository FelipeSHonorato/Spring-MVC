package com.mvc.mudi.repository;

import com.mvc.mudi.model.Pedido;
import com.mvc.mudi.model.StatusPedido;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //Informa que a classe é um repositório
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    /** Método abaixo utilizando como exemplo uma implementação via JPA utilizando EntityManager
    @PersistenceContext
    private EntityManager entityManager;

    public List<Pedido> recuperaTodosPedidos() {
        Query query = entityManager.createQuery("SELECT p FROM Pedido p", Pedido.class);
        return query.getResultList();
    }
    **/

    //Conferir os controllers para verificar as injeções de dependencias
    @Cacheable ("produtos")
    List<Pedido> findByStatus(StatusPedido status, Pageable sort);

    @Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :username")
    List<Pedido> findAllByUsuario(@Param("username")String username);

    @Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :username AND p.status = :status")
    List<Pedido> findByStatusAndUsuario(@Param("status")StatusPedido status, @Param("username") String username);
}
