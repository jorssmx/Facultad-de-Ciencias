from flask import Flask, request, jsonify
import requests
import socket

app = Flask(__name__)

# Valor inicial de la variable
valor_actual = 0

# Información del servidor actual
mi_mac = "52:28:97:95:c4:8b"  # Coloca la MAC de tu servidor aquí
mi_nombre = "MacBook-Air-de-Jorge.local"  # Coloca tu nombre aquí

# URL del siguiente nodo (deberás cambiar esta URL para cada servidor en el ciclo)
siguiente_nodo = "https://0e9d-2806-106e-25-c05-d5ee-3cf0-d547-2d6c.ngrok-free.app"

# Ruta para enviar información al siguiente nodo
@app.route('/enviar', methods=['POST'])
def enviar_variable():
    global valor_actual
    datos = request.get_json()

    if datos:
        valor_actual = datos.get('valor', 0) + 1
        if valor_actual >= 50:
            # Notificar que el proceso ha terminado
            return jsonify({"estado": "Finalizado", "valor_final": valor_actual, "name": mi_nombre}), 200
        else:
            # Enviar la variable al siguiente nodo
            siguiente_datos = {
                "valor": valor_actual,
                "mac": mi_mac,
                "name": mi_nombre
            }
            try:
                respuesta = requests.post(siguiente_nodo, json=siguiente_datos)
                return jsonify({"estado": "Enviado", "valor_actual": valor_actual, "response": respuesta.json()}), 200
            except Exception as e:
                return jsonify({"estado": "Error", "mensaje": str(e)}), 500
    return jsonify({"estado": "Error", "mensaje": "Datos no válidos"}), 400

# Ruta para recibir la variable desde el nodo anterior
@app.route('/recibir', methods=['POST'])
def recibir_variable():
    global valor_actual
    datos = request.get_json()

    if datos:
        valor_actual = datos.get('valor', 0)
        if valor_actual >= 50:
            # Si la variable es 50, devolver quién finalizó el proceso
            return jsonify({"estado": "Finalizado", "valor_final": valor_actual, "name": mi_nombre}), 200
        else:
            # Enviar la variable al siguiente nodo
            return jsonify({"estado": "Recibido", "valor_actual": valor_actual}), 200
    return jsonify({"estado": "Error", "mensaje": "Datos no válidos"}), 400

# Ruta extra para elegir el servidor con mayor memoria RAM
@app.route('/iniciar', methods=['POST'])
def iniciar_proceso():
    global valor_actual
    valor_actual = 0  # Iniciar el proceso con el valor 0
    return jsonify({"estado": "Proceso iniciado", "valor_actual": valor_actual}), 200

if __name__ == '__main__':
    app.run(port=5000)
