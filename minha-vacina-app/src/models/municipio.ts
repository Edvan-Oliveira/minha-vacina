import { Campanha } from "./campanha";

export interface Municipio {
  id: number;
  nome: string;
  campanhas?: Campanha[];
}
