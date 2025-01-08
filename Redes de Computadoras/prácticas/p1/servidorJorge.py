from flask import Flask, request, jsonify
import requests
import getmac


app = Flask(__name__)

valor = 0
next_node = "https://684c-189-228-60-24.ngrok-free.app/"
name = "Jorge"

@app.route('/data', methods=['POST'])
def receive_data():
    global valor
    data = request.get_json()
    
    if 'valor' in data:
        valor = data['valor'] + 1
        print(f"Servidor {name} ha recibido el valor {valor}")
        
        if valor >= 50:
            return jsonify({"message": f"Se ha llegado al valor 50, terminado en el servevidor {name}"}), 200
        else: 
            send_data(next_node)
            return jsonify({"message": "Dato recibido y enviado al siguiente servidor"}), 200
    return jsonify({"error": "Error al recibir el dato"}), 400

def send_data(next_node):
    global valor
    
    payload = {
        "valor": valor,
        "mac": "00:00:00:00:00:01",
        "name": name
    }
    try:
        response = requests.post(next_node + "/data", json=payload)
        if response.status_code == 200:
            print(f"Servidor {name} ha enviado el valor {valor}")
        else:
            print(f"Error al enviar el valor {valor}")
    except Exception as e:
        print(f"Error al enviar el valor {valor}, error {e}")
        
@app.route('/start', methods=['POST'])
def start_process():
    global valor
    print(f"mac de este dispositivo: {getmac.get_mac_address()}")
    valor = 0
    print(f"Servidor {name} ha reiniciado el valor")
    send_data(next_node)
    return jsonify({"message": "Proceso iniciado"}), 200

if __name__ == '__main__':
    app.run(port=5001)