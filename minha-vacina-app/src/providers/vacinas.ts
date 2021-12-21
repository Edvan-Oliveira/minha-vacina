import { boolean } from "yup/lib/locale";
import { Vacina } from "../models/vacina";
import { usuarioAutenticado } from "./api";

export const VacinasProviders = {
  //Listando vacinas
  Listar: async () => {
    const api = await usuarioAutenticado();
    const { data } = await api.get("/vacinas");
    return data;
  },

  AssociarUsuario: async (idVacina : number): Promise<boolean> => {
    const api = await usuarioAutenticado();
    const { status } = await api.put("/vacinas/associar-usuario/" + idVacina)
    return status == 204 ? true : false;
  },

  DesassociarUsuario: async (idVacina : number): Promise<boolean> => {
    const api = await usuarioAutenticado();
    const { status } = await api.put("/vacinas/desassociar-usuario + " + idVacina)
    return status == 204 ? true : false;
  }

};
