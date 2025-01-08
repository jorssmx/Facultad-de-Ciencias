from dotenv import load_dotenv
from os.path import join, dirname, isdir
from os import environ
import pymysql

class BaseDeDatosProyecto():

    def __init__(self):
        dotenv_path = join(dirname(__file__), ".env")
        load_dotenv(dotenv_path)

        dbname=environ.get("db_name")
        host=environ.get("db_host")
        username=environ.get("db_username")
        password=environ.get("db_password")

        self.connection = pymysql.connect(
            user=username,
            password=password,
            host=host,
            database=dbname,
            charset='utf8mb4')

        self.cursor = self.connection.cursor()
        print ("Conexion Establecida")

    ##nos muestra todas las terminales con todo el contenido.
    def mostrarTerminales(self):
        sql = "SELECT * FROM TABLA"
        try:
            self.cursor.execute(sql)
            TABLA = self.cursor.fetchall()
            for i in range(0,len(TABLA)):
                print(str(TABLA[i]))
        except Exception as e:
            raise
    ##para cuando el Administrador quiera agregar una nueva Terminal.
    def agregarTerminal(self, terminal, parada, destino, tarifa, asientosDisponibles):
        sql = "INSERT INTO TABLA(terminal, parada, destino, tarifa, asientosDisponibles) VALUES('{}','{}', '{}', {}, {})".format(terminal, parada, destino, tarifa, asientosDisponibles);
        self.cursor.execute(sql)
        self.connection.commit()

    ##para cuendo el Administrador quiera eliminar una Terminal.
    def eliminarTerminal(self, id):
        sql = "DELETE FROM TABLA WHERE id = {}".format(id)
        self.cursor.execute(sql)
        self.connection.commit()

    def conexionFinalizada(self):
        self.connection.close()
        print ("Se ha desconectado la base da datos.")

def main():
    conexion = BaseDeDatosProyecto()
    print('\n'+"-------------------Bienvenido al Menu-----------------------")
    print('\n' + "Este es un sistema de administracion de rutas de autobuses")
    print("y venta de boletos.")
    print('\n' + "************************************************************" + '\n')
    print("Estas son las terminales con la parada actual y el destino con")
    print("la tarifa incluida y los asientos disponibles hasta el momento.")
    conexion.mostrarTerminales()
    print("Administrador")
    print("Vendedor")
    print("---------------------")
    print("El Administrador puede agregar y eliminar terminales.")
    print('\n'+"El Vendedor consulta las terminales disponibles")
    print("agregar cosas faltantes")
    print("---------------------")

    print("1: consultar terminal")
    print("2: agregar nueva terminal")
    print("3: eliminar terminal")
    teclado = input("Escoja la funcion que quiere hacer :")

    if(teclado == 1):
        conexion.mostrarTerminales()
    if(teclado == 2):
        print("agregue los campos que se le piden: ")
        terminal = input("nombre de la nueva terminal :")
        parada= input("nombre de la nueva parada :")
        destino= input("nombre del destino :")
        tarifa= int(input("tarifa es decir cual es el costo de esa ruta :"))
        asientosDisponibles= int(input("los asientos disponibles :"))
        conexion.agregarTerminal(terminal,parada,destino,tarifa,asientosDisponibles)
        conexion.mostrarTerminales()
    if(teclado == 3):
        conexion.mostrarTerminales()
        id = input("digite el numero de fila que quiere eliminar: ")
        conexion.eliminarTerminal(id)
        conexion.mostrarTerminales()

if __name__== "__main__":
    main()
