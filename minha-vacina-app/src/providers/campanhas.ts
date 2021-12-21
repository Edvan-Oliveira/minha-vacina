import { Campanha } from "../models/campanha";
import { usuarioAutenticado } from "./api";

export const CampanhasProviders = {
  //Listando Campanhas
  Listar: async () => {
    const api = await usuarioAutenticado();
    const { data } = await api.get("/campanhas");
    return data;
  },

  BuscarPorId: async (id: number): Promise<Campanha> => {
    const api = await usuarioAutenticado();
    const { data } = await api.get<Campanha>("/campanhas/" + id)
    return data;
  },

  AssociarUsuario: async (idCampanha : number): Promise<boolean> => {
    const api = await usuarioAutenticado();
    const { status } = await api.put("/campanhas/associar-usuario/" + idCampanha)
    return status == 204 ? true : false;
  },

  DesassociarUsuario: async (idCampanha : number): Promise<boolean> => {
    const api = await usuarioAutenticado();
    const { status } = await api.put("/campanhas/desassociar-usuario/" + idCampanha)
    return status == 204 ? true : false;
  }
};
