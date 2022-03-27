package br.com.forum.forum.Dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import br.com.forum.forum.modelo.Resposta;
import br.com.forum.forum.modelo.StatusTopico;
import br.com.forum.forum.modelo.Topico;

public class DetalhesDoTopicoDto {

  private Long id;
  private String titulo;
  private String mensagem;
  private LocalDateTime dataCriacao;
  private String nomeAutor;
  private StatusTopico status;
  private List<RespostaDto> respostas;

  public DetalhesDoTopicoDto(Topico topico) {
    this.id = topico.getId();
    this.titulo = topico.getTitulo();
    this.mensagem = topico.getMensagem();
    this.dataCriacao = topico.getDataCriacao();
    this.nomeAutor = topico.getAutor().getNome();
    this.status = topico.getStatus();
    this.respostas = new ArrayList<>();
    this.respostas.addAll(topico.getRespostas().stream().map(RespostaDto::new ).collect(Collectors.toList()));
  } 


  public Long getId() {
    return this.id;
  }

  public String getTitulo() {
    return this.titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getMensagem() {
    return this.mensagem;
  }

  public LocalDateTime getDataCriacao() {
    return this.dataCriacao;
  }

  public String getNomeAutor() {
    return this.nomeAutor;
  }

  public StatusTopico getStatus() {
    return this.status;
  }

  public List<RespostaDto> getRespostas() {
    return this.respostas;
  }


}
