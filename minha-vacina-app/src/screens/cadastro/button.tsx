import * as React from 'react';
import { StyleProp, View, ViewStyle } from 'react-native';
import { Button } from 'react-native-elements';


export interface ButtonDataCadastroProps {
  titulo: string
  icone?: ('today')
  onPress(): void
  disabled?: boolean
  estilo?: StyleProp<ViewStyle>
}

export function ButtonDataCadastro (props: ButtonDataCadastroProps) {
  return (
    <View>
      <Button 
        title={props.titulo}
        icon={{name: props.icone, color:'white', size:30,}}
        onPress={props.onPress}
        disabled={props.disabled}
        buttonStyle={props.estilo}
      />
    </View>
  );
}
