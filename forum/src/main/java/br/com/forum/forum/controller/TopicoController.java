package br.com.forum.forum.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.forum.forum.Dto.DetalhesDoTopicoDto;
import br.com.forum.forum.Dto.TopicoDto;
import br.com.forum.forum.controller.form.AtualizacaoTopicoForm;
import br.com.forum.forum.controller.form.TopicoForm;
import br.com.forum.forum.modelo.Curso;
import br.com.forum.forum.modelo.Topico;
import br.com.forum.forum.repository.CursoRepository;
import br.com.forum.forum.repository.TopicoRepository;
import org.springframework.web.bind.annotation.RequestParam;


@RestController //Rest controller assume o papel do responsebody e do controller
@RequestMapping("/topicos")
public class TopicoController {

  @Autowired
  private TopicoRepository topicoRepository;

  @Autowired
  private CursoRepository cursoRepository;
  
  @GetMapping
  public List<TopicoDto> lista(String nomeCurso) {

    if (nomeCurso == null) {
      List<Topico> topicos = topicoRepository.findAll();
      return TopicoDto.converter(topicos);
    } else {
      List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
      return TopicoDto.converter(topicos);
    }
  }

  @PostMapping
  @Transactional
  public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {

    Topico topico = form.converter(cursoRepository);
    topicoRepository.save(topico);

    URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
    return ResponseEntity.created(uri).body(new TopicoDto(topico));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id) {
      
      Optional<Topico> topico = topicoRepository.findById(id);
      if (topico.isPresent()) {
        return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
      } 
      
      return ResponseEntity.notFound().build();
  }
  
  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id , @RequestBody @Valid AtualizacaoTopicoForm form) {
    Optional<Topico> optional = topicoRepository.findById(id);
      if (optional.isPresent()) {
        Topico topico = form.atuallizar(id, topicoRepository);
        return ResponseEntity.ok(new TopicoDto(topico));
      } 
      
      return ResponseEntity.notFound().build();

  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<?> remover(@PathVariable Long id) {
    Optional<Topico> optional = topicoRepository.findById(id);
    if (optional.isPresent()) {
      topicoRepository.deleteById(id);
      return ResponseEntity.ok().build();
    }

    return ResponseEntity.notFound().build();
  }

}









  //@ResponseBody indica que nao vamos acessar uma p??gina
  //@RequestMapping("/topicos") => indica que quando for acessada a url /topicos o m??todo que ser?? retornado ?? esse
  //List<Topico> => mostra uma lista com os topicos do mmodelo
  /*{
    cria um novo T??pico usando os parametros definidos no modelo
    Arrays.asList(topico, topico, topico) => transforma os objetos numa lista
  }*/