<p  align="center">
  <img  width="200"  src="https://www.fciencias.unam.mx/sites/default/files/logoFC_2.png"  alt="">  <br>Compiladores  2025-1 <br>
  Práctica 4: Analizadores sintácticos con BYACC/J (YACC) <br> Profesora: Ariel Adara Mercado Martínez
</p>

## Analizadores Sintácticos con BYACC/J
### Objetivo:
1. Aprender a definir una gramática en Yacc.
2. Implementar una gramática con Yacc.
3. Unir un programa generado con Lex/Flex y el programa generado con Yacc.


### Instalación de BYACC/J
* Para Linux:

    - Obtenemos el archivo de instalación desde [https://byaccj.sourceforge.net/#download](https://byaccj.sourceforge.net/#download)

    - Descomprimimos en algún directorio pertinente:
        ```bash
        $ tar -C /some/user/directory -xvf byaccj1.15_linux.tar.gz
        ```

    - Creamos un enlace simbólico a los ejecutables del usuario para poder ejecutar desde cualquier directorio
        ```
        # ln -s /some/user/directory/yacc.linux /usr/bin/byaccj
        ```

### Para comprobar que se ha instalado correctamente:
```$ byaccj```


## Estructura del Proyecto

```bash
project/
├── build.xml                  # Script de ANT para compilación
├── docs/                      # Documentación
│   ├── INSTRUCTIONS.md        # Instrucciones de la practica
│   └── PreguntasPráctica4.pdf # Respuestas teoricas de la practica
├── README.md                  # Este archivo
└── src/
    ├── calculator_byaccj/
    │   └── archive.y          # Definición del analizador sintáctico
    ├── calculator_byaccj_jflex/
    │   ├── byacc/
    │   │   └── Parser.y       # Analizador sintáctico
    │   ├── jflex/
    │   │   └── Lexer.flex     # Analizador léxico
    │   └── Main.java          # Programa principal para pruebas
    └── syntax_analyzer_byaccj/
        ├── byacc/
        │   └── Parser.y       # Analizador sintáctico
        ├── Colors.java        # Colores para salida en terminal
        ├── jflex/
        │   └── Lexer.flex     # Analizador léxico
        ├── LexicalClass.java  # Clases léxicas
        ├── Main.java          # Programa principal para pruebas
        ├── tests/             # Casos de prueba
        │   ├── invalid_input1.txt
        │   ├── invalid_input2.txt
        │   ├── prueba.txt
        │   ├── valid_input1.txt
        │   └── valid_input2.txt
        └── Token.java         # Definición de tokens
```
### Uso

#### Menu de opciones
Corre en tu terminal el siguiente comando para ver los comandos que puedes correr

```bash
$ ant
```

#### Corre el programa

Copia y pega el comndo que hayas elegido correr, a continuación los comandos recomendados:

```bash
# Run Calculator BYACCJ
$ ant calc-byaccj:run

# Run Calculator BYACCJ+JFlex
$ ant calc-jflex+byaccj:run

# Run all Syntax Analyzis tests
$ ant syntax_analyzer:test-all
```