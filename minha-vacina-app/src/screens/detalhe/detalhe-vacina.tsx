import * as React from "react";
import { View, Text, TouchableOpacity, ScrollView, Image, ToastAndroid } from "react-native";
import { useNavigation, useRoute } from "@react-navigation/core";
import { MaterialIcons } from "@expo/vector-icons";
import { stylesDetalheVacina } from "../../styles/styleDetalheVacina";
import HTMLView from "react-native-htmlview";
import { Button } from "react-native-elements";
import { VacinasProviders } from "../../providers/vacinas";
import { useState } from "react";
import { AntDesign } from '@expo/vector-icons';
import { Entypo } from '@expo/vector-icons';
import { Vacina } from "../../models/vacina";
import { useEffect } from "react";
import { UsuariosProviders } from "../../providers/usuarios";
import { Usuario } from "../../models/usuario";
import AsyncStorage from "@react-native-async-storage/async-storage";

export interface DetalheVacinaScreenProps {}

export function DetalheVacinaScreen(props: DetalheVacinaScreenProps) {
  const nav = useNavigation();
  const route = useRoute();

  //@ts-ignore
  const vacina: Vacina = route.params?.vacina

  const [usuario, setUsuario] = useState<Usuario>();
  const [associou, setAssociou] = useState<boolean>(false) 

  useEffect(() => {
    UsuariosProviders.ObterUsuarioLogado()
      .then(u => {
        if (u.vacinas != undefined) {
          u.vacinas.forEach(v => {
            if (v.id == vacina.id) setAssociou(true)
          })
        } 
        setUsuario(u);
      })
      .catch((e) => ToastAndroid.show("Usuario não existe", 300));
  }, []);

  const associarUsuario = () => {
    VacinasProviders.AssociarUsuario(vacina.id)
      .then(r => {
        usuario.vacinas.push(vacina)
        AsyncStorage.setItem("usuario", JSON.stringify(usuario));
        setAssociou(!associou)
      })
      .catch(e => setAssociou(false))   
  }

  const desassociarUsuario = () => {
    VacinasProviders.DesassociarUsuario(vacina.id)
      .then(r => {
        usuario.vacinas = usuario.vacinas.filter(v => v.id != vacina.id)
        AsyncStorage.setItem("usuario", JSON.stringify(usuario));
        setAssociou(!associou)
      })
      .catch(e => setAssociou(false))  
  }

  return (
    <View style={stylesDetalheVacina.containerPrincipal}>
      <View style={stylesDetalheVacina.header}>
        <TouchableOpacity onPress={() => nav.navigate("home")}>
          <MaterialIcons name="arrow-back" size={24} color={"white"} />
        </TouchableOpacity>
        <Text style={stylesDetalheVacina.textHeader}>Vacina</Text>
      </View>
      <ScrollView>
        <View style={stylesDetalheVacina.containerImagemVacina}>
          <Image
            style={stylesDetalheVacina.imagemVacina}
            source={{ uri: vacina.imagem }}
          />
        </View>
        <View style={stylesDetalheVacina.containerNome}>
          <Text style={stylesDetalheVacina.textNome}>{vacina.nome.toUpperCase()}</Text>
        </View>
        <View style={stylesDetalheVacina.containerDescricao}>
          <HTMLView value={vacina.descricao} stylesheet={stylesDetalheVacina} />
        </View>
      </ScrollView>
      <Button title={associou ? "Você será avisado!" : "QUERO SER AVISADO!" } 
        buttonStyle={{ height: 55 }} onPress={associou ? desassociarUsuario : associarUsuario} 
        icon={associou ? <AntDesign name="checkcircle" size={24} color="#eee" style={{marginRight: 15}}/> 
              : <Entypo name="back-in-time" size={24} color="#eee" style={{marginRight: 15}}/>} />
    </View>
  );
}
