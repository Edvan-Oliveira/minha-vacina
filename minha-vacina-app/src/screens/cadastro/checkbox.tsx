import * as React from "react";
import {
  View,
  GestureResponderEvent,
  StyleProp,
  ViewStyle,
} from "react-native";
import { CheckBox } from "react-native-elements";

export interface CheckboxCampoProps {
  titulo: string;
  iconeMarcado: "check";
  iconeDesmarcado: "square-o";
  corMarcada?: "green";
  verificado?: boolean;
  onPress(event: GestureResponderEvent): void;
  estilo?: StyleProp<ViewStyle>;
}

export function CheckboxCampo(props: CheckboxCampoProps) {
  return (
    <View>
      <CheckBox
        title={props.titulo}
        checkedIcon={props.iconeMarcado}
        uncheckedIcon={props.iconeDesmarcado}
        checkedColor={props.corMarcada}
        checked={props.verificado}
        onPress={props.onPress}
        containerStyle={props.estilo}
      />
    </View>
  );
}
