package com.mktech.mktechlog.api.controller;

import com.mktech.mktechlog.api.assembler.OcorrenciaAssembler;
import com.mktech.mktechlog.api.model.OcorrenciaModel;
import com.mktech.mktechlog.api.model.input.OcorrenciaInput;
import com.mktech.mktechlog.domain.model.Entrega;
import com.mktech.mktechlog.domain.model.Ocorrencia;
import com.mktech.mktechlog.domain.service.BuscaEntregaService;
import com.mktech.mktechlog.domain.service.RegistroOcorrenciasService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private BuscaEntregaService buscaEntregaService;
    private RegistroOcorrenciasService registroOcorrenciasService;
    private OcorrenciaAssembler ocorrenciaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrar(@PathVariable Long entregaId,
             @Valid @RequestBody OcorrenciaInput ocorrenciaInput){

        Ocorrencia ocorrenciaRegistrada = registroOcorrenciasService.registrar(entregaId, ocorrenciaInput.getDescricao());

        return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
    }

    @GetMapping
    public List<OcorrenciaModel> listar(@PathVariable Long entregaId){
        Entrega entrega = buscaEntregaService.buscar(entregaId);
        return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
    }
}
