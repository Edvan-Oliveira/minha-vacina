import * as React from "react";
import { View, Text, TouchableOpacity } from "react-native";
import { stylesItemCampanha } from "../../styles/styleItemCampanha";
import { Campanha } from "../../models/campanha";
import { MaterialIcons } from "@expo/vector-icons";
import { useNavigation } from "@react-navigation/core";

export interface ItemCampanhaProps {
  campanha: Campanha;
}

export function ItemCampanha(props: ItemCampanhaProps) {
  const nav = useNavigation();
  const { campanha } = props;

  const obterStatusDaCampanha = (): string => {
    const dataInicio : Date = new Date(campanha.dataInicio)
    return dataAtual.getTime() < dataInicio.getTime() ? "Pendente" : "Acontecendo";
  }

  //Formatando Data
  function dataFormatada(data): string {
    let pegandoData = new Date(data);
    let diaFormatado =
      pegandoData.getDate() < 10
        ? "0" + (pegandoData.getDate())
        : pegandoData.getDate();
    let mesFormatado =
      pegandoData.getMonth() + 1 < 10
        ? "0" + (pegandoData.getMonth() + 1)
        : pegandoData.getMonth() + 1;
    return diaFormatado + "/" + mesFormatado + "/" + pegandoData.getFullYear();
  }
  let dataAtual = new Date();

  return (
    <TouchableOpacity
      style={stylesItemCampanha.containerPrincipal}
      onPress={() => nav.navigate("detalhe-campanha", { campanha })}
    >
      <View style={stylesItemCampanha.containerNomeCampanha}>
        <Text numberOfLines={2} style={stylesItemCampanha.NomeCampanha}>
          {campanha.nome.toUpperCase()}
        </Text>
      </View>
      <View style={stylesItemCampanha.containerNomeMunicipio}>
        <Text style={stylesItemCampanha.textAtivo}>
          {obterStatusDaCampanha()}{" "}
        </Text>
        <View style={stylesItemCampanha.linhaHorizontal} />
        <Text style={stylesItemCampanha.nomeMunicipio}>
          {campanha.municipio.nome}
        </Text>
      </View>
      <View style={stylesItemCampanha.containerIdade}>
        <MaterialIcons name="people" size={30} color={"rgba(25,25,112, 0.9)"} />
        <Text style={stylesItemCampanha.textIdadeData}>
          {campanha.idadeMinima} - {campanha.idadeMaxima} anos
        </Text>
      </View>
      <View style={stylesItemCampanha.containerData}>
        <MaterialIcons name="event" size={30} color={"rgba(25,25,112, 0.9)"} />
        <Text style={stylesItemCampanha.textIdadeData}>
          {dataFormatada(campanha.dataInicio)} -{" "}
          {dataFormatada(campanha.dataFim)}
        </Text>
      </View>
    </TouchableOpacity>
  );
}
