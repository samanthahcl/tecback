package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.FuncionarioDTO;
import br.uniesp.si.techback.mapper.FuncionarioMapper;
import br.uniesp.si.techback.model.Funcionario;
import br.uniesp.si.techback.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    public FuncionarioDTO incluir(FuncionarioDTO dto) {
        Funcionario funcionario = funcionarioMapper.toEntity(dto);
        Funcionario salvo = funcionarioRepository.save(funcionario);
        return funcionarioMapper.toDTO(salvo);
    }

    public List<FuncionarioDTO> listar() {
        return funcionarioRepository.findAll()
                .stream()
                .map(funcionarioMapper::toDTO)
                .toList();
    }

    public FuncionarioDTO buscarPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Funcionário não encontrado com id: " + id
                ));

        return funcionarioMapper.toDTO(funcionario);
    }

    public FuncionarioDTO atualizar(Long id, FuncionarioDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Funcionário não encontrado com id: " + id
                ));

        funcionario.setNome(dto.getNome());
        funcionario.setCargo(dto.getCargo());

        Funcionario atualizado = funcionarioRepository.save(funcionario);
        return funcionarioMapper.toDTO(atualizado);
    }

    public void deletar(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Funcionário não encontrado com id: " + id
                ));

        funcionarioRepository.delete(funcionario);
    }
}