package com.mktech.mktechlog.domain.service;

import com.mktech.mktechlog.domain.exception.NegocioException;
import com.mktech.mktechlog.domain.model.Entrega;
import com.mktech.mktechlog.domain.model.Ocorrencia;
import com.mktech.mktechlog.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroOcorrenciasService {

    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao){
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return entrega.adicionarOcorrencia(descricao);
    }

}
