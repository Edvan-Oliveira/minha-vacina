import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({
  fundo: {
    width: "100%",
    height: "100%",
    backgroundColor: "white",
  },
  containerLogoApp: {
    alignItems: "center",
    padding: 60,
  },
  logoApp: {
    width: 230,
    height: 176,
  },
  //formulário
  conteinerFormCadastro: {},
  conteinerFormLogin: {
    marginTop: 40,
  },
  input: {
    backgroundColor: "black",
    color: "white",
    fontSize: 17,
    borderRadius: 30,
    padding: 8,
    borderBottomColor: "#00000000",
    width: "100%",
  },
  //picker
  containerPicker: {
    backgroundColor: "black",
    borderRadius: 30,
    padding: 9,
    width: "95%",
    marginLeft: 10,
  },
  btnPicker: {
    color: "white",
    fontSize: 17,
    backgroundColor: "black",
  },
  containerBtnCalendario: {
    padding: 12,
    marginTop: 13,
    marginBottom: 13,
  },
  btnCalendario: {
    padding: 18,
    borderRadius: 30,
    backgroundColor: "black",
    color: "blue",
    justifyContent: "flex-start",
  },
  //botão login e cadastrar
  btnLogin: {
    padding: 18,
    borderRadius: 18,
    width: "92%",
    marginLeft: 17,
    backgroundColor: "#66b3ff",
    marginTop: 25,
  },
  btnCadastrar: {
    padding: 9,
    borderRadius: 18,
    width: "92%",
    marginLeft: 17,
    backgroundColor: "#66b3ff",
    marginTop: 30,
  },
  textContaLoginCadastrar: {
    textAlign: "center",
    color: "black",
    textDecorationLine: "underline",
    fontSize: 15,
    padding: 30,
  },
  erro: {
    color: "black",
    fontSize: 15,
    textAlign: "right",
    margin: 10,
    marginRight: 30,
    marginTop: -28,
  },
  carregando: {
    padding: 10,
    justifyContent: "center",
  },
  containerCheckBoxSenha: {
    backgroundColor: "rgba(0, 0, 0, 0)",
    borderColor: "rgba(0, 0, 0, 0)",
  },
  containerCheckBoxsTermos: {
    backgroundColor: "rgba(0, 0, 0, 0)",
    borderColor: "rgba(0, 0, 0, 0)",
    height: 43,
  },
  textEsqueceuSenha: {
    fontSize: 15,
    color: "black",
    textDecorationLine: "underline",
    textAlign: "center",
    marginTop: 20,
  },
  //termos
  containerTextTermo: {
    marginLeft: 10,
    padding: 5,
    marginTop: 10,
  },
  textTermoUso: {
    fontSize: 15,
    color: "black",
    textDecorationLine: "underline",
  },
  //modal
  containerModal: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  estiloModal: {
    backgroundColor: "white",
    padding: 15,
    color: "black",
    width: 320,
    height: 550,
    borderRadius: 8,
  },
  estiloModalSenha: {
    backgroundColor: "white",
    padding: 15,
    color: "black",
    width: 320,
    height: 300,
    borderRadius: 8,
  },
  tituloModal: {
    textAlign: "center",
    fontWeight: "bold",
    fontSize: 17,
  },
  btnModalCancelar: {
    padding: 11,
    marginTop: 15,
    width: 100,
    backgroundColor: "#CA0A0A",
    color: "white",
  },
  //chebox
  containerCheckbox: {
    width: 365,
    marginLeft: 5,
  },
  containerCheckbox2: {
    width: 365,
    marginLeft: 5,
  },
});
