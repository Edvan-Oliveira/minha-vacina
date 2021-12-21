import { Municipio } from "./municipio";

export interface Local {
  id: number;
  descricao: string;
  cep: number;
  bairro: string;
  rua: string;
  numero: number;
  municipio: Municipio;
}
