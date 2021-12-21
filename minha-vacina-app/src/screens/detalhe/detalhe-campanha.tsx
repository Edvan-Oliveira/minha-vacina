import * as React from "react";
import {
  View,
  Text,
  ScrollView,
  TouchableOpacity,
  Platform,
  ToastAndroid,
} from "react-native";
import { stylesDetalheCampanha } from "../../styles/styleDetalheCampanha";
import { MaterialIcons } from "@expo/vector-icons";
import { useRoute, useNavigation } from "@react-navigation/core";
import { Button } from "react-native-elements";
import { Campanha } from "../../models/campanha";
import { CampanhasProviders } from "../../providers/campanhas";
import { useEffect } from "react";
import { UsuariosProviders } from "../../providers/usuarios";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { Usuario } from "../../models/usuario";
import { useState } from "react";
import { AntDesign } from '@expo/vector-icons';
import { Entypo } from '@expo/vector-icons';

export interface DetalheCampanhaScreenProps { }

export function DetalheCampanhaScreen(props: DetalheCampanhaScreenProps) {
  const nav = useNavigation();
  const route = useRoute();

  //@ts-ignore
  const campanha: Campanha = route.params?.campanha;

  //Formatando Data
  function dataFormatada(data): string {
    let pegandoData = new Date(data);
    let diaFormatado =
      pegandoData.getDate() < 10
        ? "0" + pegandoData.getDate()
        : pegandoData.getDate();
    let mesFormatado =
      pegandoData.getMonth() + 1 < 10
        ? "0" + (pegandoData.getMonth() + 1)
        : pegandoData.getMonth() + 1;
    return diaFormatado + "/" + mesFormatado + "/" + pegandoData.getFullYear();
  }

  let dataAtual = new Date();
  let resumo = campanha.vacina.nome.substr(0, 14);

  const [usuario, setUsuario] = useState<Usuario>();
  const [associou, setAssociou] = useState<boolean>(false)

  useEffect(() => {
    UsuariosProviders.ObterUsuarioLogado()
      .then(u => {
        if (u.vacinas != undefined) {
          u.campanhas.forEach(c => {
            if (c.id == campanha.id) setAssociou(true)
          })
        }
        setUsuario(u);
      })
      .catch((e) => ToastAndroid.show("Usuario não existe", 300));
  }, []);

  const associarUsuario = () => {
    CampanhasProviders.AssociarUsuario(campanha.id)
      .then(c => {
        usuario.campanhas.push(campanha)
        AsyncStorage.setItem("usuario", JSON.stringify(usuario));
        setAssociou(!associou)
      })
      .catch(e => setAssociou(false))
  }

  const desassociarUsuario = () => {
    CampanhasProviders.DesassociarUsuario(campanha.id)
      .then(r => {
        usuario.campanhas = usuario.campanhas.filter(c => c.id != campanha.id)
        AsyncStorage.setItem("usuario", JSON.stringify(usuario));
        setAssociou(!associou)
      })
      .catch(e => setAssociou(false))
  }

  return (
    <View style={stylesDetalheCampanha.containerPrincipal}>
      <View style={stylesDetalheCampanha.header}>
        <TouchableOpacity onPress={() => nav.navigate("home")}>
          <MaterialIcons name="arrow-back" size={24} color={"white"} />
        </TouchableOpacity>
        <Text style={stylesDetalheCampanha.textHeader}>Campanha</Text>
      </View>
      <ScrollView>
        <View style={stylesDetalheCampanha.containerNome}>
          <Text style={stylesDetalheCampanha.textNome}>
            {campanha.nome.toUpperCase()}
          </Text>
        </View>
        <View style={stylesDetalheCampanha.containerDescricao}>
          <Text style={stylesDetalheCampanha.descricao}>
            {campanha.descricao}
          </Text>
        </View>
        <View style={stylesDetalheCampanha.containerTextIcone}>
          <View style={stylesDetalheCampanha.containerTextInfo}>
            <MaterialIcons
              name="coronavirus"
              size={60}
              color={"rgba(25,25,112, 0.9)"}
            />
            <Text style={stylesDetalheCampanha.textInfo}>{resumo}</Text>
          </View>
          <View style={stylesDetalheCampanha.containerTextInfo}>
            <MaterialIcons
              name="location-city"
              size={60}
              color={"rgba(25,25,112, 0.9)"}
            />
            <Text style={stylesDetalheCampanha.textInfo}>
              {campanha.municipio.nome}
            </Text>
          </View>
          <View style={stylesDetalheCampanha.containerTextInfo}>
            <MaterialIcons
              name="people"
              size={60}
              color={"rgba(25,25,112, 0.9)"}
            />
            <Text style={stylesDetalheCampanha.textInfo}>
              {campanha.idadeMinima} - {campanha.idadeMaxima} anos
            </Text>
          </View>
        </View>
        <View>
          <Text style={stylesDetalheCampanha.tituloMaisInfo}>
            Mais informações
          </Text>
        </View>
        <View style={stylesDetalheCampanha.containermaisInfo}>
          {campanha.locais.map((l) => {
            return (
              <Text style={stylesDetalheCampanha.textMaisInfo} key={l.id}>
                Lugar: {l.descricao}, CEP: {l.cep}, Bairro: {l.bairro}, Rua:{" "}
                {l.rua} Número: {l.numero}
              </Text>
            );
          })}
          <Text style={stylesDetalheCampanha.textMaisInfo}>
            Horário: {campanha.horarioInicioDia.substr(0, 5)} -{" "}
            {campanha.horarioFimDia.substr(0, 5)}
          </Text>
          <Text style={stylesDetalheCampanha.textMaisInfo}>
            Situação:{" "}
            {campanha.dataInicio > dataAtual ? "Pendente" : "Acontecendo"}
          </Text>
          <Text style={stylesDetalheCampanha.textMaisInfo}>
            Período: {dataFormatada(campanha.dataInicio)} -{" "}
            {dataFormatada(campanha.dataFim)}
          </Text>
        </View>
      </ScrollView>
      <Button title={associou ? "Você será lembrado!" : "QUERO SER LEMBRADO!"}
        buttonStyle={{ height: 55 }} onPress={associou ? desassociarUsuario : associarUsuario}
        icon={associou ? <AntDesign name="checkcircle" size={24} color="#eee" style={{ marginRight: 15 }} />
          : <Entypo name="back-in-time" size={24} color="#eee" style={{ marginRight: 15 }} />} />
    </View>
  );
}
