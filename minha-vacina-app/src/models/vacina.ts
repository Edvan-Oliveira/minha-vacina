import { Campanha } from "./campanha";
import { Usuario } from "./usuario";

export interface Vacina {
  id: number;
  nome: string;
  descricao: string;
  imagem: string;
  campanhas?: Campanha[];
  usuarios?: Usuario[];
}
