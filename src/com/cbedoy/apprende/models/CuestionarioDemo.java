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
package com.cbedoy.apprende.models;

import java.util.ArrayList;

public class CuestionarioDemo {
	private ArrayList<Pregunta> cuestionario;

	public CuestionarioDemo() {
		cuestionario = new ArrayList<Pregunta>();

		// ESPA�OL_____________________________________________________________

		cuestionario
				.add(new Pregunta(
						"Selecciona la opci�n cuya relaci�n sea similar a la que se observa en la pareja de palabras en may�sculas. SUSTANCIAS es a LABORATORIO, como:",

						"alba�iles a carretilla", "enfermeras a hospital",
						"martillo a carpintero ", "tejedor a alfombra ",

						2, null));

		cuestionario
				.add(new Pregunta(
						"Selecciona la opci�n cuyo significado sea similar al de la palabra en may�sculas. Su sonrisa SOCARRONA le molestaba sobremanera.",

						"Sincera", "Formal", "C�nica ", "P�cara ",

						3, null));

		cuestionario
				.add(new Pregunta(
						"Selecciona la opci�n cuyo significado sea opuesto al de la palabra en may�sculas. Francamente, estos problemas para m� son un ALICIENTE.",

						"Atractivo", "Impedimento", "Retraso ", "Est�mulo ",

						2, null));

		cuestionario.add(new Pregunta(
				"�Cu�l de los siguientes enunciados expresa un consejo? ",

				"I want to be a doctor ", " She is going to be a doctor ",
				"You should see a doctor ", "He is a good doctor ",

				3, null));

		cuestionario
				.add(new Pregunta(
						"Completa la siguiente frase. Como el hielo es menos _____ que el agua, flota sobre ella. ",

						"fr�gil", "g�lido ", " denso ", "r�gido ",

						3, null));

		cuestionario
				.add(new Pregunta(
						"Es la oraci�n subordinada en la que la coma se utiliza correctamente. ",

						"Mar�a, la hija del maestro, no lleg� a la fiesta ",
						"Con la feria, todos los ni�os se alegraron ",
						"Para dejar de fumar, Rodolfo se someti� a un tratamientom�dico ",
						"Mart�n, preocupado por el trabajo, no fue a comer ",

						3, null));

		cuestionario
				.add(new Pregunta(
						"Las fichas bibliogr�ficas son �tiles ya que la informaci�n de estas es: ",

						"importante para conocer a los grandes autores literarios ",
						"esencial para preservar y localizar los libros impresos ",
						"fundamental para tener organizada una librer�a ",
						"necesaria para ubicar las revistas especializadas ",

						1, null));

		// BIOLOGIA_________________________________________________________________________

		cuestionario
				.add(new Pregunta(
						"Ordena cronol�gicamente las fases del ciclo de divisi�n celular",

						"Profase, metafase, anafase, telofase e interfase",
						"Metafase, anafase, telofase, interfase y profase",
						"Anafase, telofase, interfase, profase y metafase",
						"Interfase, profase, metafase, anafase y telofase",

						4, null));

		cuestionario
				.add(new Pregunta(
						"Enfermedades causadas por bacterias en las v�as respiratorias. ",

						"Gonorrea y meningitis ", "T�tanos y difteria ",
						"Neumon�a y tuberculosis ",
						"Encefalitis y poliomielitis ",

						3, null));

		cuestionario
				.add(new Pregunta(
						"Es el conjunto de caracter�sticas que permite a los individuos sobrevivir y reproducirse en un ambiente determinado: ",

						"adaptaci�n ", "evoluci�n ", "selecci�n natural ",
						"selecci�n artificial ",

						1, null));

		cuestionario
				.add(new Pregunta(
						"Una caracter�stica que comparten la ciencia y la tecnolog�a es que ambas: ",

						"buscan la soluci�n de problemas espec�ficos reales ",
						"estudian la din�mica de la sociedad ",
						"hacen uso b�sico y exhaustivo del m�todo cient�fico ",
						"venden los productos que se generan de su trabajo ",

						1, null));

		cuestionario
				.add(new Pregunta(
						"Es una categor�a taxon�mica que se define como el conjunto de individuos capaces de entrecruzarse y dejar descendencia f�rtil: ",

						"familia ", "g�nero ", "orden ", "especie ",

						4, null));

		cuestionario
				.add(new Pregunta(
						"Son consecuencias de la acumulaci�n del bi�xido de carbono en la atm�sfera, excepto: ",

						"lluvia �cida en las ciudades ",
						"deshielo de las masas polares ",
						"incremento de los periodos de sequ�as ",
						"largas temporadas de lluvias ",

						1, null));

		cuestionario
				.add(new Pregunta(
						"�Cu�les son los productos elaborados en el organismo durante la respiraci�n aerobia? ",

						"Bi�xido de carbono, agua y energ�a (ATP) ",
						"Ox�geno, agua y bi�xido de carbono ",
						"Bi�xido de carbono, agua y alcohol ",
						"Ox�geno, glucosa y energ�a (ATP) ",

						1, null));

		// FISICA________________________________________________________________________________

		cuestionario
				.add(new Pregunta(
						"Cuando sintonizamos una estaci�n de radio en la banda de AM, la se�al es m�s d�bil o posee menor energ�a que la de FM. Esto se debe a que sus ondas son de menor... ",

						"amplitud ", "frecuencia ", "longitud ", "periodo ", 2,
						null));

		cuestionario
				.add(new Pregunta(
						"Es la relaci�n que existe entre una fuerza aplicada y el �rea sobre la que esta act�a. ",

						"Cohesi�n ", "Fluidez ", "Presi�n ", "Empuje ", 1, null));

		cuestionario
				.add(new Pregunta(
						"El modelo cin�tico de las part�culas permite entender el comportamiento de la: ",

						"presi�n de los l�quidos ",
						"flotaci�n de los cuerpos ",
						"estructura de la materia ", "hidrodin�mica ",

						3, null));

		cuestionario
				.add(new Pregunta(
						"Cuando un rayo de luz viaja por el aire y atraviesa por un vidrio cambia la direcci�n de su trayectoria debido al cambio en: ",

						"el color de la luz ", "la naturaleza de la luz ",
						"la velocidad de la luz ", "la cantidad de luz ", 3,
						null));

		cuestionario
				.add(new Pregunta(
						"Las l�neas magn�ticas del im�n de una br�jula salen por el_______ y llegan hacia el _______. ",

						"sur - norte ", "norte - sur ", "este - oeste ",
						"oeste - este ",

						2, null));

		cuestionario
				.add(new Pregunta(
						"Es el proceso mediante el cual un cuerpo adquiere carga el�ctrica a trav�s de otro cuerpo cargado el�ctricamente; esto sin que haya contacto entre ambos cuerpos. ",

						"Conducci�n ", "Frotamiento ", "Inducci�n ",
						"Ionizaci�n ",

						3, null));

		// QUIMICA_____________________________________________________________________________

		cuestionario
				.add(new Pregunta(
						"La masa de un mol de una sustancia expresada en gramos recibe el nombre de: ",

						"masa molar ", "peso at�mico ", "masa at�mica ",
						"peso molecular ",

						1, null));

		cuestionario.add(new Pregunta(
				"Es la combinaci�n f�sica de dos o m�s sustancias. ",

				"Compuesto ", "Elemento ", "Mezcla ", "Mol�cula ",

				3, null));

		cuestionario.add(new Pregunta(
				"�Qu� sustancia es considerada el disolvente universal? ",

				"Alcohol ", "Acetona ", "Agua ", "Vinagre ",

				3, null));

		cuestionario.add(new Pregunta(
				"�Qu� le sucede a un �tomo cuando se oxida? ",

				"Gana uno o m�s electrones ", "Pierde uno o m�s protones ",
				"Gana uno o m�s neutrones ", "Pierde uno o m�s electrones ",

				4, null));

		cuestionario
				.add(new Pregunta(
						"Completa el enunciado. En una ________________ hay una reorganizaci�n de los �tomos formando nuevas sustancias. ",

						"fusi�n ", "reacci�n qu�mica ", "mezcla homog�nea ",
						"destilaci�n ",

						2, null));

		cuestionario
				.add(new Pregunta(
						"Esta ley establece que, en un proceso qu�mico, la masa permanece constante antes y despu�s de la reacci�n. ",

						"Proust ", "Dalton ", "Lavoisier ", "Wenzel ",

						3, null));

		cuestionario
				.add(new Pregunta(
						"Una de las caracter�sticas de estas sustancias es que al entrar en contacto con el papel tornasol lo cambian a color rosa. ",

						"B�sicas ", "Indicadoras ", "�cidas ", "Neutras ",

						3, null));

		cuestionario
				.add(new Pregunta(
						"Mendeleiev construy� la tabla peri�dica de los elementos, de acuerdo con: ",

						"los n�meros at�micos ", "las masas at�micas ",
						"el n�mero de neutrones ", "el n�mero de electrones ",

						2, null));

		// HISTORIA_________________________________________________________________________

		cuestionario
				.add(new Pregunta(
						"Completa el siguiente enunciado. Entre 1822 y ______ , __________ protagoniz� fallidos intentos para reconquistar M�xico, cuya independencia hab�a desconocido. ",

						"1829 - Espa�a ", "1838 - Francia ",
						"1847 - Inglaterra ", "1862 - Alemania ",

						1, null));

		cuestionario
				.add(new Pregunta(
						"La principal actividad de la econom�a de los pueblos mesoamericanos fue la explotaci�n: ",

						"agr�cola ", "minera ", "maderera ", "artesanal ",

						1, null));

		cuestionario
				.add(new Pregunta(
						"Se considera el efecto demogr�fico m�s importante de la Segunda Guerra Mundial: ",

						"millones de mutilados y heridos ",
						"millones de muertos civiles y militares ",
						"cantidad de refugiados ", "cantidad de desplazados ",

						1, null));

		cuestionario
				.add(new Pregunta(
						"Se considera el efecto demogr�fico m�s importante de la Segunda Guerra Mundial: ",

						"millones de mutilados y heridos ",
						"millones de muertos civiles y militares ",
						"cantidad de refugiados ", "cantidad de desplazados ",

						2, null));

		cuestionario
				.add(new Pregunta(
						"El invento de James Watt que transform� el comercio terrestre y mar�timo durante la Revoluci�n Industrial fue: ",

						"la locomotora ", "el motor a diesel ",
						"la m�quina de vapor ", "vapor ",

						3, null));

		cuestionario.add(new Pregunta(
				"La principal consecuencia de la llamada Paz Armada fue: ",

				"el inicio de la Primera Guerra Mundial ",
				"la Guerra del Opio entre Inglaterra y China ",
				"el r�pido desarrollo de la industria petrolera ",
				"la creaci�n de las Conferencias de Par�s ",

				1, null));

		cuestionario
				.add(new Pregunta(
						"�Cu�l es el principal contenido del Tratado de Guadalupe Hidalgo? ",

						"Casi la mitad del territorio mexicano pasar�a a ser parte de Estados Unidos ",
						"Inglaterra mediar�a en el conflicto entre los gobiernos de Texas y M�xico ",
						"Estados Unidos reclamaba la aplicaci�n de la doctrina Monroe a su favor ",
						"Texas le declaraba la guerra a M�xico por cambiar su leyes constitucionales ",

						1, null));

		cuestionario
				.add(new Pregunta(
						"Son factores que impulsan el desarrollo de la globalizaci�n, excepto: ",

						"libre comercio mundial ", "empresas multinacionales ",
						"privatizaciones masivas ",
						"tradicionalismo ideol�gico ",

						4, null));

		// GEOGRAFIA__________________________________________________________________________

		cuestionario
				.add(new Pregunta(
						"Es una consecuencia positiva para los pa�ses receptores de inmigrantes. ",

						"Trabajadores especializados ",
						"Fortalecimiento de su econom�a ",
						"Mayor �ndice educativo ", "Env�o de remesas ",

						2, null));

		cuestionario.add(new Pregunta(" Las tradiciones son ejemplo de:",

		"educaci�n ", "multiculturalidad ", "interculturalidad ",
				"identidad cultural ",

				4, null));

		cuestionario
				.add(new Pregunta(
						"Es el factor astron�mico que influye en la din�mica terrestre, permitiendo el desarrollo de la vida en el planeta. ",

						"Distancia de la Tierra al Sol ",
						"Forma de la Tierra ", "Presencia de la Luna ",
						"Inclinaci�n del eje terrestre ",

						1, null));

		cuestionario.add(new Pregunta(
				"Constantemente, en el interior del Sol se transforma el...",

				"helio en hidr�geno", "helio de uranio", "Hidrogeno de helio",
				"uranio en hidr�geno",

				3, null));

		cuestionario
				.add(new Pregunta(
						"La conclusi�n de que el Sol es un gigantesco reactor nuclear se debe a...",

						" la nueva explicaci�n de los ge�logos y bi�logos",
						"las investigaciones en f�sica at�mica y nuclear",
						" la idea de los antiguos griegos",
						"los descubrimientos de Occidente",

						2, null));

		cuestionario
				.add(new Pregunta(
						"De acuerdo con el autor, si el Sol fuera un cuerpo ________ ya se habr�a extinguido hace mucho tiempo.",

						"inanimado", "incandescente", "incoloro",
						"incombustible",

						2, null));

		cuestionario.add(new Pregunta("�Cual es la capital de Suiza?",

		"Estocolmo", "Vienna", "Madrid", "Berna",

		4, null));

		cuestionario
				.add(new Pregunta(
						"El r�o _________________ es l�mite de M�xico con Estados Unidos, y el r�o _________________ es l�mite con Belice",

						"Bravo - Hondo", "Bravo - Suchiate",
						"Bravo - Usumacinta", "Colorado - Balsas",

						1, null));

		cuestionario
				.add(new Pregunta(
						"Las capas de la atm�sfera ordenadas de abajo hacia arriba son...",

						"estratosfera, troposfera, ionosfera",
						"troposfera, estratosfera, ionosfera",
						"ionosfera, estratosfera, troposfera",
						" estratosfera, ionosfera, troposfera",

						2, null));

		// INGLES____________________________________________________________________________

		cuestionario
				.add(new Pregunta(
						"Completa el enunciado. What ________________? You look fascinated. ",

						"are you reading ", "do you read ", "will you read ",
						"did you read ",

						1, null));

		cuestionario
				.add(new Pregunta(
						"Completa el enunciado. She _______ a businesswoman. She has ________ own company. ",

						"is - her ", "was - her ", "were - his ", "are - my ",

						1, null));

		cuestionario
				.add(new Pregunta(
						"Completa el enunciado. Jack was _______ yesterday morning when it started to rain. ",

						"run ", "runs ", "ran ", "running ",

						4, null));

		cuestionario
				.add(new Pregunta(
						"Completa la pregunta. How _____ you _____ to the concert tonight? ",

						"are - going ", "do - go ", "did - go ",
						"were - going ",

						1, null));

		cuestionario
				.add(new Pregunta(
						"Completa el enunciado. I _______ do that. It is too difficult to understand. ",

						"can ", "can�t ", "could ", "couldn�t ",

						2, null));

		cuestionario.add(new Pregunta(
				"What was Robert doing when he had the accident? ",

				"Running on the beach ", "Water skiing in the sea ",
				"Swimming in the pool ", " Playing volleyball ",

				2, null));

		cuestionario.add(new Pregunta(
				"How long was Robert in the hospital? ",

				"Nearly a month ", " Less than a month ",
				"A month and a week ", " A month and a half ",

				4, null));

		cuestionario
				.add(new Pregunta("Why did the nurses feed him? Because� ",
						"his Spanish was not good ",
						"he was there for a long time ",
						"he didn�t like spoons ", "his arms were in plasters ",
						1, null));

	}

	public ArrayList<Pregunta> getCuestionario() {
		return this.cuestionario;
	}
}
