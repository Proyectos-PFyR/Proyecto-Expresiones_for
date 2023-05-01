import scala.::

package object MatchingProblem
{
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
    ///(for(x <- 1 to n) yield (i, x)).toList
    ((1 to n) map (x => (i, x))).toList
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

    def auxPossibleMatchings(matchs: List[List[Match]]): List[List[Match]] =
    {
      matchs match
      {
        case Nil => Nil
        case _ => (matchs.head flatMap (x => matchs.tail flatMap (y => y map (y => List(x, y))))) ::: auxPossibleMatchings(matchs.tail)
      }
    }
    auxPossibleMatchings(allMatches)
  }
}
