package br.com.minhavacina;

import br.com.minhavacina.clientrest.ClienteRest;
import br.com.minhavacina.clientrest.TipoAutenticacao;
import br.com.minhavacina.domain.Municipio;
import br.com.minhavacina.shared.Constantes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Executavel {
    public static void main(String[] args) {
        ClienteRest clienteRest = new ClienteRest();
        ResponseEntity<Municipio> resposta = clienteRest.chamarMetodoGet2(Constantes.MUNICIPIO + "/5", Municipio.class);


        System.out.println(resposta.getBody().getNome());

        if (resposta.getStatusCode().equals(HttpStatus.OK))
            System.out.println("Funcionou: " + resposta.getStatusCodeValue());
        else
            System.out.println("Não funcionou: " + resposta.getStatusCodeValue());

    }
}
