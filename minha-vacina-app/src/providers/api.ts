import AsyncStorage from "@react-native-async-storage/async-storage";
import Axios from "axios";

//Api minha vacina
export const api = Axios.create({
  baseURL: "http://192.168.0.124:8080/minha-vacina-api",
});

export const usuarioAutenticado = async () => {
  const token = await AsyncStorage.getItem("token");
  api.defaults.headers.common = { Authorization: `Bearer ${token}` };
  return api;
};
