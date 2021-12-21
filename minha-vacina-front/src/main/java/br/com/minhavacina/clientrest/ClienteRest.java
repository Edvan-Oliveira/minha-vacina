package br.com.minhavacina.clientrest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static br.com.minhavacina.clientrest.TipoAutenticacao.BEARER_TOKEN;

public class ClienteRest {

    private TipoAutenticacao tipoAutenticacao;
    private String token;
    private String usuario;
    private String senha;
    private Map<String, String> headersEspecificos;

    public ClienteRest() {

        this.tipoAutenticacao = BEARER_TOKEN;
    }

    public <T> ResponseEntity<T> chamarMetodoGet(String url, Class<T> classType) {
        try {
            HttpEntity<?> body = new HttpEntity<>(obterHeaders());
            return getClient().exchange(url, HttpMethod.GET, body, classType);
        } catch (Exception e) {
            return new ResponseEntity<T>(HttpStatus.BAD_REQUEST);
        }
    }

    public <T> ResponseEntity<T> chamarMetodoGet2(String url, Class<T> classType) {
        try {
            HttpEntity<?> body = new HttpEntity<>(obterHeaders());
            return getClient().exchange(url, HttpMethod.GET, body, classType);
        } catch (Exception e) {
            return new ResponseEntity<T>(HttpStatus.BAD_REQUEST);
        }
    }

    public <T> ResponseEntity<List<T>> chamarMetodoGetListagem(String url) {
        try {
            HttpEntity<?> body = new HttpEntity<>(obterHeaders());
            return getClient().exchange(url, HttpMethod.GET, body, new ParameterizedTypeReference<>() {
            });
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public <T> ResponseEntity<T> chamarMetodoGet(String url, Object parametros, Class<T> classType) {
        try {
            HttpEntity<?> body = new HttpEntity<>(parametros, obterHeaders());
            return getClient().exchange(url, HttpMethod.GET, body, classType);
        } catch (Exception e) {
            return new ResponseEntity<T>(HttpStatus.BAD_REQUEST);
        }
    }

    public <T> ResponseEntity<List<T>> chamarMetodoGetListagem(String url, Object parametros) {
        try {
            HttpEntity<?> body = new HttpEntity<>(parametros, obterHeaders());
            return getClient().exchange(url, HttpMethod.GET, body, new ParameterizedTypeReference<>() {
            });
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public <T> ResponseEntity<T> chamarMetodoPost(String url, Object parametros, Class<T> classType) {
        try {
            HttpEntity<?> body = new HttpEntity<>(parametros, obterHeaders());
            return getClient().exchange(url, HttpMethod.POST, body, classType);
        } catch (Exception e) {
            return new ResponseEntity<T>(HttpStatus.BAD_REQUEST);
        }
    }

    public <T> ResponseEntity<List<T>> chamarMetodoPostListagem(String url, Object parametros) {
        try {
            HttpEntity<?> body = new HttpEntity<>(parametros, obterHeaders());
            return getClient().exchange(url, HttpMethod.POST, body, new ParameterizedTypeReference<>() {
            });
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public <T> ResponseEntity chamarMetodoPut(String url, Object parametros) {
        try {
            HttpEntity<?> body = new HttpEntity<>(parametros, obterHeaders());
            return getClient().exchange(url, HttpMethod.PUT, body, Void.class);
        } catch (Exception e) {
            return new ResponseEntity<T>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity chamarMetodoDelete(String url) {
        try {
            HttpEntity<?> request = new HttpEntity<>(obterHeaders());
            return getClient().exchange(url, HttpMethod.DELETE, request, Void.class);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private HttpHeaders obterHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        setarHeaderAutenticacaoCasoNecessario(headers);
        setarHeadersEspecificosCasoNecessario(headers);
        return headers;
    }

    private void setarHeaderAutenticacaoCasoNecessario(HttpHeaders headers) {
        if (BEARER_TOKEN.equals(tipoAutenticacao)) {
            setarBearerTokenCasoNecessario(headers);
        } else if (TipoAutenticacao.BASIC_AUTH.equals(tipoAutenticacao)) {
            setarBasicAuthCasoNecessario(headers);
        }
    }

    private void setarHeadersEspecificosCasoNecessario(HttpHeaders headers) {
        if (VerificadorUtil.naoEstaNuloOuVazio(headersEspecificos)) {
            for (Map.Entry<String, String> entry : headersEspecificos.entrySet()) {
                headers.set(entry.getKey(), entry.getValue());
            }
        }
    }

    private void setarBearerTokenCasoNecessario(HttpHeaders headers) {
        if (VerificadorUtil.naoEstaNuloOuVazio(token)) {
            headers.setBearerAuth(token);
        }
    }

    private void setarBasicAuthCasoNecessario(HttpHeaders headers) {
        if (VerificadorUtil.naoEstaNuloOuVazio(usuario) && VerificadorUtil.naoEstaNuloOuVazio(senha)) {
            headers.setBasicAuth(usuario, senha);
        }
    }

    private RestTemplate getClient() {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        restTemplate.setMessageConverters(getHttpMessageConverters());
        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        return restTemplate;
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        return new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
    }

    private List<HttpMessageConverter<?>> getHttpMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(getJacksonMessageConverter());
        return messageConverters;
    }

    private MappingJackson2HttpMessageConverter getJacksonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        converter.setObjectMapper(getObjectMapper());
        return converter;
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    private static class VerificadorUtil {

        public VerificadorUtil() {
        }

        public static boolean estaNulo(Object objeto) {
            return objeto == null;
        }

        public static boolean estaVazio(Object objeto) {
            return (objeto instanceof Collection) ? ((Collection) objeto).isEmpty() : StringUtils.isEmpty(objeto.toString());
        }

        public static boolean naoEstaNulo(Object objeto) {
            return !estaNulo(objeto);
        }

        public static boolean naoEstaVazio(Object objeto) {
            return !estaVazio(objeto);
        }

        public static boolean estaNuloOuVazio(Object valor) {
            return estaNulo(valor) || estaVazio(valor);
        }

        public static boolean naoEstaNuloOuVazio(Object objeto) {
            return naoEstaNulo(objeto) && naoEstaVazio(objeto);
        }
    }

    public TipoAutenticacao getTipoAutenticacao() {
        return tipoAutenticacao;
    }

    public void setTipoAutenticacao(TipoAutenticacao tipoAutenticacao) {
        this.tipoAutenticacao = tipoAutenticacao;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Map<String, String> getHeadersEspecificos() {
        return headersEspecificos;
    }

    public void setHeadersEspecificos(Map<String, String> headersEspecificos) {
        this.headersEspecificos = headersEspecificos;
    }
}
