package br.com.minhavacina.domain.converter;

import br.com.minhavacina.domain.Local;
import br.com.minhavacina.service.CampanhaService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import static br.com.minhavacina.util.JSFUtil.objetoEstarNuloOuVazio;

@FacesConverter(value = "localConverter")
public class LocalConverter implements Converter {

    private final CampanhaService campanhaService = new CampanhaService();

    public Object getAsObject(FacesContext contexto, UIComponent componente, String texto) {
        if (objetoEstarNuloOuVazio(texto) || texto.equals("null")) return null;
        try {
            int id = Integer.parseInt(texto);
            return campanhaService.buscarLocalPorId(id).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao converter a classe 'Local'", null));
        }
    }

    public String getAsString(FacesContext contexto, UIComponent componente, Object objeto) {
        if (objetoEstarNuloOuVazio(objeto)) return null;
        String valor = objeto.toString();
        if (valor.startsWith("{id=")) return (valor.split(",")[0]).split("=")[1];
        return String.valueOf(((Local) objeto).getId());
    }
}

