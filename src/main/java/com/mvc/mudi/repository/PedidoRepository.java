package com.mvc.mudi.repository;

import com.mvc.mudi.model.Pedido;
import com.mvc.mudi.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
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

    List<Pedido> findByStatus(StatusPedido status);

}
