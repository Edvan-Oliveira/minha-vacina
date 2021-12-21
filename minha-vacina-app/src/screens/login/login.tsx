import { useNavigation } from "@react-navigation/core";
import Constants from 'expo-constants';
import * as Notifications from 'expo-notifications';
import { Formik } from "formik";
import * as React from "react";
import { useEffect } from "react";
import { useRef } from "react";
import { useState } from "react";
import {
  ActivityIndicator, Image, Platform, StatusBar, Text, ToastAndroid, TouchableOpacity, View
} from "react-native";
import { CheckBox } from "react-native-elements";
import * as Yup from "yup";
import { InputCampo, InputSenha } from "../../components/input";
import { ModalSenha } from "../../components/modal";
import { TokenNotificacao } from "../../models/tokenNotificacao";
import { CampanhasProviders } from "../../providers/campanhas";
import { TokenNotificacaoProvider } from "../../providers/token-notificacao";
import { UsuariosProviders } from "../../providers/usuarios";
import { styles } from "../../styles/styleLoginCadastro";
import { ButtonLogin } from "../login/button";

export interface LoginScreenProps {}

export function LoginScreen(props: LoginScreenProps) {

  Notifications.setNotificationHandler({
    handleNotification: async () => ({
      shouldShowAlert: true,
      shouldPlaySound: false,
      shouldSetBadge: false,
    }),
  });

  const nav = useNavigation();
  const responseListener = useRef();

  useEffect(() => {

    //@ts-ignore
    responseListener.current = Notifications.addNotificationResponseReceivedListener(response => {
      const dados = response.notification.request.content.data

      if (dados.idCampanha == undefined) nav.navigate("home");
      
      CampanhasProviders.BuscarPorId(Number(dados.idCampanha))
        .then(campanha => nav.navigate("detalhe-campanha", { campanha }))
        .catch(erro => nav.navigate("home"))
    });

    return () => {
      //@ts-ignore
      Notifications.removeNotificationSubscription(responseListener.current);
    };

  }, []);

  const logar = async (dados) => {
    let resposta = await UsuariosProviders.Logar(dados.email, dados.senha);
    if (resposta) {
      let tokenNotificacao: TokenNotificacao = { token : await registerForPushNotificationsAsync() }
      await TokenNotificacaoProvider.salvarToken(tokenNotificacao);
      nav.navigate("home");
    } else {  
      ToastAndroid.show("Usuario não existe", 300);
    }
  };

  //Estado de selação modal
  const [modalSelecionado, setModalSelecionado] = useState(false);

  const [mostrarSenha, setMostrarSenha] = useState(true);

  async function registerForPushNotificationsAsync() {
    let token;
    if (Constants.isDevice) {
      const { status: existingStatus } = await Notifications.getPermissionsAsync();
      let finalStatus = existingStatus;
      if (existingStatus !== 'granted') {
        const { status } = await Notifications.requestPermissionsAsync();
        finalStatus = status;
      }
      if (finalStatus !== 'granted') {
        alert('Failed to get push token for push notification!');
        return;
      }
      token = (await Notifications.getExpoPushTokenAsync()).data;
      console.log(token);
    } else {
      alert('Must use physical device for Push Notifications');
    }
  
    if (Platform.OS === 'android') {
      Notifications.setNotificationChannelAsync('default', {
        name: 'default',
        importance: Notifications.AndroidImportance.MAX,
        vibrationPattern: [0, 250, 250, 250],
        lightColor: '#FF231F7C',
      });
    }
  
    return token;
  }


  return (
    <View style={styles.fundo}>
      <StatusBar />
      <Formik
        initialValues={{
          email: "",
          senha: "",
        }}
        validationSchema={Yup.object().shape({
          email: Yup.string()
            .required("Campo e-mail obrigatório")
            .email("Email inválido")
            .max(30, "Máximo 30 caracteres"),
          senha: Yup.string()
            .required("Campo senha obrigatório")
            .min(6, "Mínimo 6 caracteres")
            .max(20, "Máximo 20 caracteres"),
        })}
        onSubmit={logar}
      >
        {({
          handleChange,
          handleSubmit,
          errors,
          isSubmitting,
          touched,
          handleBlur,
        }) => (
          <View style={styles.conteinerFormLogin}>
            <View style={styles.containerLogoApp}>
              <Image
                style={styles.logoApp}
                source={require("../../assets/image/logo-app/logo-minha-vacina.png")}
              />
            </View>
            <InputCampo
              placeholder="Digite seu e-mail"
              icone="email"
              tipoTeclado="email-address"
              onChangeText={handleChange("email")}
              onBlur={() => handleBlur("email")}
            />
            {touched.email && <Text style={styles.erro}>{errors.email}</Text>}

            <InputSenha
              placeholder="Digite sua senha"
              secureText={mostrarSenha}
              onChangeText={handleChange("senha")}
              onBlur={() => handleBlur("senha")}
            />
            {touched.senha && <Text style={styles.erro}>{errors.senha}</Text>}

            <CheckBox
              title="Exibir senha"
              checked={mostrarSenha == true ? undefined : true}
              onPress={() => setMostrarSenha(!mostrarSenha)}
              containerStyle={styles.containerCheckBoxSenha}
            />

            <ModalSenha
              titulo="Restaurar senha"
              visivel={modalSelecionado}
              onCancelar={() => setModalSelecionado(!modalSelecionado)}
            >
              <View>
                <Text>
                  Será enviado uma nova senha aleatória para seu e-mail.
                </Text>
                <InputCampo
                  placeholder="Digite seu e-mail"
                  icone="email"
                  tipoTeclado="email-address"
                  onChangeText={handleChange("email")}
                  onBlur={() => handleBlur("email")}
                />
              </View>
            </ModalSenha>

            <TouchableOpacity
              onPress={() => setModalSelecionado(!modalSelecionado)}
            >
              <Text style={styles.textEsqueceuSenha}>Esqueceu sua senha?</Text>
            </TouchableOpacity>

            {isSubmitting && (
              <ActivityIndicator
                style={styles.carregando}
                size="small"
                color="white"
              />
            )}
            {!isSubmitting && (
              <ButtonLogin
                titulo="Entrar"
                onPress={() => handleSubmit()}
                estilo={styles.btnLogin}
              />
            )}
            <TouchableOpacity onPress={() => nav.navigate("cadastro")}>
              <Text style={styles.textContaLoginCadastrar}>
                Ainda não possuo uma conta!
              </Text>
            </TouchableOpacity>
          </View>
        )}
      </Formik>
    </View>
  );
}
