//--------------------------------------------
//
//	Apprende
//  Develop by Carlos Alfredo Cervantes Bedoy
//
//	Android Developer
//
//	Independent project:	carlos.bedoy@gmail.com
//
//	Aguascalientes | Mexico
//-------------------------------------------------------
package com.uandroides.aprende.modelos;

import java.util.ArrayList;

public class CuestionarioDemo {
	private ArrayList<APPregunta> cuestionario;

	public CuestionarioDemo() {
		cuestionario = new ArrayList<APPregunta>();

		// ESPAÑOL_____________________________________________________________

		cuestionario
				.add(new APPregunta(
						"Selecciona la opción cuya relación sea similar a la que se observa en la pareja de palabras en mayúsculas. SUSTANCIAS es a LABORATORIO, como:",

						"albañiles a carretilla", "enfermeras a hospital",
						"martillo a carpintero ", "tejedor a alfombra ",

						2, null));

		cuestionario
				.add(new APPregunta(
						"Selecciona la opción cuyo significado sea similar al de la palabra en mayúsculas. Su sonrisa SOCARRONA le molestaba sobremanera.",

						"Sincera", "Formal", "Cínica ", "Pícara ",

						3, null));

		cuestionario
				.add(new APPregunta(
						"Selecciona la opción cuyo significado sea opuesto al de la palabra en mayúsculas. Francamente, estos problemas para mí son un ALICIENTE.",

						"Atractivo", "Impedimento", "Retraso ", "Estímulo ",

						2, null));

		cuestionario.add(new APPregunta(
				"¿Cuál de los siguientes enunciados expresa un consejo? ",

				"I want to be a doctor ", " She is going to be a doctor ",
				"You should see a doctor ", "He is a good doctor ",

				3, null));

		cuestionario
				.add(new APPregunta(
						"Completa la siguiente frase. Como el hielo es menos _____ que el agua, flota sobre ella. ",

						"frágil", "gélido ", " denso ", "rígido ",

						3, null));

		cuestionario
				.add(new APPregunta(
						"Es la oración subordinada en la que la coma se utiliza correctamente. ",

						"María, la hija del maestro, no llegó a la fiesta ",
						"Con la feria, todos los niños se alegraron ",
						"Para dejar de fumar, Rodolfo se sometió a un tratamientomédico ",
						"Martín, preocupado por el trabajo, no fue a comer ",

						3, null));

		cuestionario
				.add(new APPregunta(
						"Las fichas bibliográficas son útiles ya que la información de estas es: ",

						"importante para conocer a los grandes autores literarios ",
						"esencial para preservar y localizar los libros impresos ",
						"fundamental para tener organizada una librería ",
						"necesaria para ubicar las revistas especializadas ",

						1, null));

		// BIOLOGIA_________________________________________________________________________

		cuestionario
				.add(new APPregunta(
						"Ordena cronológicamente las fases del ciclo de división celular",

						"Profase, metafase, anafase, telofase e interfase",
						"Metafase, anafase, telofase, interfase y profase",
						"Anafase, telofase, interfase, profase y metafase",
						"Interfase, profase, metafase, anafase y telofase",

						4, null));

		cuestionario
				.add(new APPregunta(
						"Enfermedades causadas por bacterias en las vías respiratorias. ",

						"Gonorrea y meningitis ", "Tétanos y difteria ",
						"Neumonía y tuberculosis ",
						"Encefalitis y poliomielitis ",

						3, null));

		cuestionario
				.add(new APPregunta(
						"Es el conjunto de características que permite a los individuos sobrevivir y reproducirse en un ambiente determinado: ",

						"adaptación ", "evolución ", "selección natural ",
						"selección artificial ",

						1, null));

		cuestionario
				.add(new APPregunta(
						"Una característica que comparten la ciencia y la tecnología es que ambas: ",

						"buscan la solución de problemas específicos reales ",
						"estudian la dinámica de la sociedad ",
						"hacen uso básico y exhaustivo del método científico ",
						"venden los productos que se generan de su trabajo ",

						1, null));

		cuestionario
				.add(new APPregunta(
						"Es una categoría taxonómica que se define como el conjunto de individuos capaces de entrecruzarse y dejar descendencia fértil: ",

						"familia ", "género ", "orden ", "especie ",

						4, null));

		cuestionario
				.add(new APPregunta(
						"Son consecuencias de la acumulación del bióxido de carbono en la atmósfera, excepto: ",

						"lluvia ácida en las ciudades ",
						"deshielo de las masas polares ",
						"incremento de los periodos de sequías ",
						"largas temporadas de lluvias ",

						1, null));

		cuestionario
				.add(new APPregunta(
						"¿Cuáles son los productos elaborados en el organismo durante la respiración aerobia? ",

						"Bióxido de carbono, agua y energía (ATP) ",
						"Oxígeno, agua y bióxido de carbono ",
						"Bióxido de carbono, agua y alcohol ",
						"Oxígeno, glucosa y energía (ATP) ",

						1, null));

		// FISICA________________________________________________________________________________

		cuestionario
				.add(new APPregunta(
						"Cuando sintonizamos una estación de radio en la banda de AM, la señal es más débil o posee menor energía que la de FM. Esto se debe a que sus ondas son de menor... ",

						"amplitud ", "frecuencia ", "longitud ", "periodo ", 2,
						null));

		cuestionario
				.add(new APPregunta(
						"Es la relación que existe entre una fuerza aplicada y el área sobre la que esta actúa. ",

						"Cohesión ", "Fluidez ", "Presión ", "Empuje ", 1, null));

		cuestionario
				.add(new APPregunta(
						"El modelo cinético de las partículas permite entender el comportamiento de la: ",

						"presión de los líquidos ",
						"flotación de los cuerpos ",
						"estructura de la materia ", "hidrodinámica ",

						3, null));

		cuestionario
				.add(new APPregunta(
						"Cuando un rayo de luz viaja por el aire y atraviesa por un vidrio cambia la dirección de su trayectoria debido al cambio en: ",

						"el color de la luz ", "la naturaleza de la luz ",
						"la velocidad de la luz ", "la cantidad de luz ", 3,
						null));

		cuestionario
				.add(new APPregunta(
						"Las líneas magnéticas del imán de una brújula salen por el_______ y llegan hacia el _______. ",

						"sur - norte ", "norte - sur ", "este - oeste ",
						"oeste - este ",

						2, null));

		cuestionario
				.add(new APPregunta(
						"Es el proceso mediante el cual un cuerpo adquiere carga eléctrica a través de otro cuerpo cargado eléctricamente; esto sin que haya contacto entre ambos cuerpos. ",

						"Conducción ", "Frotamiento ", "Inducción ",
						"Ionización ",

						3, null));

		// QUIMICA_____________________________________________________________________________

		cuestionario
				.add(new APPregunta(
						"La masa de un mol de una sustancia expresada en gramos recibe el nombre de: ",

						"masa molar ", "peso atómico ", "masa atómica ",
						"peso molecular ",

						1, null));

		cuestionario.add(new APPregunta(
				"Es la combinación física de dos o más sustancias. ",

				"Compuesto ", "Elemento ", "Mezcla ", "Molécula ",

				3, null));

		cuestionario.add(new APPregunta(
				"¿Qué sustancia es considerada el disolvente universal? ",

				"Alcohol ", "Acetona ", "Agua ", "Vinagre ",

				3, null));

		cuestionario.add(new APPregunta(
				"¿Qué le sucede a un átomo cuando se oxida? ",

				"Gana uno o más electrones ", "Pierde uno o más protones ",
				"Gana uno o más neutrones ", "Pierde uno o más electrones ",

				4, null));

		cuestionario
				.add(new APPregunta(
						"Completa el enunciado. En una ________________ hay una reorganización de los átomos formando nuevas sustancias. ",

						"fusión ", "reacción química ", "mezcla homogénea ",
						"destilación ",

						2, null));

		cuestionario
				.add(new APPregunta(
						"Esta ley establece que, en un proceso químico, la masa permanece constante antes y después de la reacción. ",

						"Proust ", "Dalton ", "Lavoisier ", "Wenzel ",

						3, null));

		cuestionario
				.add(new APPregunta(
						"Una de las características de estas sustancias es que al entrar en contacto con el papel tornasol lo cambian a color rosa. ",

						"Básicas ", "Indicadoras ", "Ácidas ", "Neutras ",

						3, null));

		cuestionario
				.add(new APPregunta(
						"Mendeleiev construyó la tabla periódica de los elementos, de acuerdo con: ",

						"los números atómicos ", "las masas atómicas ",
						"el número de neutrones ", "el número de electrones ",

						2, null));

		// HISTORIA_________________________________________________________________________

		cuestionario
				.add(new APPregunta(
						"Completa el siguiente enunciado. Entre 1822 y ______ , __________ protagonizó fallidos intentos para reconquistar México, cuya independencia había desconocido. ",

						"1829 - España ", "1838 - Francia ",
						"1847 - Inglaterra ", "1862 - Alemania ",

						1, null));

		cuestionario
				.add(new APPregunta(
						"La principal actividad de la economía de los pueblos mesoamericanos fue la explotación: ",

						"agrícola ", "minera ", "maderera ", "artesanal ",

						1, null));

		cuestionario
				.add(new APPregunta(
						"Se considera el efecto demográfico más importante de la Segunda Guerra Mundial: ",

						"millones de mutilados y heridos ",
						"millones de muertos civiles y militares ",
						"cantidad de refugiados ", "cantidad de desplazados ",

						1, null));

		cuestionario
				.add(new APPregunta(
						"Se considera el efecto demográfico más importante de la Segunda Guerra Mundial: ",

						"millones de mutilados y heridos ",
						"millones de muertos civiles y militares ",
						"cantidad de refugiados ", "cantidad de desplazados ",

						2, null));

		cuestionario
				.add(new APPregunta(
						"El invento de James Watt que transformó el comercio terrestre y marítimo durante la Revolución Industrial fue: ",

						"la locomotora ", "el motor a diesel ",
						"la máquina de vapor ", "vapor ",

						3, null));

		cuestionario.add(new APPregunta(
				"La principal consecuencia de la llamada Paz Armada fue: ",

				"el inicio de la Primera Guerra Mundial ",
				"la Guerra del Opio entre Inglaterra y China ",
				"el rápido desarrollo de la industria petrolera ",
				"la creación de las Conferencias de París ",

				1, null));

		cuestionario
				.add(new APPregunta(
						"¿Cuál es el principal contenido del Tratado de Guadalupe Hidalgo? ",

						"Casi la mitad del territorio mexicano pasaría a ser parte de Estados Unidos ",
						"Inglaterra mediaría en el conflicto entre los gobiernos de Texas y México ",
						"Estados Unidos reclamaba la aplicación de la doctrina Monroe a su favor ",
						"Texas le declaraba la guerra a México por cambiar su leyes constitucionales ",

						1, null));

		cuestionario
				.add(new APPregunta(
						"Son factores que impulsan el desarrollo de la globalización, excepto: ",

						"libre comercio mundial ", "empresas multinacionales ",
						"privatizaciones masivas ",
						"tradicionalismo ideológico ",

						4, null));

		// GEOGRAFIA__________________________________________________________________________

		cuestionario
				.add(new APPregunta(
						"Es una consecuencia positiva para los países receptores de inmigrantes. ",

						"Trabajadores especializados ",
						"Fortalecimiento de su economía ",
						"Mayor índice educativo ", "Envío de remesas ",

						2, null));

		cuestionario.add(new APPregunta(" Las tradiciones son ejemplo de:",

		"educación ", "multiculturalidad ", "interculturalidad ",
				"identidad cultural ",

				4, null));

		cuestionario
				.add(new APPregunta(
						"Es el factor astronómico que influye en la dinámica terrestre, permitiendo el desarrollo de la vida en el planeta. ",

						"Distancia de la Tierra al Sol ",
						"Forma de la Tierra ", "Presencia de la Luna ",
						"Inclinación del eje terrestre ",

						1, null));

		cuestionario.add(new APPregunta(
				"Constantemente, en el interior del Sol se transforma el...",

				"helio en hidrógeno", "helio de uranio", "Hidrogeno de helio",
				"uranio en hidrógeno",

				3, null));

		cuestionario
				.add(new APPregunta(
						"La conclusión de que el Sol es un gigantesco reactor nuclear se debe a...",

						" la nueva explicación de los geólogos y biólogos",
						"las investigaciones en física atómica y nuclear",
						" la idea de los antiguos griegos",
						"los descubrimientos de Occidente",

						2, null));

		cuestionario
				.add(new APPregunta(
						"De acuerdo con el autor, si el Sol fuera un cuerpo ________ ya se habría extinguido hace mucho tiempo.",

						"inanimado", "incandescente", "incoloro",
						"incombustible",

						2, null));

		cuestionario.add(new APPregunta("¿Cual es la capital de Suiza?",

		"Estocolmo", "Vienna", "Madrid", "Berna",

		4, null));

		cuestionario
				.add(new APPregunta(
						"El río _________________ es límite de México con Estados Unidos, y el río _________________ es límite con Belice",

						"Bravo - Hondo", "Bravo - Suchiate",
						"Bravo - Usumacinta", "Colorado - Balsas",

						1, null));

		cuestionario
				.add(new APPregunta(
						"Las capas de la atmósfera ordenadas de abajo hacia arriba son...",

						"estratosfera, troposfera, ionosfera",
						"troposfera, estratosfera, ionosfera",
						"ionosfera, estratosfera, troposfera",
						" estratosfera, ionosfera, troposfera",

						2, null));

		// INGLES____________________________________________________________________________

		cuestionario
				.add(new APPregunta(
						"Completa el enunciado. What ________________? You look fascinated. ",

						"are you reading ", "do you read ", "will you read ",
						"did you read ",

						1, null));

		cuestionario
				.add(new APPregunta(
						"Completa el enunciado. She _______ a businesswoman. She has ________ own company. ",

						"is - her ", "was - her ", "were - his ", "are - my ",

						1, null));

		cuestionario
				.add(new APPregunta(
						"Completa el enunciado. Jack was _______ yesterday morning when it started to rain. ",

						"run ", "runs ", "ran ", "running ",

						4, null));

		cuestionario
				.add(new APPregunta(
						"Completa la pregunta. How _____ you _____ to the concert tonight? ",

						"are - going ", "do - go ", "did - go ",
						"were - going ",

						1, null));

		cuestionario
				.add(new APPregunta(
						"Completa el enunciado. I _______ do that. It is too difficult to understand. ",

						"can ", "can’t ", "could ", "couldn’t ",

						2, null));

		cuestionario.add(new APPregunta(
				"What was Robert doing when he had the accident? ",

				"Running on the beach ", "Water skiing in the sea ",
				"Swimming in the pool ", " Playing volleyball ",

				2, null));

		cuestionario.add(new APPregunta(
				"How long was Robert in the hospital? ",

				"Nearly a month ", " Less than a month ",
				"A month and a week ", " A month and a half ",

				4, null));

		cuestionario
				.add(new APPregunta("Why did the nurses feed him? Because… ",
						"his Spanish was not good ",
						"he was there for a long time ",
						"he didn´t like spoons ", "his arms were in plasters ",
						1, null));

	}

	public ArrayList<APPregunta> getCuestionario() {
		return this.cuestionario;
	}
}
