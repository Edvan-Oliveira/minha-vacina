import * as React from "react";
import { View, Text, TouchableOpacity, Image } from "react-native";
import { stylesItemVacina } from "../../styles/styleItemVacina";
import { Vacina } from "../../models/vacina";
import HTMLView from "react-native-htmlview";
import { useNavigation } from "@react-navigation/core";

export interface ItemVacinaScreenProps {
  vacina: Vacina;
}

export function ItemVacinaScreen(props: ItemVacinaScreenProps) {
  const nav = useNavigation();
  const { vacina } = props;

  let resumo = vacina.descricao.substr(0, 130);

  return (
    <TouchableOpacity
      onPress={() =>
        nav.navigate("detalhe-vacina", { vacina })
      }
      style={stylesItemVacina.containerPrincipal}
    >
      <View style={stylesItemVacina.containerNome}>
        <Text style={stylesItemVacina.nomeVacina}>
          {vacina.nome.toUpperCase()}
        </Text>
      </View>
      <View style={stylesItemVacina.containerDescricao}>
        <HTMLView value={resumo + "..."} stylesheet={stylesItemVacina} />
      </View>
      <View style={stylesItemVacina.containerImagem}>
        <Image
          style={stylesItemVacina.imagemVacina}
          source={{ uri: vacina.imagem }}
        />
      </View>
    </TouchableOpacity>
  );
}
