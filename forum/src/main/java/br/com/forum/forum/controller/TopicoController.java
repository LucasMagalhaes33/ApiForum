package br.com.forum.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.forum.forum.Dto.TopicoDto;
import br.com.forum.forum.modelo.Curso;
import br.com.forum.forum.modelo.Topico;

@RestController //Rest controller assume o papel do responsebody e do controller
public class TopicoController {
  
  @RequestMapping("/topicos")
  public List<TopicoDto> lista() {
    Topico topico = new Topico("Dúvida", "Dúvida com Spring", new Curso("Spring", "Programação"));

    return TopicoDto.converter(Arrays.asList(topico, topico, topico));
  }
  //@ResponseBody indica que nao vamos acessar uma página
  //@RequestMapping("/topicos") => indica que quando for acessada a url /topicos o método que será retornado é esse
  //List<Topico> => mostra uma lista com os topicos do mmodelo
  /*{
    cria um novo Tópico usando os parametros definidos no modelo
    Arrays.asList(topico, topico, topico) => transforma os objetos numa lista
  }*/
}
