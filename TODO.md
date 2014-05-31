# Binding a SL4J
Implementar un binding a SLF4J http://www.slf4j.org/ para que una library que use SLF4J pueda 
usar nuestro  logger como backend. 

# [DONE] Nuevo Nivel
Se requiere agregar el nivel ``TRACE`` > DEBUG > INFO > WARN > ERROR > FATAL > OFF. 

# [DONE] Multiples Loggers
Permitir definir y utilizar loguers distintos en diferentes areas de una aplicacion. Es decir, se 
quiere poder utilizar dos loggers con distintas configuraciones en lugares distintos. 

    Ej: 
    //“en el Modulo A” 
    logger = xxxx.getLogger(“module­A­logger”) 
    //“en el Modulo B” 
    logger = xxxx.getLogger(“module­B­logger”) 
 
Y cada uno de esos Loggers tienen potencialmente una configuración distinta. 

# Logger API
Agregar la posibilidad de recibir una exception (Throwable) como parametro de la api.  
Ej: logger.info(‘un mensaje’, exception) 

# Filtros
- Permitir configurar un filtro a un Logger particular que filtre aquellos mensajes que 
cumplan con algun patrón de regular expression. 
- Permitir a un usuario definir y configurar un Filtro Custom, que le permita decidir en base 
a todas las propiedades de un mensaje (contenido, nivel, fecha, etc) si lo desea loguear 
o no. 

# Configuración
Permitir leer la configuración desde un archivo XML. 

# Inicialización
La herramienta deberá intentar leer la configuración automáticamente de la siguiente manera: 
- Si existe el archivo logger­config.properties, leerlo y utilizar esa configuración. 
- Si existe el archivo logger­config.xml, leerlo y utilizar esa configuración. 
- Si no existe ningún de los dos anteriores, utilizar una configuración default (por ejemplo 
que se utilice siempre el mismo Logger, con nivel INFO y un formato de mensaje 
específico). 

# Nuevo Formato
Se quiere poder formatear los mensajes a un JSON del siguiente estilo: 

    {‘datetime’: ‘2001­07­04T12:08:56.235­0700’, ‘level’: ‘INFO’, ‘logger’: ‘LoggerName’, ‘message’: ‘processing ...’} 

# [DONE] Formato Mensajes (Pattern de la entrega anterior) 
Agregar una nueva pseudo­variable: 
- %g debería mostrar el nombre del Logger que emitió el mensaje. 

# Destinos Custom
Permitir al usuario definir y configurar sus propios destinos.  
Ej: (pueden usar otra estructura) 

    <...> 
        <implementor>org.foo.bar.MailAppender</implementor> 
        <param1>192.168.1.1</param1> 
        <param2>juan­perez­123</param2> 
    </...> 

y “org.foo.bar.MailAppender” es escrita por el usuario, no existe en la herramienta. 
