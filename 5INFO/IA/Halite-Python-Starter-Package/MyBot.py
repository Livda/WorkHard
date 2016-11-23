from hlt import *
from networking import *

myID, gameMap = getInit()
sendInit("Livda Zeppelin")

def move(location):
    site = gameMap.getSite(location)
    if site.strength > 100:
      return Move(location, NORTH)
    for d in CARDINALS:
        neighbour_site = gameMap.getSite(location, d)
        if neighbour_site.owner != myID and neighbour_site.strength < site.strength:
            return Move(location, d)
    if site.strength < site.production * 2:
        return Move(location, STILL)
    return Move(location, random.choice(CARDINALS))

while True:
    moves = []
    gameMap = getFrame()
    for y in range(gameMap.height):
        for x in range(gameMap.width):
            location = Location(x, y)
            if gameMap.getSite(location).owner == myID:
                moves.append(move(location))
    sendFrame(moves)
