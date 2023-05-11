import MatchingProblem._

/******************************************************************************
 * FUNCIÓN:                valores
 ******************************************************************************/
val pilot = Vector(Vector(2,3,1,1), Vector(1,1,4,3), Vector(1,2,3,4), Vector(2,3,2,1))
val navig = Vector(Vector(4,1,3,2), Vector(4,2,3,1), Vector(1,1,1,4), Vector(3,2,3,3))
val pilotMas = Vector(Vector(2,3,1,1,2), Vector(1,1,4,3,1), Vector(1,2,3,4,1), Vector(2,3,2,1,1), Vector(1, 1, 1, 1, 1))
val navigMas = Vector(Vector(2,3,1,1,2), Vector(1,1,4,3,1), Vector(1,2,3,4,1), Vector(2,3,2,1,1), Vector(1, 1, 1, 1, 1))

/******************************************************************************
 * FUNCIÓN:                matchByElement
 ******************************************************************************/
matchByElement(1, 2)
matchByElement(3, 3)
matchByElement(10, 4)
matchByElement(2, 5)
matchByElement(7, 10)

/******************************************************************************
 * FUNCIÓN:                matchByElements
 ******************************************************************************/
matchByElements(2)
matchByElements(3)
matchByElements(4)
matchByElements(5)
matchByElements(6)

/******************************************************************************
 * FUNCIÓN:                possibleMatchings
 ******************************************************************************/
possibleMatchings(2)
possibleMatchings(3)
possibleMatchings(4)
possibleMatchings(4).length
possibleMatchings(5)
possibleMatchings(5).length
possibleMatchings(6)
possibleMatchings(6).length

/******************************************************************************
 * FUNCIÓN:                matchings
 ******************************************************************************/
matchings(2)
matchings(3)
matchings(4)
matchings(4).length
matchings(5)
matchings(5).length
matchings(6)
matchings(6).length

/******************************************************************************
 * FUNCIÓN:                weightedMatchings
 ******************************************************************************/
weightedMatchings(2, pilot, navig)
weightedMatchings(3, pilot, navig)
weightedMatchings(4, pilot, navig)
weightedMatchings(4, pilot, navig).length
weightedMatchings(2, pilotMas, navigMas)
weightedMatchings(5, pilotMas, navigMas)
weightedMatchings(5, pilotMas, navigMas).length

/******************************************************************************
 * FUNCIÓN:                bestMatching
 ******************************************************************************/
bestMatching(2, pilot, navig)
bestMatching(3, pilot, navig)
bestMatching(4, pilot, navig)
bestMatching(2, pilotMas, navigMas)
bestMatching(5, pilotMas, navigMas)