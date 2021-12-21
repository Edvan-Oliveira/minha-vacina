import { StyleSheet } from "react-native";

export const stylesItemVacina = StyleSheet.create({
  containerPrincipal: {
    backgroundColor: "rgba(192,192,192, 0.5)",
    borderRadius: 5,
    margin: 5,
    padding: 8,
    width: "95%",
    height: 80,
    left: 4.5,
  },
  containerNome: {},

  containerDescricao: {
    width: "75%",
  },
  nomeVacina: {
    color: "black",
    fontWeight: "bold",
    fontSize: 15,
    left: 10,
  },
  /*Descrição em tag html*/
  p: {
    fontSize: 10,
    color: "black",
    textAlign: "justify",
  },
  imagemVacina: {
    width: 65,
    height: 65,
    borderRadius: 50,
  },
  containerImagem: {
    alignItems: "flex-end",
    marginTop: -59,
    right: 10,
  },
});
