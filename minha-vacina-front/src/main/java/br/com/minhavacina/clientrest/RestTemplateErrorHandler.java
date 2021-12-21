package br.com.minhavacina.clientrest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

public class RestTemplateErrorHandler implements ResponseErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteRest.class);

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return (clientHttpResponse.getStatusCode().series() == CLIENT_ERROR || clientHttpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
         mostrarErros(clientHttpResponse);
        throw new br.com.minhavacina.clientrest.ClienteRestException("Erro ao executar operação.");
    }

    private void mostrarErros(ClientHttpResponse clientHttpResponse) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (!obterJson(clientHttpResponse).startsWith("[")) {
            adicionarMensagemErroAoContexto(context, obterErro(clientHttpResponse));
        } else {
            for (br.com.minhavacina.clientrest.ApiErrorDTO erro : obterErros(clientHttpResponse)) {
                LOGGER.error(erro.getCampo() + ": " + erro.getMensagem());
                adicionarMensagemErroAoContexto(context, erro);
            }
        }
    }

    private br.com.minhavacina.clientrest.ApiErrorDTO obterErro(ClientHttpResponse clientHttpResponse) throws IOException {
        return new Gson().fromJson(obterJson(clientHttpResponse), br.com.minhavacina.clientrest.ApiErrorDTO.class);
    }

    private List<br.com.minhavacina.clientrest.ApiErrorDTO> obterErros(ClientHttpResponse clientHttpResponse) throws IOException {
        Type tipoLista = new TypeToken<ArrayList<br.com.minhavacina.clientrest.ApiErrorDTO>>(){}.getType();
        return new Gson().fromJson(obterJson(clientHttpResponse), tipoLista);
    }

    private String obterJson(ClientHttpResponse clientHttpResponse) throws IOException {
        return StreamUtils.copyToString(clientHttpResponse.getBody(), StandardCharsets.UTF_8);
    }

    private void adicionarMensagemErroAoContexto(FacesContext context, br.com.minhavacina.clientrest.ApiErrorDTO erro) {
        String mensagem = erro.getCampo().concat(" ").concat(erro.getMensagem());
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "Erro");
        context.addMessage(null, facesMessage);
    }
}
