import * as React from 'react';
import { StyleProp, View, ViewStyle } from 'react-native';
import { Button } from 'react-native-elements';


export interface ButtonLoginProps {
  titulo: string
  onPress(): void
  estilo?: StyleProp<ViewStyle>
  estiloTitulo?: StyleProp<ViewStyle>
}

export function ButtonLogin (props: ButtonLoginProps) {
  return (
    <View>
      <Button 
        title={props.titulo}
        onPress={props.onPress}
        buttonStyle={props.estilo}
      />
    </View>
  );
}
