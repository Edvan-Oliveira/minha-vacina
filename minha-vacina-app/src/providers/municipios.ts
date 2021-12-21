import { api } from "./api";

export const MunicipiosProviders = {
  //Listando municipios
  Listar: async () => {
    const { data } = await api.get("/municipios");
    return data;
  },
};
