package br.com.minhavacina.dto;

import br.com.minhavacina.domain.Campanha;
import lombok.Data;

import java.time.LocalTime;

@Data
public class HorariosCampanhaDTO {
    private String horarioInicioDiaTexto;
    private String horarioFimDiaTexto;

    public HorariosCampanhaDTO(){
    }

    public HorariosCampanhaDTO(Campanha campanha) {
        this.popularHorarios(campanha);
    }

    public void popularHorarios(Campanha campanha) {
        this.horarioInicioDiaTexto = this.formatarHorario(this.horarioInicioDiaTexto);
        this.horarioFimDiaTexto = this.formatarHorario(this.horarioFimDiaTexto);
        campanha.setHorarioInicioDia(this.converterHorarioParaLocalTime(this.horarioInicioDiaTexto));
        campanha.setHorarioFimDia(this.converterHorarioParaLocalTime(this.horarioFimDiaTexto));
    }

    public boolean validarHorarios() {
        return this.validarHorario(this.horarioInicioDiaTexto) && this.validarHorario(this.horarioFimDiaTexto);
    }

    public void popularHorariosTextos(Campanha campanha) {
        this.horarioInicioDiaTexto = campanha.getHorarioInicioDia().toString();
        this.horarioFimDiaTexto = campanha.getHorarioFimDia().toString();
    }

    private boolean validarHorario(String horario) {
        try {
            String[] horaMinuto = horario.split(":");
            int hora = Integer.parseInt(horaMinuto[0]);
            int minuto = Integer.parseInt(horaMinuto[1]);
            if (hora < 0 || hora > 24) return false;
            if (minuto < 0 || minuto > 60) return false;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String formatarHorario(String horario) {
        String[] horaMinuto = horario.split(":");
        String hora = horaMinuto[0];
        String minuto = horaMinuto[1];
        hora = hora.length() < 2 ? "0" + hora : hora;
        minuto = minuto.length() < 2 ? minuto + "0" : minuto;
        return String.format("%s:%s", hora, minuto);
    }

    private LocalTime converterHorarioParaLocalTime(String horario) {
        return LocalTime.parse(horario);
    }
}
