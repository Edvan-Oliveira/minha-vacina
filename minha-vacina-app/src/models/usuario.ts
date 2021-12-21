import { Campanha } from "./campanha";
import { Municipio } from "./municipio";
import { Permissao } from "./permissao";
import { TokenNotificacao } from "./tokenNotificacao";
import { Vacina } from "./vacina";

export interface Usuario {
  id?: number;
  permissao?: Permissao[];
  nome: string;
  municipio: Municipio;
  dataNascimento: Date;
  email: string;
  senha: string;
  vacinas?: Vacina[];
  campanhas?: Campanha[];
}
