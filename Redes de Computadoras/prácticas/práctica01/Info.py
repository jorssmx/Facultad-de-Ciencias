import uuid
import socket

# Obtener la dirección MAC
def obtener_mac():
    mac = uuid.UUID(int=uuid.getnode()).hex[-12:]
    return ":".join([mac[e:e+2] for e in range(0, 12, 2)])

# Obtener el nombre del servidor
mi_mac = obtener_mac()
mi_nombre = socket.gethostname()

print(f"Dirección MAC: {mi_mac}")
print(f"Nombre del servidor: {mi_nombre}")
