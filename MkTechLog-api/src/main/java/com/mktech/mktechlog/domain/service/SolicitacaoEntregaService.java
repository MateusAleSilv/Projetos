package com.mktech.mktechlog.domain.service;

import com.mktech.mktechlog.domain.exception.NegocioException;
import com.mktech.mktechlog.domain.model.Cliente;
import com.mktech.mktechlog.domain.model.Entrega;
import com.mktech.mktechlog.domain.model.StatusEntrega;
import com.mktech.mktechlog.domain.repository.ClienteRepository;
import com.mktech.mktechlog.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

    private CatalogoClienteService catalogoClienteService;
    private EntregaRepository entregaRepository;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }
}
