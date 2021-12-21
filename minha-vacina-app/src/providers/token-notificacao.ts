import AsyncStorage from "@react-native-async-storage/async-storage";
import { TokenNotificacao } from "../models/tokenNotificacao";
import { api } from "./api";

export const TokenNotificacaoProvider = {
  salvarToken: async (tokenNotificacao: TokenNotificacao) => {
    let token : string = await AsyncStorage.getItem("token")
    api.defaults.headers.common = { Authorization: `Bearer ${token}` };
    api.post<TokenNotificacao>("notificacao/token", tokenNotificacao)
  },
};
