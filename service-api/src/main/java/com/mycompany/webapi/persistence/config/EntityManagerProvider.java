package com.mycompany.webapi.persistence.config;

import javax.persistence.EntityManager;

public interface EntityManagerProvider {
	/**
     * Prioridade do produtor no contexto do CDI para testes.
     */
    int TEST_PRIORITY = 100;

    /**
     * Método que realiza a criação/produção de um gerenciador de entidades, baseado no ponto de injeção.
     *
     * @return Gerenciador de entidades criado.
     */
    EntityManager produce();

    /**
     * Método que realiza o descarte do gerenciado de entidades.
     *
     * @param manager Gerenciador de entidades a ser descartado.
     */
    void dispose(EntityManager manager);
}
