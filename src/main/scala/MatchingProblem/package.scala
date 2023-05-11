package object MatchingProblem
{
  /** ****************************************************************************
   * AUTORES
   * Samuel Galindo Cuevas - 202177491
   * Nicolas Herrera Marulanda - 202182551
   * **************************************************************************** */

  /** ****************************************************************************
   * Funciones Entregadas por el profesor.
   * **************************************************************************** */
  type Match = (Int, Int)
  type Matching = List[Match]
  type Preferences = Vector[Vector[Int]]

  /** ****************************************************************************
   * FUNCIÓN:                matchByElement
   * DESCRIPCIÓN:            Función que retorna todas las posibles combinaciones entre el piloto i con n copilotos.
   * PARÁMETROS DE ENTRADA
   * $i :                    Numero del piloto.
   * $n :                    Numero de copilotos.
   * RETORNO
   * List[Match] :  			   Todas las posibles combinaciones entre el piloto i con n copilotos.
   * **************************************************************************** */
  def matchByElement(i:Int, n:Int): List[Match] =
  {
    (for(x <- 1 to n) yield (i, x)).toList
  }

  /** ****************************************************************************
   * FUNCIÓN:                matchsByElements
   * DESCRIPCIÓN:            Función que retorna todas las posibles combinaciones entre n pilotos con n copilotos.
   * PARÁMETROS DE ENTRADA
   * $n :                    Numero de copilotos y pilotos.
   * RETORNO
   * List[List[Match]] :  	 Todas las posibles combinaciones entre n pilotos con n copilotos.
   * **************************************************************************** */
  def matchByElements(n: Int): List[List[Match]] =
  {
    (for(x <- 1 to n) yield(matchByElement(x, n))).toList
  }

  /** ****************************************************************************
   * FUNCIÓN:                possibleMatchings
   * DESCRIPCIÓN:            Función que retorna todas las posibles combinaciones entre parjeas de n pilotos con n copilotos.
   * PARÁMETROS DE ENTRADA
   * $n :                    Numero de copilotos y pilotos.
   * RETORNO
   * List[List[Match]] :  	 Todas las posibles combinaciones entre parjeas de n pilotos con n copilotos.
   * **************************************************************************** */
  def possibleMatchings(n: Int): List[List[Match]] = {

    val allMatches = matchByElements(n)

    def auxCombinateMatches(possibleMatches: List[List[Match]], matches: List[List[Match]]): List[List[Match]] =
    {
      matches match
      {
        case Nil => possibleMatches
        case a :: tail => auxCombinateMatches((for (x <- a; y <- possibleMatches) yield y :+ x), tail)
      }
    }

    allMatches match
    {
      case a :: b :: tail => auxCombinateMatches((for (x <- a; y <- b) yield List(x, y)), tail)
    }
  }

  /** ****************************************************************************
   * FUNCIÓN:                matchings
   * DESCRIPCIÓN:            Similar a la función possibleMatchings pero sin valores repetidos
   * PARÁMETROS DE ENTRADA
   * $n :                    Numero de copilotos y pilotos.
   * RETORNO
   * List[List[Match]] :  	 Todas las posibles combinaciones validas entre parejas de n pilotos con n copilotos.
   * **************************************************************************** */
  def matchings(n: Int): List[Matching] = {
    def listaValida(matching: Matching): Boolean = {
      val segundas = for(x <- matching) yield x._2
      if (segundas.toSet.size == matching.size) true else false
    }

    val posiblesMatchs = possibleMatchings(n)
    for (x <- posiblesMatchs if listaValida(x) && !x.isEmpty) yield x
  }

  /** ****************************************************************************
   * FUNCIÓN:                weightedMatchings
   * DESCRIPCIÓN:            devuelve las tuplas validas con sus respectivo peso
   * PARÁMETROS DE ENTRADA
   * $n :                    numero de pilotos y copilotos
   * $pilotPres:             matriz de preferencia de los pilotos
   * $naviPrefs:             matriz de preferencia de los copilotos
   * RETORNO
   * List[(Matching, Int)]] : Tuplas validas con su respectivo peso.
   * **************************************************************************** */
  def weightedMatchings(n: Int, pilotPrefs: Preferences, navigPrefs: Preferences): List[(Matching, Int)] = {

    def generarTupla(matching: Matching): (Matching, Int) = {
      def generarPeso(lista: Matching): Int =
      {
        val valorTupla = for (x <- lista; fila = x._1 -1; columna = x._2 - 1 ;pil = pilotPrefs(fila)(columna); nav = navigPrefs(columna)(fila); sol = pil * nav) yield sol
        valorTupla.sum
      }
      (matching, generarPeso(matching))
    }

    val matchValidos = matchings(n)
    for (x <- matchValidos) yield generarTupla(x)
  }

  /** ****************************************************************************
   * FUNCIÓN:                bestMatching
   * DESCRIPCIÓN:            Función que utiliza las anteriores para devolver la mejor combinacion posible
   * PARÁMETROS DE ENTRADA
   * $n :                   numero de pilotos y copilotos
   * $pilotPres:          matriz de preferencia de los pilotos
   * $naviPrefs:            matriz de preferencia de los copilotos
   * RETORNO
   * (Matching, Int):     Tupla de la lista de Matching con su peso
   * **************************************************************************** */
  def bestMatching(n: Int, pilotPrefs: Preferences, navigPrefs: Preferences): (Matching, Int) = {

    val matchPesos = weightedMatchings(n, pilotPrefs, navigPrefs)
    val pesos = for(x <- matchPesos) yield x._2
    val pesoMaximo = pesos.max
    val tuplaMax = for(x <- matchPesos if x._2 == pesoMaximo) yield x

    tuplaMax.head
  }
}
