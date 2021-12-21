import { StyleSheet } from "react-native";

export const stylesDetalheCampanha = StyleSheet.create({
  containerPrincipal: {
    width: "100%",
    height: "100%",
    backgroundColor: "white",
  },
  header: {
    backgroundColor: "#003366",
    flexDirection: "row",
    padding: 18,
  },
  textHeader: {
    fontSize: 20,
    fontWeight: "bold",
    color: "white",
    left: 30,
    marginTop: -2,
  },
  containerNome: {
    marginTop: 50,
  },
  textNome: {
    color: "black",
    fontSize: 40,
    textAlign: "center",
  },
  containerDescricao: {
    width: "95%",
    marginTop: 40,
    left: 9,
  },
  descricao: {
    color: "black",
    textAlign: "justify",
    fontSize: 20,
  },
  containerTextIcone: {
    flex: 1,
    flexDirection: "row",
    justifyContent: "space-around"

  },
  containerTextInfo: {
    display: "flex",
    flexDirection: "column",
    alignItems: "center"  
  },
  containerIcones: {
    flex: 1,
    flexDirection: "row",
    justifyContent: "space-around",
    marginTop: 20,
  },
  textInfo: {
    color: "black",
    fontWeight: "bold",
    fontSize: 15,
  },
  containermaisInfo: {
    backgroundColor: "rgba(192,192,192, 0.5)",
    width: "95%",
    left: 9,
    borderRadius: 5,
    marginTop: 10,
    marginBottom: 5,
  },
  textMaisInfo: {
    color: "black",
    fontWeight: "bold",
    padding: 8,
    fontSize: 15,
  },
  tituloMaisInfo: {
    color: "black",
    fontWeight: "bold",
    padding: 10,
    fontSize: 15,
    marginTop: 10,
  },
});
