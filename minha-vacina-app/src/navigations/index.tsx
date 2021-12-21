import * as React from "react";
import {
  NavigationContainer,
  StackActions,
  useNavigation,
} from "@react-navigation/native";
import {
  CardAnimationContext,
  CardStyleInterpolators,
  createStackNavigator,
} from "@react-navigation/stack";
import { LoginScreen } from "../screens/login/login";
import { CadastroScreen } from "../screens/cadastro/cadastro";
import { HomeScreen } from "../screens/home/home";
import { DetalheVacinaScreen } from "../screens/detalhe/detalhe-vacina";
import { DetalheCampanhaScreen } from "../screens/detalhe/detalhe-campanha";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { View } from "react-native";
import App from "../../App";

const Stack = createStackNavigator();
/*
const nav = useNavigation();
const TelaInicial = () => {
  AsyncStorage.getItem("token").then((valor) => {
    if (valor) nav.navigate("home");
    else nav.navigate("login");
  });
  return <View />;
};
*/

export const MainNavigation = () => (
  <NavigationContainer>
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      {/*<Stack.Screen name="inicial" component={TelaInicial} />*/}
      <Stack.Screen name="login" component={LoginScreen} />
      <Stack.Screen name="home" component={HomeScreen} />
      <Stack.Screen name="cadastro" component={CadastroScreen} />
      <Stack.Screen name="detalhe-vacina" component={DetalheVacinaScreen} />
      <Stack.Screen name="detalhe-campanha" component={DetalheCampanhaScreen} />
    </Stack.Navigator>
  </NavigationContainer>
);
