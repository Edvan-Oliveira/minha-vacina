import * as React from 'react';
import { View } from 'react-native';
import { Input } from 'react-native-elements';
import { styles } from '../styles/styleLoginCadastro';


export interface InputCampoProps {
    placeholder: string
    icone: string
    tipoTeclado?: ('email-address')
    onChangeText(texto: string): void
    onBlur(): void
    
}

export function InputCampo (props: InputCampoProps) {
    return (
      <View >
        <Input placeholder={props.placeholder}
            placeholderTextColor="white"
            autoCorrect={false}
            onChangeText={props.onChangeText}
            onBlur={props.onBlur}
            keyboardType={props.tipoTeclado}
            leftIcon={{name: props.icone, color:'white', size:32}}
            style={styles.input}
            inputContainerStyle={styles.input} 
        />
      </View>
    )
}

export interface InputSenhaProps {
    placeholder: string
    onChangeText(texto: string): void
    onBlur(): void
    secureText: boolean  
}
    
export function InputSenha (props: InputSenhaProps) {
    return (
      
        <View>
          <Input placeholder={props.placeholder}
              placeholderTextColor="white"
              autoCorrect={false}
              onChangeText={props.onChangeText}
              onBlur={props.onBlur}
              leftIcon={{name:'lock', color:'white', size:32}}
              style={styles.input}
              inputContainerStyle={styles.input}
              secureTextEntry={props.secureText}
          />
        </View>
    )
}

