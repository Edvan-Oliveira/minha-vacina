import { Municipio } from "./municipio";
import { Vacina } from "./vacina";
import { Local } from "./local";
import { Usuario } from "./usuario";

export interface Campanha {
  id: number;
  nome: string;
  descricao: string;
  vacina: Vacina;
  municipio: Municipio;
  horarioInicioDia: string;
  horarioFimDia: string;
  locais: Local[];
  ativa: boolean;
  dataInicio: Date;
  dataFim: Date;
  idadeMinima: number;
  idadeMaxima: number;
  usuarios?: Usuario[];
}
