import * as React from 'react';
import { View, Text, Modal } from 'react-native';
import { Button } from 'react-native-elements';
import { styles } from '../styles/styleLoginCadastro';

export interface ModalTermoUsoProps {
  titulo?: string
  visivel: boolean
  onCancelar(): void
  children: any
}

export function ModalTermoUso (props: ModalTermoUsoProps) {
    return (
      <Modal
        visible={props.visivel}
        animationType={"slide"}
        transparent={true}
      >
        <View style={styles.containerModal}>
          <View style={styles.estiloModal}>
            <Text style={styles.tituloModal}>{props.titulo}</Text>
              {props.children}  
          </View>
          <Button buttonStyle={styles.btnModalCancelar} title="Fechar" onPress={() => props.onCancelar()}/>
        </View>
      </Modal>
    );
}

export interface ModalSenhaProps {
  titulo?: string
  visivel: boolean
  onCancelar(): void
  children: any
}

export function ModalSenha (props: ModalSenhaProps) {
    return (
      <Modal
        visible={props.visivel}
        animationType={"slide"}
        transparent={true}
      >
        <View style={styles.containerModal}>
          <View style={styles.estiloModalSenha}>
            <Text style={styles.tituloModal}>{props.titulo}</Text>
              {props.children}  
          </View>
          <Button buttonStyle={styles.btnModalCancelar} title="Fechar" onPress={() => props.onCancelar()}/>
        </View>
      </Modal>
    );
}

