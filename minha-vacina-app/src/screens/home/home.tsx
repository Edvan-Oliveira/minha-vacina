import * as React from "react";
import {
  View,
  Text,
  StatusBar,
  TouchableOpacity,
  Image,
  FlatList,
  ScrollView,
  Alert,
  ToastAndroid,
} from "react-native";
import { stylesHome } from "../../styles/styleHome";
import { MaterialIcons } from "@expo/vector-icons";
import { useNavigation } from "@react-navigation/core";
import { UsuariosProviders } from "../../providers/usuarios";
import { CampanhasProviders } from "../../providers/campanhas";
import { useEffect, useState } from "react";
import { Campanha } from "../../models/campanha";
import { ItemCampanha } from "./item-campanha";
import { VacinasProviders } from "../../providers/vacinas";
import { Vacina } from "../../models/vacina";
import { ItemVacinaScreen } from "./item-vacina";
import { Usuario } from "../../models/usuario";

export interface HomeScreenProps {}

export function HomeScreen(props: HomeScreenProps) {
  const [usuario, setUsuario] = useState<Usuario>();

  useEffect(() => {
    UsuariosProviders.ObterUsuarioLogado()
      .then((r) => {
        setUsuario(r);
      })
      .catch((e) => ToastAndroid.show("Usuario não existe", 300));
  }, []);

  const nav = useNavigation();

  const logout = () => {
    nav.navigate("login");
    UsuariosProviders.Logout;
  };

  //Listando Campanhas
  const [listaCampanhas, setListaCampanhas] = useState<Campanha[]>([]);
  nav.addListener("focus", () => {
    CampanhasProviders.Listar().then((campanhas) =>
      setListaCampanhas(campanhas)
    );
  });

  const [listaVacinas, setListaVacinas] = useState<Vacina[]>([]);
  nav.addListener("focus", () => {
    VacinasProviders.Listar().then((vacinas) => setListaVacinas(vacinas));
  });

  return (
    <View style={stylesHome.fundo}>
      <StatusBar />
      <View style={stylesHome.containerDecima}>
        <View style={stylesHome.containerUsuario}>
          <View style={stylesHome.containerNomeUsuario}>
            <Text style={stylesHome.textOla}>Olá</Text>
            <Text style={stylesHome.nomeUsuario} numberOfLines={1}>
              , {usuario?.nome}
            </Text>
          </View>

          <View style={stylesHome.borderImg}>
            <View style={stylesHome.containerFoto}>
              <MaterialIcons
                style={stylesHome.imgUsuario}
                name="person-outline"
                color="rgba(25,25,112, 0.9)"
                size={35}
              />
            </View>
          </View>
        </View>
        <TouchableOpacity style={stylesHome.containerBanner}>
          <Image
            style={stylesHome.imgBanner}
            source={require("../../assets/image/banner/banner-saibamais.png")}
          />
        </TouchableOpacity>
        <View style={stylesHome.containerTextCampanhas}>
          <Text style={stylesHome.textCampanhas}>Campanhas</Text>
          <MaterialIcons
            name="campaign"
            size={25}
            color={"rgba(25,25,112, 0.9)"}
            style={{ marginTop: 7, left: 6 }}
          />
        </View>
        <View style={stylesHome.containerCampanha}>
          <FlatList
            horizontal
            data={listaCampanhas}
            ItemSeparatorComponent={() => <View style={{ width: 5 }} />}
            keyExtractor={(id, index) => index.toString()}
            renderItem={({ item }) => <ItemCampanha campanha={item} />}
          />
        </View>
      </View>
      <View style={stylesHome.containerTextVacinas}>
        <Text style={stylesHome.textVacinas}>Vacinas</Text>
      </View>
      <ScrollView>
        {listaVacinas.map((v) => {
          return <ItemVacinaScreen vacina={v} key={v.id} />;
        })}
      </ScrollView>
    </View>
  );
}
