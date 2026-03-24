package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.FuncionarioDTO;
import br.uniesp.si.techback.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @PostMapping
    public FuncionarioDTO incluir(@RequestBody @Valid FuncionarioDTO dto) {
        return funcionarioService.incluir(dto);
    }

    @GetMapping
    public List<FuncionarioDTO> listar() {
        return funcionarioService.listar();
    }

    @GetMapping("/{id}")
    public FuncionarioDTO buscarPorId(@PathVariable Long id) {
        return funcionarioService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public FuncionarioDTO atualizar(@PathVariable Long id, @RequestBody @Valid FuncionarioDTO dto) {
        return funcionarioService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        funcionarioService.deletar(id);
    }
}