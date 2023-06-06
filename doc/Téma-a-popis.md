#### Téma práce:
 RPG arkáda
#### Popis: 
Jednoduchá single player 2D hra, kdy hráč ovládá rybku v moři a v jednotlivých levelech musí vždy zabít všechny nepřátele, aby mohl postoupit do dalšího kola. Nepřátelé jsou NPC ryby, které se pohybují po předem naprogramovaných trasách, různou rychlostí, každá z nich má určitý způsob útoku a počet Energy points. Plánovaná je vlastní animovaná ručně kreslená grafika.

#### Herní mechanismus:
Hráč má na celou hru 3 životy, pokud se jeho Energy points sníží na 0, bude mu ubrán jeden život (pokud už to není jeho pooslední život) a Energy points se obnoví. Nepřátelé mají vždy jen jeden život a různý počet Energy points. Nepřítel Shark a Piranha hráči uberou Energy points, pokud dojde k vzájemnému dotyku, nepřátelé Squid a Jelly fish mohou hráči Energy points ubrat distančně (Jelly fish útočí v oblasti obdobně jako bomby ve hře Bomberman (tzn. ortogonální úsečky), Squid útočí "oblakem inkoustu", který má vliv na její okolí do určité vzdálenosti).

Hráč útočí pomocí Bubbles, které vystřeluje klávesou "Space" ve směru, kterým je zrovna otočen 9možno pouze vlevo nebo vpravo). Počet Bubbles je ale omezený, po vystřelení maximálně 5ti Bubbles musí hráč Bubbles doplnit (to se provede vyplaváním na hladinu a zmáčknutím klávesy "Ctrl", čímž má k dispozici opět 5 Bubbles.

Každý level se odehrává v rámci jedné obrazovky, nedochází k posunům. Po zabití všech nepřátel může hráč proplout dveřmi z obrazovky ven a bude načten nový level (nová obrazovka).

Ve hře se vyskytují speciální předměty, kterými je Key, Chest a Shield. Jestliže hráč získá Klíč, může otevřít truhlu a z ní se mu pak do inventáře přidá štít, který funguje jako krátkodobá ochrana proti veškerým útokům. Lze jej využít jednou, pomocí klávesy "Shift".